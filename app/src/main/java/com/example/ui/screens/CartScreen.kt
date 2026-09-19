package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.local.entity.CartItemEntity
import com.example.ui.theme.MeeshoPink
import com.example.ui.theme.MeeshoPinkSoft
import com.example.ui.theme.MeeshoTeal
import com.example.ui.theme.MeeshoTealLight
import com.example.ui.viewmodel.MeeshoViewModel
import com.example.ui.viewmodel.Screen

enum class CheckoutStep(val title: String) {
    CART("Cart"),
    ADDRESS("Address"),
    PAYMENT("Payment"),
    SUMMARY("Summary")
}

@Composable
fun CartScreen(
    viewModel: MeeshoViewModel,
    onBack: () -> Unit
) {
    val cartItems by viewModel.cartItems.collectAsState()

    var currentStep by remember { mutableStateOf(CheckoutStep.CART) }

    var isResellOrder by remember { mutableStateOf(false) }
    var customerResellPriceText by remember { mutableStateOf("") }
    
    var selectedPaymentMethod by remember { mutableStateOf("UPI (Google Pay)") }
    var selectedUpiApp by remember { mutableStateOf("Google Pay") }
    
    // Address fields
    var fullName by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("Gujarat") }
    var houseNo by remember { mutableStateOf("") }
    var roadName by remember { mutableStateOf("") }

    val totalAmount = cartItems.sumOf { it.price * it.quantity }
    val totalMrp = cartItems.sumOf { it.originalPrice * it.quantity }
    val totalDiscount = totalMrp - totalAmount

    val customerPrice = customerResellPriceText.toIntOrNull() ?: totalAmount
    val calculatedMargin = if (isResellOrder && customerPrice > totalAmount) customerPrice - totalAmount else 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 8.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    if (currentStep == CheckoutStep.CART) {
                        onBack()
                    } else {
                        currentStep = CheckoutStep.values()[currentStep.ordinal - 1]
                    }
                }, modifier = Modifier.testTag("cart_back_button")) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
                Text(
                    text = when(currentStep) {
                        CheckoutStep.CART -> "Cart (${cartItems.size} items)"
                        CheckoutStep.ADDRESS -> "ADD DELIVERY ADDRESS"
                        CheckoutStep.PAYMENT -> "PAYMENT"
                        CheckoutStep.SUMMARY -> "ORDER SUMMARY"
                    },
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        if (cartItems.isEmpty()) {
            // Empty Cart View
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(MeeshoPinkSoft),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingBag,
                        contentDescription = null,
                        tint = MeeshoPink,
                        modifier = Modifier.size(45.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Your Cart is Empty!",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Browse lowest priced fashion, kitchen and electronics products and add them to your cart.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { viewModel.navigateTo(Screen.Home) },
                    colors = ButtonDefaults.buttonColors(containerColor = MeeshoPink),
                    modifier = Modifier.testTag("cart_shop_now_button")
                ) {
                    Text("Explore Products")
                }
            }
        } else {
            // Stepper
            CheckoutStepper(currentStep)
            Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))

            // Body
            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                when (currentStep) {
                    CheckoutStep.CART -> {
                        LazyColumn(
                            contentPadding = PaddingValues(12.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            item {
                                Surface(
                                    color = MeeshoTealLight,
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = null,
                                            tint = MeeshoTeal,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Yay! You unlocked FREE Delivery & 100% Safe Online Payment",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MeeshoTeal
                                        )
                                    }
                                }
                            }

                            items(cartItems, key = { it.id }) { item ->
                                CartItemRow(
                                    item = item,
                                    onQuantityChange = { newQty -> viewModel.updateCartQuantity(item.id, newQty) },
                                    onRemove = { viewModel.removeFromCart(item.id) }
                                )
                            }

                            item {
                                ResellingCard(
                                    isResellOrder = isResellOrder,
                                    onResellChange = { isResellOrder = it },
                                    customerResellPriceText = customerResellPriceText,
                                    onCustomerPriceChange = { customerResellPriceText = it },
                                    totalAmount = totalAmount,
                                    calculatedMargin = calculatedMargin
                                )
                            }
                        }
                    }
                    CheckoutStep.ADDRESS -> {
                        AddressForm(
                            fullName = fullName, onFullNameChange = { fullName = it },
                            mobileNumber = mobileNumber, onMobileNumberChange = { mobileNumber = it },
                            pincode = pincode, onPincodeChange = { pincode = it },
                            city = city, onCityChange = { city = it },
                            state = state, onStateChange = { state = it },
                            houseNo = houseNo, onHouseNoChange = { houseNo = it },
                            roadName = roadName, onRoadNameChange = { roadName = it }
                        )
                    }
                    CheckoutStep.PAYMENT -> {
                        LazyColumn(
                            contentPadding = PaddingValues(12.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            item {
                                PaymentOptionsCard(
                                    selectedPaymentMethod = selectedPaymentMethod,
                                    onPaymentMethodChange = { selectedPaymentMethod = it },
                                    selectedUpiApp = selectedUpiApp,
                                    onUpiAppChange = { selectedUpiApp = it }
                                )
                            }
                        }
                    }
                    CheckoutStep.SUMMARY -> {
                        LazyColumn(
                            contentPadding = PaddingValues(12.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            item {
                                val fullAddress = "$fullName\n$houseNo, $roadName\n$city, $state - $pincode\nMobile: +91 $mobileNumber"
                                DeliveryAddressCard(address = fullAddress, onChangeClick = { currentStep = CheckoutStep.ADDRESS })
                            }
                            item {
                                SelectedPaymentCard(paymentMethod = selectedPaymentMethod, onChangeClick = { currentStep = CheckoutStep.PAYMENT })
                            }
                            item {
                                PriceSummaryCard(
                                    itemCount = cartItems.size,
                                    totalMrp = totalMrp,
                                    totalDiscount = totalDiscount,
                                    totalAmount = totalAmount
                                )
                            }
                            item {
                                SafeAndSecureBadge()
                            }
                        }
                    }
                }
            }

            // Bottom Bar
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 12.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                if (currentStep == CheckoutStep.ADDRESS) {
                    Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                        Button(
                            onClick = { currentStep = CheckoutStep.PAYMENT },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF900C3F)), // Match deep pink/purple
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.fillMaxWidth().height(48.dp)
                        ) {
                            Text(
                                text = "Save Address and Continue",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "₹$totalAmount",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "View Price Details",
                                fontSize = 11.sp,
                                color = MeeshoPink,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Button(
                            onClick = {
                                when (currentStep) {
                                    CheckoutStep.CART -> currentStep = CheckoutStep.ADDRESS
                                    CheckoutStep.PAYMENT -> currentStep = CheckoutStep.SUMMARY
                                    CheckoutStep.SUMMARY -> {
                                        val addr = "$fullName\n$houseNo, $roadName\n$city, $state - $pincode\nMobile: +91 $mobileNumber"
                                        viewModel.placeOrder(
                                            items = cartItems,
                                            paymentMethod = selectedPaymentMethod,
                                            deliveryAddress = addr,
                                            isResell = isResellOrder,
                                            customerMargin = calculatedMargin
                                        )
                                    }
                                    else -> {}
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MeeshoPink),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .height(48.dp)
                                .testTag("checkout_continue_button")
                        ) {
                            Text(
                                text = if (currentStep == CheckoutStep.SUMMARY) "Place Order" else "Continue",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CheckoutStepper(currentStep: CheckoutStep) {
    val steps = CheckoutStep.values()
    val activeColor = Color(0xFF5C83F6)
    val inactiveColor = Color(0xFFD1D5DB)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 12.dp, horizontal = 24.dp)
    ) {
        // Draw the background lines
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            steps.dropLast(1).forEachIndexed { index, _ ->
                val isCompleted = index < currentStep.ordinal
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(if (isCompleted) activeColor else inactiveColor)
                )
            }
        }

        // Draw the circles and labels
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            steps.forEachIndexed { index, step ->
                val isCompleted = index < currentStep.ordinal
                val isActive = index == currentStep.ordinal

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(60.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(if (isCompleted || isActive) activeColor else Color.White)
                            .border(
                                width = 1.dp,
                                color = if (isCompleted || isActive) activeColor else inactiveColor,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isCompleted) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        } else {
                            Text(
                                text = (index + 1).toString(),
                                color = if (isActive) Color.White else inactiveColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = step.title,
                        fontSize = 10.sp,
                        color = if (isActive || isCompleted) Color.DarkGray else Color.Gray,
                        fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
fun AddressForm(
    fullName: String, onFullNameChange: (String) -> Unit,
    mobileNumber: String, onMobileNumberChange: (String) -> Unit,
    pincode: String, onPincodeChange: (String) -> Unit,
    city: String, onCityChange: (String) -> Unit,
    state: String, onStateChange: (String) -> Unit,
    houseNo: String, onHouseNoChange: (String) -> Unit,
    roadName: String, onRoadNameChange: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF9FAFB)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp),
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 16.dp)) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color(0xFF5C83F6),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Address",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = onFullNameChange,
                        label = { Text("Full Name", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp)
                    )
                    
                    OutlinedTextField(
                        value = mobileNumber,
                        onValueChange = onMobileNumberChange,
                        label = { Text("Mobile Number", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp)
                    )
                    
                    OutlinedTextField(
                        value = pincode,
                        onValueChange = onPincodeChange,
                        label = { Text("Pincode", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp)
                    )
                    
                    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        OutlinedTextField(
                            value = city,
                            onValueChange = onCityChange,
                            label = { Text("City", fontSize = 12.sp) },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp)
                        )
                        OutlinedTextField(
                            value = state,
                            onValueChange = onStateChange,
                            label = { Text("State", fontSize = 12.sp) },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                            readOnly = true
                        )
                    }
                    
                    OutlinedTextField(
                        value = houseNo,
                        onValueChange = onHouseNoChange,
                        label = { Text("House No., Building Name", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp)
                    )
                    
                    OutlinedTextField(
                        value = roadName,
                        onValueChange = onRoadNameChange,
                        label = { Text("Road name, Area, Colony", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Secure checkout. Payment is processed through the active payment gateway.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun ResellingCard(
    isResellOrder: Boolean,
    onResellChange: (Boolean) -> Unit,
    customerResellPriceText: String,
    onCustomerPriceChange: (String) -> Unit,
    totalAmount: Int,
    calculatedMargin: Int
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Reselling this order to a customer?",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Add your margin. Meesho will ship with your name on package!",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = isResellOrder,
                    onCheckedChange = onResellChange,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = MeeshoPink
                    ),
                    modifier = Modifier.testTag("resell_order_switch")
                )
            }

            AnimatedVisibility(visible = isResellOrder) {
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Final Price to Collect from Customer (₹)",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = customerResellPriceText,
                        onValueChange = onCustomerPriceChange,
                        placeholder = { Text("e.g. ₹${totalAmount + 150}") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("resell_customer_price_input")
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Surface(
                        color = MeeshoTealLight,
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = if (calculatedMargin > 0)
                                "Your Resell Margin: ₹$calculatedMargin will be credited to your bank account!"
                            else "Enter a price higher than ₹$totalAmount to earn margin",
                            color = MeeshoTeal,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PaymentOptionsCard(
    selectedPaymentMethod: String,
    onPaymentMethodChange: (String) -> Unit,
    selectedUpiApp: String,
    onUpiAppChange: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Select Payment Method",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Surface(
                    color = MeeshoTealLight,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "100% Secure",
                        color = MeeshoTeal,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Surface(
                color = MeeshoPinkSoft.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = MeeshoPink,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Prepaid only: COD option is disabled. Enjoy instant 1-day priority dispatch & 100% money-back guarantee.",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            PaymentOptionRow(
                title = "UPI (Google Pay, PhonePe, Paytm, BHIM)",
                subtitle = "Instant safe payment directly from your bank • ₹0 Fee",
                isSelected = selectedPaymentMethod.startsWith("UPI"),
                onSelect = { onPaymentMethodChange("UPI ($selectedUpiApp)") }
            )

            AnimatedVisibility(visible = selectedPaymentMethod.startsWith("UPI")) {
                Column(modifier = Modifier.padding(start = 32.dp, bottom = 8.dp)) {
                    Text(
                        text = "Choose UPI App:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        listOf("Google Pay", "PhonePe", "Paytm", "BHIM").forEach { app ->
                            val isAppSelected = selectedUpiApp == app
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isAppSelected) MeeshoPink else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                                modifier = Modifier.clickable {
                                    onUpiAppChange(app)
                                    onPaymentMethodChange("UPI ($app)")
                                }
                            ) {
                                Text(
                                    text = app,
                                    fontSize = 10.sp,
                                    fontWeight = if (isAppSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isAppSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }

            Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))

            PaymentOptionRow(
                title = "Debit / Credit Card",
                subtitle = "Visa, MasterCard, RuPay, Maestro accepted",
                isSelected = selectedPaymentMethod == "Debit / Credit Card",
                onSelect = { onPaymentMethodChange("Debit / Credit Card") }
            )

            Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))

            PaymentOptionRow(
                title = "Net Banking",
                subtitle = "SBI, HDFC, ICICI, Axis & 50+ other Indian banks",
                isSelected = selectedPaymentMethod == "Net Banking",
                onSelect = { onPaymentMethodChange("Net Banking") }
            )

            Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))

            PaymentOptionRow(
                title = "Wallets",
                subtitle = "Paytm Wallet, PhonePe, Amazon Pay",
                isSelected = selectedPaymentMethod == "Wallet",
                onSelect = { onPaymentMethodChange("Wallet") }
            )
        }
    }
}

@Composable
fun DeliveryAddressCard(address: String, onChangeClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = MeeshoPink,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Delivery Address",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Change",
                    color = MeeshoPink,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onChangeClick() }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = address,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
fun SelectedPaymentCard(paymentMethod: String, onChangeClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Payment,
                        contentDescription = null,
                        tint = MeeshoPink,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Payment Method",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Change",
                    color = MeeshoPink,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onChangeClick() }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = paymentMethod,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun PriceSummaryCard(itemCount: Int, totalMrp: Int, totalDiscount: Int, totalAmount: Int) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = "Price Details ($itemCount Items)",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            PriceSummaryRow(label = "Total Product MRP", value = "₹$totalMrp")
            PriceSummaryRow(label = "Meesho Discount", value = "- ₹$totalDiscount", isPositive = true)
            PriceSummaryRow(label = "Delivery Charges", value = "FREE", isPositive = true)

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Order Total",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "₹$totalAmount",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = MeeshoPink
                )
            }
        }
    }
}

@Composable
fun SafeAndSecureBadge() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Security,
            contentDescription = null,
            tint = MeeshoTeal,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "100% Safe & Secure Payments • Verified Sellers",
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CartItemRow(
    item: CartItemEntity,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("cart_item_${item.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = item.title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 2,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = onRemove,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remove",
                            tint = Color(0xFFEF4444),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Size: ${item.size}",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "₹${item.price * item.quantity}",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "₹${item.originalPrice * item.quantity}",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(6.dp))
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    ) {
                        IconButton(
                            onClick = { onQuantityChange(item.quantity - 1) },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(Icons.Default.Remove, contentDescription = "Decrease", modifier = Modifier.size(14.dp))
                        }
                        Text(
                            text = "${item.quantity}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        IconButton(
                            onClick = { onQuantityChange(item.quantity + 1) },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Increase", modifier = Modifier.size(14.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PaymentOptionRow(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(selectedColor = MeeshoPink)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Column {
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            )
            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun PriceSummaryRow(
    label: String,
    value: String,
    isPositive: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isPositive) MeeshoTeal else MaterialTheme.colorScheme.onSurface
        )
    }
}
