import re

with open('index.html', 'r') as f:
    content = f.read()

# Define replacement
stepper_html = """      <div class="sheet-header">
        <h3 id="cart-sheet-title">Shopping Cart (<span id="cart-sheet-count">0</span>)</h3>
        <button class="close-btn" onclick="closeCart()">✕</button>
      </div>

      <!-- Stepper UI -->
      <div class="checkout-stepper" id="checkout-stepper" style="display: flex; align-items: center; justify-content: space-between; padding: 12px 24px; background: white; border-bottom: 1px solid var(--border);">
        <div style="display: flex; flex-direction: column; align-items: center; width: 60px;">
           <div class="step-circle" id="step-circle-1" style="width: 24px; height: 24px; border-radius: 50%; background: #5C83F6; border: 1px solid #5C83F6; color: white; display: flex; align-items: center; justify-content: center; font-size: 12px; font-weight: bold; margin-bottom: 4px;">1</div>
           <div class="step-label" id="step-label-1" style="font-size: 10px; color: #333; font-weight: bold;">Cart</div>
        </div>
        <div class="step-line" id="step-line-1" style="flex: 1; height: 1px; background: #D1D5DB; margin: 0 4px; margin-top: -14px;"></div>
        <div style="display: flex; flex-direction: column; align-items: center; width: 60px;">
           <div class="step-circle" id="step-circle-2" style="width: 24px; height: 24px; border-radius: 50%; background: white; border: 1px solid #D1D5DB; color: #D1D5DB; display: flex; align-items: center; justify-content: center; font-size: 12px; font-weight: bold; margin-bottom: 4px;">2</div>
           <div class="step-label" id="step-label-2" style="font-size: 10px; color: gray;">Address</div>
        </div>
        <div class="step-line" id="step-line-2" style="flex: 1; height: 1px; background: #D1D5DB; margin: 0 4px; margin-top: -14px;"></div>
        <div style="display: flex; flex-direction: column; align-items: center; width: 60px;">
           <div class="step-circle" id="step-circle-3" style="width: 24px; height: 24px; border-radius: 50%; background: white; border: 1px solid #D1D5DB; color: #D1D5DB; display: flex; align-items: center; justify-content: center; font-size: 12px; font-weight: bold; margin-bottom: 4px;">3</div>
           <div class="step-label" id="step-label-3" style="font-size: 10px; color: gray;">Payment</div>
        </div>
        <div class="step-line" id="step-line-3" style="flex: 1; height: 1px; background: #D1D5DB; margin: 0 4px; margin-top: -14px;"></div>
        <div style="display: flex; flex-direction: column; align-items: center; width: 60px;">
           <div class="step-circle" id="step-circle-4" style="width: 24px; height: 24px; border-radius: 50%; background: white; border: 1px solid #D1D5DB; color: #D1D5DB; display: flex; align-items: center; justify-content: center; font-size: 12px; font-weight: bold; margin-bottom: 4px;">4</div>
           <div class="step-label" id="step-label-4" style="font-size: 10px; color: gray;">Summary</div>
        </div>
      </div>

      <div id="cart-step-1" class="checkout-step">
        <div id="cart-items-container"></div>
      </div>

      <div id="cart-step-2" class="checkout-step" style="display:none; background: #F9FAFB; padding: 16px;">
        <div style="background: white; border-radius: 8px; padding: 16px; box-shadow: 0 1px 2px rgba(0,0,0,0.05);">
            <h4 style="font-size: 16px; margin-bottom: 16px; display: flex; align-items: center; gap: 8px; font-weight: bold;">
                <span style="color:#5C83F6">📍</span> Address
            </h4>
            <div style="font-size: 12px; margin-bottom: 4px; color: #666;">Full Name</div>
            <input type="text" id="addr-name" value="Satish Baldaniya" style="width:100%; margin-bottom: 12px; padding: 10px; border: 1px solid #ccc; border-radius: 6px;">
            <div style="font-size: 12px; margin-bottom: 4px; color: #666;">Mobile Number</div>
            <input type="text" id="addr-phone" value="9876543210" style="width:100%; margin-bottom: 12px; padding: 10px; border: 1px solid #ccc; border-radius: 6px;">
            <div style="font-size: 12px; margin-bottom: 4px; color: #666;">Pincode</div>
            <input type="text" id="addr-pin" value="395006" style="width:100%; margin-bottom: 12px; padding: 10px; border: 1px solid #ccc; border-radius: 6px;">
            <div style="display: flex; gap: 12px; margin-bottom: 12px;">
                <div style="flex:1;">
                    <div style="font-size: 12px; margin-bottom: 4px; color: #666;">City</div>
                    <input type="text" id="addr-city" value="Surat" style="width:100%; padding: 10px; border: 1px solid #ccc; border-radius: 6px;">
                </div>
                <div style="flex:1;">
                    <div style="font-size: 12px; margin-bottom: 4px; color: #666;">State</div>
                    <select id="addr-state" style="width:100%; padding: 10px; border: 1px solid #ccc; border-radius: 6px; background: white;">
                        <option>Gujarat</option>
                        <option>Maharashtra</option>
                        <option>Delhi</option>
                        <option>Karnataka</option>
                    </select>
                </div>
            </div>
            <div style="font-size: 12px; margin-bottom: 4px; color: #666;">House No., Building Name</div>
            <input type="text" id="addr-house" value="Flat 402, Royal Residency" style="width:100%; margin-bottom: 12px; padding: 10px; border: 1px solid #ccc; border-radius: 6px;">
            <div style="font-size: 12px; margin-bottom: 4px; color: #666;">Road name, Area, Colony</div>
            <input type="text" id="addr-road" value="Varachha" style="width:100%; margin-bottom: 12px; padding: 10px; border: 1px solid #ccc; border-radius: 6px;">
        </div>
        <p style="font-size: 11px; text-align: center; color: #666; margin-top: 16px; padding-bottom: 24px;">Secure checkout. Payment is processed through the active payment gateway.</p>
      </div>

      <div id="cart-step-3" class="checkout-step" style="display:none;">
"""

