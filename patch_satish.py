import re

with open('index.html', 'r') as f:
    content = f.read()

content = content.replace('if (!addr) return "Satish Baldaniya • Flat 402, Royal Residency, Varachha, Surat, Gujarat - 395006";', 'if (!addr) return "Please provide an address";')
content = content.replace('name: "Satish Baldaniya",', 'name: "",')
content = content.replace('phone: "9876543210",', 'phone: "",')
content = content.replace('house: "Flat 402, Royal Residency",', 'house: "",')
content = content.replace('area: "Varachha",', 'area: "",')
content = content.replace('cityState: "Surat, Gujarat",', 'cityState: "",')
content = content.replace('pincode: "395006",', 'pincode: "",')

with open('index.html', 'w') as f:
    f.write(content)

