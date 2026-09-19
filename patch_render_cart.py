with open('index.html', 'r') as f:
    content = f.read()

# Replace assignments
old_1 = "document.getElementById('bill-subtotal').innerText = `₹${total}`;"
new_1 = """document.getElementById('bill-subtotal').innerText = `₹${total}`;
      const mrp = cart.reduce((acc, item) => acc + (item.originalPrice * item.qty), 0);
      const discount = mrp - total;
      
      const elSubSum = document.getElementById('bill-subtotal-summary');
      if (elSubSum) elSubSum.innerText = `₹${mrp}`;
      
      const elDiscount = document.querySelector('#cart-step-4 .bill-card .bill-row:nth-child(3) span:last-child');
      if (elDiscount) elDiscount.innerText = `- ₹${discount}`;
"""

old_2 = "document.getElementById('bill-total').innerText = `₹${total}`;"
new_2 = """document.getElementById('bill-total').innerText = `₹${total}`;
      const elTotalSum = document.getElementById('bill-total-summary');
      if (elTotalSum) elTotalSum.innerText = `₹${total}`;
      const elBottom = document.getElementById('bottom-total-price');
      if (elBottom) elBottom.innerText = `₹${total}`;
"""

content = content.replace(old_1, new_1)
content = content.replace(old_2, new_2)

# Handle empty cart edge case
old_empty_1 = "document.getElementById('bill-subtotal').innerText = '₹0';"
new_empty_1 = """document.getElementById('bill-subtotal').innerText = '₹0';
        if (document.getElementById('bill-subtotal-summary')) document.getElementById('bill-subtotal-summary').innerText = '₹0';
"""
old_empty_2 = "document.getElementById('bill-total').innerText = '₹0';"
new_empty_2 = """document.getElementById('bill-total').innerText = '₹0';
        if (document.getElementById('bill-total-summary')) document.getElementById('bill-total-summary').innerText = '₹0';
        if (document.getElementById('bottom-total-price')) document.getElementById('bottom-total-price').innerText = '₹0';
"""

content = content.replace(old_empty_1, new_empty_1)
content = content.replace(old_empty_2, new_empty_2)

# Fix open cart
old_open_cart = """function openCart() {
      if (cart.length === 0) {
        showToast("Your cart is empty");
        return;
      }
      renderCartSheet();
      document.getElementById('cart-sheet').classList.add('open');
    }"""
new_open_cart = """function openCart() {
      if (cart.length === 0) {
        showToast("Your cart is empty");
        return;
      }
      renderCartSheet();
      // Will be overridden by the hook at the end of the file, but we keep this base structure
      document.getElementById('cart-sheet').classList.add('open');
    }"""
content = content.replace(old_open_cart, new_open_cart)

with open('index.html', 'w') as f:
    f.write(content)
