package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PublishedWithChanges
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

data class FaqItem(
    val id: Int,
    val category: String,
    val question: String,
    val answer: String
)

@Composable
fun HelpScreen(
    viewModel: MeeshoViewModel
) {
    val context = LocalContext.current
    val orders by viewModel.orders.collectAsState()
    var showChatDialog by remember { mutableStateOf(false) }
    var expandedFaqId by remember { mutableIntStateOf(-1) }

    val faqs = remember {
        listOf(
            FaqItem(
                id = 1,
                category = "Orders & Delivery",
                question = "How can I track my Meesho package in real-time?",
                answer = "Go to 'My Orders' from the bottom menu and tap on your order. You can view the live status from Order Placed, Shipped from Surat Hub, to Out for Delivery with the delivery partner details."
            ),
            FaqItem(
                id = 2,
                category = "Returns & Exchanges",
                question = "What is Meesho's 7-Day Easy Return Policy?",
                answer = "If you are not satisfied with size, color, or quality, you can request a return within 7 days of delivery. Our courier partner will pick up the item from your doorstep for FREE with zero pickup charges."
            ),
            FaqItem(
                id = 3,
                category = "Refunds & Payments",
                question = "How do I receive my refund for returned orders?",
                answer = "Since all orders are prepaid via secure online payment (UPI, Card, Net Banking), your refund is automatically credited back to your original payment source within 24 hours of courier pickup."
            ),
            FaqItem(
                id = 4,
                category = "Payments",
                question = "Why is Cash on Delivery (COD) not available?",
                answer = "Cash on Delivery is disabled to prevent fake/unverified orders and ensure faster 1-day priority dispatch. All orders are protected by 100% Buyer Protection and hassle-free return policy."
            ),
            FaqItem(
                id = 5,
                category = "Reselling Margin",
                question = "When is my Reselling Margin deposited into my bank account?",
                answer = "For orders placed with your custom reseller margin, the profit is deposited into your registered bank account 7 days after customer delivery (after the return period expires)."
            )
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("help_screen"),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 80.dp)
    ) {
        // Hero Header
        item {
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = MeeshoTeal,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.SupportAgent,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "24x7 CUSTOMER SUPPORT",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }

                        Surface(
                            color = Color.White.copy(alpha = 0.2f),
                            shape = CircleShape
                        ) {
                            Text(
                                text = "Toll-Free",
                                color = Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "How Can We Help You Today?",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black,
                        lineHeight = 28.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Get instant help with your orders, returns, refunds, payments, and deliveries.",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )
                }
            }
        }

        // Quick Contact Cards Row (Call, Chat, Email)
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Call Support
                SupportActionCard(
                    icon = Icons.Default.Call,
                    title = "Call Us",
                    subtitle = "1800 123 4567",
                    badge = "24x7 Free",
                    color = MeeshoTeal,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        Toast.makeText(context, "Dialing Meesho Helpline: 1800 123 4567", Toast.LENGTH_SHORT).show()
                    }
                )

                // Chat Support
                SupportActionCard(
                    icon = Icons.Default.Chat,
                    title = "Live Chat",
                    subtitle = "Instant Reply",
                    badge = "Fastest",
                    color = MeeshoPink,
                    modifier = Modifier.weight(1f),
                    onClick = { showChatDialog = true }
                )

                // Email Support
                SupportActionCard(
                    icon = Icons.Default.Email,
                    title = "Email Us",
                    subtitle = "help@meesho",
                    badge = "< 2 hours",
                    color = MeeshoOrange,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        Toast.makeText(context, "Email support: help@meesho.com", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

        // Section: Recent Order Help
        if (orders.isNotEmpty()) {
            item {
                val latestOrder = orders.first()
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Help with Recent Order",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Surface(
                                color = MeeshoTealLight,
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = latestOrder.deliveryStatus,
                                    color = MeeshoTeal,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Order #${latestOrder.orderId} • ${latestOrder.summaryTitle}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(
                                onClick = { viewModel.navigateTo(Screen.Orders) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Track Package", fontSize = 11.sp)
                            }
                            Button(
                                onClick = {
                                    Toast.makeText(context, "Return request initiated for Order #${latestOrder.orderId}", Toast.LENGTH_LONG).show()
                                },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MeeshoPink)
                            ) {
                                Text("Return / Exchange", fontSize = 11.sp)
                            }
                        }
                    }
                }
            }
        }

        // Section Title: FAQs
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 14.dp, top = 14.dp, bottom = 8.dp)
            ) {
                Text(
                    text = "Frequently Asked Questions",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Quick solutions to common queries",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // FAQ Items
        items(faqs, key = { it.id }) { faq ->
            val isExpanded = expandedFaqId == faq.id
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 4.dp)
                    .clickable {
                        expandedFaqId = if (isExpanded) -1 else faq.id
                    }
                    .testTag("faq_item_${faq.id}")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = faq.question,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isExpanded) MeeshoPink else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = if (isExpanded) "Collapse" else "Expand",
                            tint = if (isExpanded) MeeshoPink else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    AnimatedVisibility(visible = isExpanded) {
                        Column(modifier = Modifier.padding(top = 8.dp)) {
                            Divider()
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = faq.answer,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        }

        // Trust Strip at bottom of Help Screen
        item {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MeeshoTealLight),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = null,
                            tint = MeeshoTeal,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Meesho Buyer Protection Promise",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MeeshoTeal
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "• 100% money back guarantee on defective or wrong products.\n• Zero fee for doorstep returns pickup.\n• Safe payments with RBI-authorized Indian banking gateways.",
                        fontSize = 11.sp,
                        color = MeeshoTeal,
                        lineHeight = 17.sp
                    )
                }
            }
        }
    }

    // Interactive Live Chat Dialog
    if (showChatDialog) {
        LiveChatAssistantDialog(onDismiss = { showChatDialog = false })
    }
}

@Composable
private fun SupportActionCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    badge: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .clickable { onClick() }
            .testTag("support_action_${title.lowercase().replace(" ", "_")}")
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = color,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                color = color.copy(alpha = 0.15f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = badge,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    color = color,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                )
            }
        }
    }
}

@Composable
private fun LiveChatAssistantDialog(
    onDismiss: () -> Unit
) {
    val messages = remember {
        mutableStateListOf(
            "Hello! Welcome to Meesho 24x7 Support Assistant.",
            "Please select what you need assistance with:"
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(MeeshoPink),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.SupportAgent,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(text = "Meesho Assistant", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Online • Instant Support", fontSize = 10.sp, color = MeeshoTeal)
                }
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                messages.forEach { msg ->
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = msg,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Quick Choices:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))

                val choices = listOf(
                    "Track my latest order status" to "Your order is dispatched from Surat Hub and will be delivered in 3 days!",
                    "Return / Refund procedure" to "Returns are accepted within 7 days. Free doorstep pickup will be arranged.",
                    "Talk to human executive" to "Connecting you to an agent... Please wait a moment or call 1800 123 4567."
                )

                choices.forEach { (option, reply) ->
                    Surface(
                        color = MeeshoPinkSoft,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                messages.add("User: $option")
                                messages.add("Agent: $reply")
                            }
                            .padding(vertical = 3.dp)
                    ) {
                        Text(
                            text = option,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MeeshoPink,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = MeeshoPink)
            ) {
                Text("Close")
            }
        }
    )
}