stepper_html_part2 = """
      </div>

      <div id="cart-step-4" class="checkout-step" style="display:none; background: #F9FAFB; padding: 12px;">
        
        <div class="deliver-to-card" style="margin: 0 0 12px 0;">
          <div class="deliver-to-header">
            <div class="deliver-to-title-wrap">
              <span class="deliver-to-pin-icon">📍</span>
              <span style="font-weight: bold; font-size: 14px;">Delivery Address</span>
            </div>
            <button type="button" class="deliver-to-change-btn" onclick="goToStep(2)">Change</button>
          </div>
          <p class="deliver-to-address-line" id="summary-address-text" style="font-size: 12px; color: #666; margin-top: 8px;">Satish Baldaniya • Flat 402, Royal Residency, Varachha, Surat, Gujarat - 395006</p>
        </div>

        <div class="deliver-to-card" style="margin: 0 0 12px 0;">
          <div class="deliver-to-header">
            <div class="deliver-to-title-wrap">
              <span class="deliver-to-pin-icon" style="color: var(--primary);">💳</span>
              <span style="font-weight: bold; font-size: 14px;">Payment Method</span>
            </div>
            <button type="button" class="deliver-to-change-btn" onclick="goToStep(3)">Change</button>
          </div>
          <p class="deliver-to-address-line" id="summary-payment-text" style="font-size: 12px; color: #666; margin-top: 8px;">UPI (Google Pay, PhonePe, Paytm, BHIM)</p>
        </div>

        <!-- Bill Summary Card -->
        <div class="bill-card" style="margin: 0 0 12px 0;">
          <h4 style="font-size: 14px; font-weight: bold; margin-bottom: 12px;">Price Details</h4>
          <div class="bill-row"><span>Total Product MRP</span><span id="bill-subtotal-summary">₹0</span></div>
          <div class="bill-row"><span>Meesho Discount</span><span style="color: var(--rating-green); font-weight: bold;">- ₹0</span></div>
          <div class="bill-row"><span>Delivery Charges</span><span style="color: var(--rating-green); font-weight: bold;">FREE</span></div>
          <div class="bill-row total" style="margin-top: 8px; padding-top: 8px; border-top: 1px solid #eee;"><span>Order Total</span><span id="bill-total-summary" style="color: var(--primary); font-size: 16px;">₹0</span></div>
        </div>

        <div style="display: flex; align-items: center; justify-content: center; gap: 6px; padding: 12px; color: var(--teal); font-size: 11px;">
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path></svg>
            100% Safe & Secure Payments • Verified Sellers
        </div>
      </div>
"""

