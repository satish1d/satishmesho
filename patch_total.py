with open('index.html', 'r') as f:
    content = f.read()

content = content.replace("document.getElementById('bill-subtotal').innerText", "if (document.getElementById('bill-subtotal')) document.getElementById('bill-subtotal').innerText")
content = content.replace("document.getElementById('bill-total').innerText", "if (document.getElementById('bill-total')) document.getElementById('bill-total').innerText")

with open('index.html', 'w') as f:
    f.write(content)

