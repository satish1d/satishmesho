import json
import os
import secrets
import urllib.error
import urllib.request
from urllib.parse import parse_qs, urlparse
from http.server import SimpleHTTPRequestHandler, ThreadingHTTPServer

ROOT = os.path.dirname(os.path.abspath(__file__))
PORT = int(os.environ.get("PORT", "8000"))
CASHFREE_API = "https://api.cashfree.com/pg/orders"
CASHFREE_CREATE_VERSION = "2022-01-01"


def load_local_env():
    env_path = os.path.join(ROOT, ".env")
    if not os.path.exists(env_path):
        return
    with open(env_path, "r", encoding="utf-8") as env_file:
        for line in env_file:
            line = line.strip()
            if not line or line.startswith("#") or "=" not in line:
                continue
            name, value = line.split("=", 1)
            os.environ.setdefault(name.strip(), value.strip().strip('"').strip("'"))


class AppHandler(SimpleHTTPRequestHandler):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, directory=ROOT, **kwargs)

    def send_json(self, status, payload):
        body = json.dumps(payload).encode("utf-8")
        self.send_response(status)
        self.send_header("Content-Type", "application/json")
        self.send_header("Content-Length", str(len(body)))
        self.end_headers()
        self.wfile.write(body)

    def do_POST(self):
        if self.path == "/api/admin/facebook-pixel":
            try:
                length = int(self.headers.get("Content-Length", "0"))
                config = json.loads(self.rfile.read(length) or b"{}")
                pixel_id = str(config.get("pixelId", "")).strip()
                if pixel_id and not pixel_id.isdigit():
                    self.send_json(422, {"error": "Facebook Pixel ID must contain numbers only"})
                    return
                env_path = os.path.join(ROOT, ".env")
                existing = {}
                if os.path.exists(env_path):
                    with open(env_path, "r", encoding="utf-8") as env_file:
                        for line in env_file:
                            if "=" in line and not line.lstrip().startswith("#"):
                                name, value = line.rstrip("\n").split("=", 1)
                                existing[name] = value
                existing["FACEBOOK_PIXEL_ID"] = pixel_id
                existing.setdefault("PORT", str(PORT))
                with open(env_path, "w", encoding="utf-8") as env_file:
                    for name, value in existing.items():
                        env_file.write(f"{name}={value}\n")
                os.environ["FACEBOOK_PIXEL_ID"] = pixel_id
                self.send_json(200, {"ok": True, "configured": bool(pixel_id)})
            except (ValueError, OSError) as error:
                self.send_json(400, {"error": str(error)})
            return
        if self.path == "/api/admin/cashfree-config":
            try:
                length = int(self.headers.get("Content-Length", "0"))
                config = json.loads(self.rfile.read(length) or b"{}")
                client_id = str(config.get("clientId", "")).strip()
                client_secret = str(config.get("clientSecret", "")).strip()
                if not client_id or not client_secret:
                    self.send_json(422, {"error": "Client ID and Secret Key are required"})
                    return
                env_path = os.path.join(ROOT, ".env")
                existing = {}
                if os.path.exists(env_path):
                    with open(env_path, "r", encoding="utf-8") as env_file:
                        for line in env_file:
                            if "=" in line and not line.lstrip().startswith("#"):
                                name, value = line.rstrip("\n").split("=", 1)
                                existing[name] = value
                existing["CASHFREE_CLIENT_ID"] = client_id
                existing["CASHFREE_CLIENT_SECRET"] = client_secret
                existing.setdefault("PORT", str(PORT))
                with open(env_path, "w", encoding="utf-8") as env_file:
                    for name, value in existing.items():
                        env_file.write(f"{name}={value}\n")
                os.environ["CASHFREE_CLIENT_ID"] = client_id
                os.environ["CASHFREE_CLIENT_SECRET"] = client_secret
                self.send_json(200, {"ok": True, "configured": True})
            except (ValueError, OSError) as error:
                self.send_json(400, {"error": str(error)})
            return
        if self.path != "/api/cashfree/order":
            self.send_json(404, {"error": "Not found"})
            return

        client_id = os.environ.get("CASHFREE_CLIENT_ID")
        client_secret = os.environ.get("CASHFREE_CLIENT_SECRET")
        if not client_id or not client_secret:
            self.send_json(503, {"error": "Cashfree server credentials are not configured"})
            return

        try:
            length = int(self.headers.get("Content-Length", "0"))
            request_data = json.loads(self.rfile.read(length) or b"{}")
            amount = float(request_data.get("amount", 0))
            customer = request_data.get("customer", {})
            if amount <= 0 or not customer.get("name") or not customer.get("phone"):
                self.send_json(400, {"error": "Valid amount, customer name, and phone are required"})
                return

            order_id = "MEESHO_" + secrets.token_hex(8).upper()
            payload = {
                "order_id": order_id,
                "order_amount": round(amount, 2),
                "order_currency": "INR",
                "customer_details": {
                    "customer_id": customer.get("id") or order_id,
                    "customer_name": customer["name"],
                    "customer_email": customer.get("email") or "customer@example.com",
                    "customer_phone": customer["phone"],
                },
                "order_meta": {
                    "return_url": request_data.get("returnUrl") or "http://localhost:8000/?cashfree_order_id={order_id}",
                },
                "order_note": "Meesho storefront order",
            }
            request = urllib.request.Request(
                CASHFREE_API,
                data=json.dumps(payload).encode("utf-8"),
                headers={
                    "Content-Type": "application/json",
                    "x-api-version": CASHFREE_CREATE_VERSION,
                    "x-client-id": client_id,
                    "x-client-secret": client_secret,
                },
                method="POST",
            )
            with urllib.request.urlopen(request, timeout=20) as response:
                cashfree_data = json.loads(response.read().decode("utf-8"))
            redirect_url = ""
            for candidate in (
                cashfree_data.get("payment_link"),
                cashfree_data.get("payment_url"),
                cashfree_data.get("redirect_url"),
                cashfree_data.get("url"),
                (cashfree_data.get("payments") or {}).get("web"),
            ):
                if isinstance(candidate, str) and candidate.startswith(("http://", "https://")):
                    redirect_url = candidate
                    break
            payment_session_id = cashfree_data.get("payment_session_id")
            if not redirect_url and not payment_session_id:
                self.send_json(502, {"error": "Cashfree did not return a hosted checkout URL"})
                return
            self.send_json(200, {
                "orderId": order_id,
                "redirectUrl": redirect_url,
                "paymentSessionId": payment_session_id,
            })
        except urllib.error.HTTPError as error:
            detail = error.read().decode("utf-8", errors="replace")
            self.send_json(error.code, {"error": "Cashfree rejected the order", "detail": detail})
        except (ValueError, KeyError, urllib.error.URLError) as error:
            self.send_json(400, {"error": str(error)})

    def do_GET(self):
        parsed = urlparse(self.path)
        if parsed.path == "/api/admin/facebook-pixel":
            self.send_json(200, {
                "configured": bool(os.environ.get("FACEBOOK_PIXEL_ID")),
                "pixelId": os.environ.get("FACEBOOK_PIXEL_ID", ""),
            })
            return
        if parsed.path == "/api/admin/cashfree-config":
            self.send_json(200, {
                "configured": bool(os.environ.get("CASHFREE_CLIENT_ID") and os.environ.get("CASHFREE_CLIENT_SECRET")),
                "mode": "production",
            })
            return
        if parsed.path == "/api/cashfree/status":
            client_id = os.environ.get("CASHFREE_CLIENT_ID")
            client_secret = os.environ.get("CASHFREE_CLIENT_SECRET")
            order_id = parse_qs(parsed.query).get("order_id", [""])[0]
            if not client_id or not client_secret or not order_id:
                self.send_json(400, {"error": "Cashfree credentials and order_id are required"})
                return
            try:
                request = urllib.request.Request(
                    f"{CASHFREE_API}/{order_id}",
                    headers={
                        "x-api-version": "2023-08-01",
                        "x-client-id": client_id,
                        "x-client-secret": client_secret,
                    },
                    method="GET",
                )
                with urllib.request.urlopen(request, timeout=20) as response:
                    cashfree_data = json.loads(response.read().decode("utf-8"))
                self.send_json(200, {"orderStatus": cashfree_data.get("order_status")})
            except urllib.error.HTTPError as error:
                self.send_json(error.code, {"error": "Unable to verify Cashfree order"})
            except urllib.error.URLError as error:
                self.send_json(502, {"error": str(error)})
            return
        super().do_GET()


if __name__ == "__main__":
    load_local_env()
    print(f"Serving Meesho storefront and Cashfree API on http://localhost:{PORT}")
    ThreadingHTTPServer(("127.0.0.1", PORT), AppHandler).serve_forever()
