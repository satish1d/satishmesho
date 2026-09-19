with open('index.html', 'r') as f:
    content = f.read()

bad_hook = """  // Hook openCart to reset stepper
  const originalOpenCart = window.openCart;
  window.openCart = function() {
      currentCheckoutStep = 1;
      updateStepperUI();
      document.getElementById('cart-sheet').classList.add('open');
  };"""

good_hook = """  // Hook openCart to reset stepper
  const originalOpenCartHook = window.openCart;
  window.openCart = function() {
      currentCheckoutStep = 1;
      updateStepperUI();
      originalOpenCartHook();
  };"""

content = content.replace(bad_hook, good_hook)

with open('index.html', 'w') as f:
    f.write(content)

