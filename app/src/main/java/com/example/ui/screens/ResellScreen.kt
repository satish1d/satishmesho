package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MeeshoOrange
import com.example.ui.theme.MeeshoPink
import com.example.ui.theme.MeeshoPinkDark
import com.example.ui.theme.MeeshoPinkSoft
import com.example.ui.theme.MeeshoRatingGreen
import com.example.ui.theme.MeeshoTeal
import com.example.ui.theme.MeeshoTealLight
import com.example.ui.viewmodel.MeeshoViewModel
import com.example.ui.viewmodel.Screen

@Composable
fun ResellScreen(
    viewModel: MeeshoViewModel
) {
    var productCost by remember { mutableFloatStateOf(300f) }
    var customerPrice by remember { mutableFloatStateOf(500f) }
    var ordersPerMonth by remember { mutableFloatStateOf(40f) }

    val marginPerOrder = (customerPrice - productCost).toInt().coerceAtLeast(0)
    val monthlyEarnings = marginPerOrder * ordersPerMonth.toInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 80.dp)
    ) {
        // Hero Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(MeeshoPink, MeeshoPinkDark)
                    )
                )
                .statusBarsPadding()
                .padding(20.dp)
        ) {
            Column {
                Surface(
                    color = MeeshoOrange,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "ZERO INVESTMENT BUSINESS",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Earn ₹25,000+ / Month\nFrom Home With Meesho",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black,
                    lineHeight = 28.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Join 1.5 Crore+ successful Indian resellers sharing trending catalogs on WhatsApp & social media.",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
            }
        }

        Column(modifier = Modifier.padding(14.dp)) {
            // Interactive Margin Calculator
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("margin_calculator_card")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Calculate,
                            contentDescription = null,
                            tint = MeeshoPink,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Monthly Profit Calculator",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Product Cost Slider
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Meesho Product Cost", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(text = "₹${productCost.toInt()}", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                    Slider(
                        value = productCost,
                        onValueChange = {
                            productCost = it
                            if (customerPrice < it) customerPrice = it + 150
                        },
                        valueRange = 100f..1000f,
                        steps = 18,
                        colors = SliderDefaults.colors(thumbColor = MeeshoPink, activeTrackColor = MeeshoPink)
                    )

                    // Customer Selling Price Slider
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Price you charge Customer", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(text = "₹${customerPrice.toInt()}", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MeeshoPink)
                    }
                    Slider(
                        value = customerPrice,
                        onValueChange = { customerPrice = it },
                        valueRange = productCost..2000f,
                        steps = 20,
                        colors = SliderDefaults.colors(thumbColor = MeeshoPink, activeTrackColor = MeeshoPink)
                    )

                    // Orders per month
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Estimated Orders/Month", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(text = "${ordersPerMonth.toInt()} orders", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                    Slider(
                        value = ordersPerMonth,
                        onValueChange = { ordersPerMonth = it },
                        valueRange = 5f..100f,
                        steps = 19,
                        colors = SliderDefaults.colors(thumbColor = MeeshoPink, activeTrackColor = MeeshoPink)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Estimated Earnings Result Banner
                    Surface(
                        color = MeeshoTealLight,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Your Estimated Monthly Earnings",
                                fontSize = 12.sp,
                                color = MeeshoTeal,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "₹$monthlyEarnings",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Black,
                                color = MeeshoTeal
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "(₹$marginPerOrder margin × ${ordersPerMonth.toInt()} orders)",
                                fontSize = 11.sp,
                                color = MeeshoTeal.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 4 Easy Steps to Resell
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "How Meesho Reselling Works",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    ResellStep(
                        step = "1",
                        icon = Icons.Default.ShoppingBag,
                        title = "Select Hit Catalogs",
                        description = "Browse trending sarees, kurtis, men wear & kitchen products at wholesale factory prices."
                    )
                    ResellStep(
                        step = "2",
                        icon = Icons.Default.Share,
                        title = "Share on WhatsApp & Groups",
                        description = "Share product photos & description with 1-click on your WhatsApp status and contacts."
                    )
                    ResellStep(
                        step = "3",
                        icon = Icons.Default.CurrencyRupee,
                        title = "Add Your Margin & Order",
                        description = "When a customer wants to buy, place the order on Meesho and enter your customer selling price."
                    )
                    ResellStep(
                        step = "4",
                        icon = Icons.Default.AccountBalance,
                        title = "Meesho Delivers & Pays Margin",
                        description = "Meesho delivers package with YOUR name. Your margin profit is transferred directly to your bank account!"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // CTA Button
            Button(
                onClick = { viewModel.navigateTo(Screen.Home) },
                colors = ButtonDefaults.buttonColors(containerColor = MeeshoPink),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("start_reselling_button")
            ) {
                Text(
                    text = "Start Sharing Catalogs Now",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun ResellStep(
    step: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(MeeshoPinkSoft),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = step,
                color = MeeshoPink,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = description,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 15.sp
            )
        }
    }
}