# Extract the chunks from original HTML
# From: <div class="sheet-header">
# To: <div id="cart-items-container"></div>
# and replace everything up to Bill Summary Card with our separated step divs.

target_start = """      <div class="sheet-header">
        <h3>Shopping Cart (<span id="cart-sheet-count">0</span>)</h3>
        <button class="close-btn" onclick="closeCart()">✕</button>
      </div>

      <div id="cart-items-container"></div>"""

target_end = """      <button class="primary-action-btn" id="place-order-btn" onclick="executeCheckout()">Place Order & Pay Now</button>
    </div>
  </div>"""

# Find Payment Section block to put into cart-step-3
payment_regex = re.compile(r'(<!-- Payment Section - NO COD -->.*?<!-- Bill Summary Card -->)', re.DOTALL)
payment_match = payment_regex.search(content)
payment_html = payment_match.group(1).replace("<!-- Bill Summary Card -->", "")

# We need to construct the full replacement
replacement = stepper_html + payment_html + stepper_html_part2 + """
      <!-- Bottom Fixed Action Bar -->
      <div style="position: sticky; bottom: 0; background: white; padding: 12px 16px; border-top: 1px solid #eee; display: flex; justify-content: space-between; align-items: center; z-index: 10;">
          <div id="bottom-price-info" style="display: flex; flex-direction: column;">
             <span id="bottom-total-price" style="font-size: 18px; font-weight: 900; color: #333;">₹0</span>
             <span style="font-size: 11px; color: var(--primary); font-weight: 600;">View Price Details</span>
          </div>
          <button class="primary-action-btn" id="stepper-continue-btn" onclick="nextCheckoutStep()" style="margin: 0; width: auto; padding: 12px 32px; border-radius: 6px;">Continue</button>
      </div>
    </div>
  </div>"""

# Replace in content
start_idx = content.find(target_start)
end_idx = content.find(target_end) + len(target_end)

new_content = content[:start_idx] + replacement + content[end_idx:]

