import re

with open('index.html', 'r') as f:
    content = f.read()

target = """      alert(`🎉 Order Confirmed Successfully!\\n\\nOrder ID: ${newOrderId}\\nTotal: ₹${orderTotal}\\nPayment: ${selectedPayment} (Prepaid Verified)\\n\\nThank you for shopping on Meesho! You can track this order under 'My Orders'.`);"""
replacement = target + """
      switchTab('home');"""

content = content.replace(target, replacement)

with open('index.html', 'w') as f:
    f.write(content)

