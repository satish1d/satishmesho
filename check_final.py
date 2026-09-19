with open('index.html', 'r') as f:
    text = f.read()

# Check for presence of "document.getElementById('bill-subtotal').innerText" to see if null check was applied
import re
print("Check 1:", "if (document.getElementById('bill-subtotal'))" in text)
print("Check 2:", "originalOpenCartHook();" in text)
