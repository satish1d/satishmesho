with open('index.html', 'r') as f:
    code = f.read()

# 1. Update executeCheckout
target_checkout = """      orders.unshift(newOrder);
      cart = [];
      saveState();

      // Close all overlays/sheets completely"""

replacement_checkout = """      orders.unshift(newOrder);
      cart = [];
      saveState();
      renderOrders();
      renderCartSheet();

      // Close all overlays/sheets completely"""

if target_checkout in code:
    code = code.replace(target_checkout, replacement_checkout)
    print("Updated executeCheckout!")
else:
    print("Could not find target_checkout")

# 2. Update addToCart
target_add = """      saveState();
      showToast(`Added to cart: ${prod.title.slice(0, 24)}...`);"""

replacement_add = """      saveState();
      renderCartSheet();
      showToast(`Added to cart: ${prod.title.slice(0, 24)}...`);"""

if target_add in code:
    code = code.replace(target_add, replacement_add)
    print("Updated addToCart!")
else:
    print("Could not find target_add")

# 3. Update updateStepperUI
target_stepper_title = """    if (currentCheckoutStep === 1) titleEl.innerHTML = `Shopping Cart (<span id="cart-sheet-count">${cart.length}</span>)`;"""
replacement_stepper_title = """    if (currentCheckoutStep === 1) {
      if (cart.length === 0 && orders && orders.length > 0) {
        titleEl.innerHTML = `Shopping Cart • Placed Orders (${orders.length})`;
      } else {
        titleEl.innerHTML = `Shopping Cart (<span id="cart-sheet-count">${cart.length}</span>)`;
      }
    }"""

if target_stepper_title in code:
    code = code.replace(target_stepper_title, replacement_stepper_title)
    print("Updated stepper title!")
else:
    print("Could not find target_stepper_title")

target_btn_step1 = """    if (currentCheckoutStep === 1) {
        btn.innerText = 'Continue';
        btn.style.width = 'auto';
        priceInfo.style.display = 'flex';
        btn.style.background = 'var(--primary)';
    }"""

replacement_btn_step1 = """    if (currentCheckoutStep === 1) {
        if (cart.length === 0 && orders && orders.length > 0) {
            btn.innerText = 'Shop More Products';
            btn.disabled = false;
            btn.style.opacity = '1';
            btn.style.width = 'auto';
            priceInfo.style.display = 'flex';
            btn.style.background = 'var(--primary)';
            btn.onclick = function() { closeCart(); switchTab('home'); };
        } else {
            btn.innerText = 'Continue';
            btn.disabled = cart.length === 0;
            btn.style.opacity = cart.length === 0 ? '0.5' : '1';
            btn.style.width = 'auto';
            priceInfo.style.display = 'flex';
            btn.style.background = 'var(--primary)';
            btn.onclick = function() { nextCheckoutStep(); };
        }
    }"""

if target_btn_step1 in code:
    code = code.replace(target_btn_step1, replacement_btn_step1)
    print("Updated stepper button step 1!")
else:
    print("Could not find target_btn_step1")

with open('index.html', 'w') as f:
    f.write(code)