# Inject JS for stepper
js_script = """
  // Checkout Stepper Logic
  let currentCheckoutStep = 1;
  const totalSteps = 4;

  function updateStepperUI() {
    // Titles
    const titleEl = document.getElementById('cart-sheet-title');
    if (currentCheckoutStep === 1) titleEl.innerHTML = `Shopping Cart (<span id="cart-sheet-count">${cart.length}</span>)`;
    else if (currentCheckoutStep === 2) titleEl.innerText = 'ADD DELIVERY ADDRESS';
    else if (currentCheckoutStep === 3) titleEl.innerText = 'PAYMENT';
    else if (currentCheckoutStep === 4) titleEl.innerText = 'ORDER SUMMARY';

    // Steps rendering
    for (let i = 1; i <= totalSteps; i++) {
        const circle = document.getElementById(`step-circle-${i}`);
        const label = document.getElementById(`step-label-${i}`);
        const line = document.getElementById(`step-line-${i}`);
        const stepContent = document.getElementById(`cart-step-${i}`);
        
        if (circle) {
            if (i < currentCheckoutStep) {
                // Completed
                circle.style.background = '#5C83F6';
                circle.style.borderColor = '#5C83F6';
                circle.style.color = 'white';
                circle.innerHTML = '✔';
                label.style.color = '#333';
                label.style.fontWeight = 'bold';
            } else if (i === currentCheckoutStep) {
                // Active
                circle.style.background = '#5C83F6';
                circle.style.borderColor = '#5C83F6';
                circle.style.color = 'white';
                circle.innerHTML = i;
                label.style.color = '#333';
                label.style.fontWeight = 'bold';
            } else {
                // Pending
                circle.style.background = 'white';
                circle.style.borderColor = '#D1D5DB';
                circle.style.color = '#D1D5DB';
                circle.innerHTML = i;
                label.style.color = 'gray';
                label.style.fontWeight = 'normal';
            }
        }
        
        if (line) {
            if (i < currentCheckoutStep) {
                line.style.background = '#5C83F6';
            } else {
                line.style.background = '#D1D5DB';
            }
        }
        
        // Content visibility
        if (stepContent) {
            stepContent.style.display = (i === currentCheckoutStep) ? 'block' : 'none';
        }
    }

    // Button states
    const btn = document.getElementById('stepper-continue-btn');
    const priceInfo = document.getElementById('bottom-price-info');
    if (currentCheckoutStep === 1) {
        btn.innerText = 'Continue';
        btn.style.width = 'auto';
        priceInfo.style.display = 'flex';
        btn.style.background = 'var(--primary)';
    } else if (currentCheckoutStep === 2) {
        btn.innerText = 'Save Address and Continue';
        btn.style.width = '100%';
        priceInfo.style.display = 'none';
        btn.style.background = '#900C3F';
    } else if (currentCheckoutStep === 3) {
        btn.innerText = 'Continue';
        btn.style.width = 'auto';
        priceInfo.style.display = 'flex';
        btn.style.background = 'var(--primary)';
    } else if (currentCheckoutStep === 4) {
        btn.innerText = 'Place Order';
        btn.style.width = 'auto';
        priceInfo.style.display = 'flex';
        btn.style.background = 'var(--primary)';
    }
  }

  function goToStep(step) {
      if (step >= 1 && step <= totalSteps) {
          currentCheckoutStep = step;
          
          if (step === 4) {
              // Update summary fields
              const name = document.getElementById('addr-name').value;
              const house = document.getElementById('addr-house').value;
              const road = document.getElementById('addr-road').value;
              const city = document.getElementById('addr-city').value;
              const state = document.getElementById('addr-state').value;
              const pin = document.getElementById('addr-pin').value;
              const phone = document.getElementById('addr-phone').value;
              
              document.getElementById('summary-address-text').innerText = `${name} • ${house}, ${road}, ${city}, ${state} - ${pin} • Mobile: ${phone}`;
              
              const selectedPay = document.querySelector('input[name="payment-method"]:checked').parentElement.querySelector('h4').innerText;
              document.getElementById('summary-payment-text').innerText = selectedPay;
          }
          
          updateStepperUI();
      }
  }

  function nextCheckoutStep() {
      if (currentCheckoutStep === 4) {
          executeCheckout();
      } else {
          goToStep(currentCheckoutStep + 1);
      }
  }

  // Override closeCart
  const originalCloseCart = window.closeCart;
  window.closeCart = function() {
      if (currentCheckoutStep > 1) {
          goToStep(currentCheckoutStep - 1);
      } else {
          document.getElementById('cart-sheet').classList.remove('open');
      }
  };
  
  // Expose to window
  window.goToStep = goToStep;
  window.nextCheckoutStep = nextCheckoutStep;
  window.updateStepperUI = updateStepperUI;
"""

# inject JS before </script> at the bottom
script_end_idx = new_content.rfind('</script>')
new_content = new_content[:script_end_idx] + js_script + new_content[script_end_idx:]

# Also override window.openCart slightly so it always starts at step 1 if cart opened from scratch
open_cart_hook = """
  // Hook openCart to reset stepper
  const originalOpenCart = window.openCart;
  window.openCart = function() {
      currentCheckoutStep = 1;
      updateStepperUI();
      document.getElementById('cart-sheet').classList.add('open');
  };
"""
new_content = new_content[:script_end_idx] + open_cart_hook + new_content[script_end_idx:]


with open('index.html', 'w') as f:
    f.write(new_content)

