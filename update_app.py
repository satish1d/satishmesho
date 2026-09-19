with open('index.html', 'r') as f:
    code = f.read()

# Replace renderCartSheet with real-time order support
old_render_cart_start = """    function renderCartSheet() {
      const container = document.getElementById('cart-items-container');
      if (cart.length === 0) {
        container.innerHTML = `
          <div style="text-align: center; padding: 40px 14px; color: #888;">
            <div style="font-size: 40px;">🛒</div>
            <h4 style="margin-top: 8px; font-size: 14px; color: #333;">Your Cart is Empty</h4>
            <p style="font-size: 11px; margin-top: 4px;">Explore lowest wholesale price collections now!</p>
            <button onclick="closeCart(); switchTab('home');" style="margin-top: 14px; background: var(--primary); color: white; border: none; padding: 8px 16px; border-radius: 6px; font-size: 12px; font-weight: bold; cursor: pointer;">Start Shopping</button>
          </div>
        `;
        if (document.getElementById('bill-subtotal')) document.getElementById('bill-subtotal').innerText = '₹0';
        if (document.getElementById('bill-subtotal-summary')) document.getElementById('bill-subtotal-summary').innerText = '₹0';

        if (document.getElementById('bill-total')) document.getElementById('bill-total').innerText = '₹0';
        if (document.getElementById('bill-total-summary')) document.getElementById('bill-total-summary').innerText = '₹0';
        if (document.getElementById('bottom-total-price')) document.getElementById('bottom-total-price').innerText = '₹0';

        document.getElementById('stepper-continue-btn').disabled = true;
        document.getElementById('stepper-continue-btn').style.opacity = '0.5';
        return;
      }"""

new_render_cart_start = """    function renderCartSheet() {
      const container = document.getElementById('cart-items-container');
      if (cart.length === 0) {
        if (orders && orders.length > 0) {
          const ordersHtml = orders.map(ord => `
            <div class="order-card" style="margin-bottom: 12px; background: white; border: 1px solid var(--border); border-radius: 8px; padding: 12px;">
              <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid var(--border); padding-bottom: 8px; margin-bottom: 8px;">
                <div>
                  <div style="font-size: 12px; font-weight: 800; color: #333;">${ord.orderId}</div>
                  <div style="font-size: 10px; color: #888;">Placed on ${ord.date} • ${ord.paymentMethod}</div>
                </div>
                <span class="order-status-pill" style="background: #E8F5E9; color: #2E7D32; border: 1px solid #A5D6A7; font-size: 10px; padding: 2px 6px; border-radius: 4px; font-weight: bold;">${ord.status}</span>
              </div>
              <div>
                ${(ord.items || []).map(it => `
                  <div style="display: flex; gap: 8px; align-items: center; margin-bottom: 6px;">
                    <img src="${it.imageUrl || it.image}" style="width: 44px; height: 44px; border-radius: 4px; object-fit: cover;" />
                    <div style="flex: 1;">
                      <div style="font-size: 12px; font-weight: 600; line-height: 15px;">${it.title}</div>
                      <div style="font-size: 12px; font-weight: bold; color: #111;">₹${it.price} <span style="font-size: 10px; color: #888; font-weight: normal;">Qty: ${it.qty || 1}</span></div>
                    </div>
                  </div>
                `).join('')}
              </div>
              <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 8px; padding-top: 8px; border-top: 1px dashed var(--border);">
                <div style="font-size: 12px; font-weight: 800;">Total Paid: <span style="color: var(--primary);">₹${ord.total}</span></div>
                <button onclick="alert('Order Status: Confirmed & Under Packing. Expected delivery in 3-4 days.')" style="background: none; border: 1px solid var(--primary); color: var(--primary); padding: 4px 8px; border-radius: 4px; font-size: 10px; font-weight: bold; cursor: pointer;">Track Order</button>
              </div>
            </div>
          `).join('');

          container.innerHTML = `
            <div style="padding: 6px 0;">
              <div style="background: #E8F5E9; border: 1px solid #C8E6C9; border-radius: 8px; padding: 12px; margin-bottom: 12px; display: flex; align-items: center; justify-content: space-between;">
                <div>
                  <div style="font-size: 13px; font-weight: 700; color: #2E7D32;">🎉 Order Placed in Real-Time!</div>
                  <div style="font-size: 11px; color: #555; margin-top: 2px;">Your order has been confirmed successfully.</div>
                </div>
                <button onclick="closeCart(); switchTab('home');" style="background: var(--primary); color: white; border: none; padding: 6px 12px; border-radius: 4px; font-size: 11px; font-weight: bold; cursor: pointer;">Shop More</button>
              </div>
              <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                <div style="font-size: 13px; font-weight: 800; color: #222;">📦 Placed Orders in Cart:</div>
                <span style="font-size: 11px; color: var(--primary); font-weight: 600; cursor: pointer;" onclick="closeCart(); switchTab('orders');">View in My Orders →</span>
              </div>
              ${ordersHtml}
            </div>
          `;

          const lastOrd = orders[0];
          if (document.getElementById('bill-subtotal')) document.getElementById('bill-subtotal').innerText = `₹${lastOrd.total}`;
          if (document.getElementById('bill-total')) document.getElementById('bill-total').innerText = `₹${lastOrd.total}`;
          if (document.getElementById('bottom-total-price')) document.getElementById('bottom-total-price').innerText = `₹${lastOrd.total} (Paid)`;

          const btn = document.getElementById('stepper-continue-btn');
          if (btn) {
            btn.disabled = false;
            btn.style.opacity = '1';
            btn.innerText = 'Shop More Products';
            btn.onclick = function() { closeCart(); switchTab('home'); };
          }
          return;
        }

        container.innerHTML = `
          <div style="text-align: center; padding: 40px 14px; color: #888;">
            <div style="font-size: 40px;">🛒</div>
            <h4 style="margin-top: 8px; font-size: 14px; color: #333;">Your Cart is Empty</h4>
            <p style="font-size: 11px; margin-top: 4px;">Explore lowest wholesale price collections now!</p>
            <button onclick="closeCart(); switchTab('home');" style="margin-top: 14px; background: var(--primary); color: white; border: none; padding: 8px 16px; border-radius: 6px; font-size: 12px; font-weight: bold; cursor: pointer;">Start Shopping</button>
          </div>
        `;
        if (document.getElementById('bill-subtotal')) document.getElementById('bill-subtotal').innerText = '₹0';
        if (document.getElementById('bill-subtotal-summary')) document.getElementById('bill-subtotal-summary').innerText = '₹0';

        if (document.getElementById('bill-total')) document.getElementById('bill-total').innerText = '₹0';
        if (document.getElementById('bill-total-summary')) document.getElementById('bill-total-summary').innerText = '₹0';
        if (document.getElementById('bottom-total-price')) document.getElementById('bottom-total-price').innerText = '₹0';

        document.getElementById('stepper-continue-btn').disabled = true;
        document.getElementById('stepper-continue-btn').style.opacity = '0.5';
        return;
      }"""

if old_render_cart_start in code:
    code = code.replace(old_render_cart_start, new_render_cart_start)
    print("Replaced renderCartSheet successfully!")
else:
    print("Could not find old_render_cart_start")

with open('index.html', 'w') as f:
    f.write(code)

