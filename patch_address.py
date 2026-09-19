import re

with open('index.html', 'r') as f:
    content = f.read()

# 1. Full page cart sheet
content = content.replace('<div class="sheet-overlay" id="cart-sheet" onclick="closeSheetOnBackdrop(event, \'cart-sheet\')">\n    <div class="sheet-content">', '<div class="sheet-overlay" id="cart-sheet" onclick="closeSheetOnBackdrop(event, \'cart-sheet\')">\n    <div class="sheet-content" style="max-height: 100vh; height: 100vh; border-radius: 0;">')

# 2. Empty the address fields
content = content.replace('<input type="text" id="addr-name" value="Satish Baldaniya"', '<input type="text" id="addr-name" value=""')
content = content.replace('<input type="text" id="addr-phone" value="9876543210"', '<input type="text" id="addr-phone" value=""')
content = content.replace('<input type="text" id="addr-pin" value="395006"', '<input type="text" id="addr-pin" value=""')
content = content.replace('<input type="text" id="addr-city" value="Surat"', '<input type="text" id="addr-city" value=""')
content = content.replace('<input type="text" id="addr-house" value="Flat 402, Royal Residency"', '<input type="text" id="addr-house" value=""')
content = content.replace('<input type="text" id="addr-road" value="Varachha"', '<input type="text" id="addr-road" value=""')

# 3. Empty summary-address-text hardcoded value
content = content.replace('<p class="deliver-to-address-line" id="summary-address-text" style="font-size: 12px; color: #666; margin-top: 8px;">Satish Baldaniya • Flat 402, Royal Residency, Varachha, Surat, Gujarat - 395006</p>', '<p class="deliver-to-address-line" id="summary-address-text" style="font-size: 12px; color: #666; margin-top: 8px;"></p>')

with open('index.html', 'w') as f:
    f.write(content)

