package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Wallet
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MeeshoPink
import com.example.ui.theme.MeeshoPinkSoft
import com.example.ui.theme.MeeshoRatingGreen
import com.example.ui.theme.MeeshoTeal
import com.example.ui.theme.MeeshoTealLight
import com.example.ui.viewmodel.MeeshoViewModel
import com.example.ui.viewmodel.Screen

@Composable
fun AccountScreen(
    viewModel: MeeshoViewModel
) {
    var showLanguageDialog by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("English") }
    var showHelpDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 80.dp)
    ) {
        // Top Profile Card
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(MeeshoPink),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "SB",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Satish Baldaniya",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = "Verified",
                                tint = MeeshoTeal,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Text(
                            text = "satishbaldaniyaa@gmail.com",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "+91 98765 43210 • Surat, Gujarat",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Meesho Balance / Wallet Card
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .testTag("wallet_balance_card")
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(MeeshoTealLight),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Wallet,
                            contentDescription = null,
                            tint = MeeshoTeal,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Meesho Balance",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "₹250",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            color = MeeshoTeal
                        )
                    }
                }

                Surface(
                    color = MeeshoPinkSoft,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.clickable { }
                ) {
                    Text(
                        text = "Bank Details",
                        color = MeeshoPink,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Menu Section 1: Orders & Wishlist
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
        ) {
            Column {
                AccountMenuItem(
                    icon = Icons.Default.ShoppingBag,
                    title = "My Orders",
                    subtitle = "Track orders, cancel & return packages",
                    onClick = { viewModel.navigateTo(Screen.Orders) }
                )
                Divider()
                AccountMenuItem(
                    icon = Icons.Default.Favorite,
                    title = "My Wishlist",
                    subtitle = "Saved items to buy later",
                    onClick = { viewModel.navigateTo(Screen.Home) }
                )
                Divider()
                AccountMenuItem(
                    icon = Icons.Default.Share,
                    title = "Shared Catalogs",
                    subtitle = "Catalogs you shared on WhatsApp",
                    onClick = { viewModel.navigateTo(Screen.Resell) }
                )
                Divider()
                AccountMenuItem(
                    icon = Icons.Default.AccountBalance,
                    title = "My Bank & UPI Details",
                    subtitle = "Account details for reseller margin deposit",
                    onClick = { }
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Menu Section 2: Support & Settings
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
        ) {
            Column {
                AccountMenuItem(
                    icon = Icons.Default.Language,
                    title = "Change Language",
                    subtitle = "Currently: $selectedLanguage",
                    onClick = { showLanguageDialog = true }
                )
                Divider()
                AccountMenuItem(
                    icon = Icons.Default.Headphones,
                    title = "Help Center & 24x7 Support",
                    subtitle = "Instant resolution for orders, refunds & delivery",
                    onClick = { viewModel.navigateTo(Screen.Help) }
                )
                Divider()
                AccountMenuItem(
                    icon = Icons.Default.Security,
                    title = "Legal and Policies",
                    subtitle = "Terms, Privacy Policy, Return Guidelines",
                    onClick = { }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Meesho Clone v1.0 • Made with Jetpack Compose & Room",
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }

    // Language Selector Dialog
    if (showLanguageDialog) {
        AlertDialog(
            onDismissRequest = { showLanguageDialog = false },
            title = { Text("Choose App Language") },
            text = {
                Column {
                    val languages = listOf("English", "हिन्दी (Hindi)", "ગુજરાતી (Gujarati)", "मराठी (Marathi)", "বাংলা (Bengali)", "தமிழ் (Tamil)", "తెలుగు (Telugu)")
                    languages.forEach { lang ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedLanguage = lang
                                    showLanguageDialog = false
                                }
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = lang,
                                fontSize = 14.sp,
                                fontWeight = if (selectedLanguage == lang) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedLanguage == lang) MeeshoPink else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showLanguageDialog = false }) {
                    Text("Close", color = MeeshoPink)
                }
            }
        )
    }

    // Help Center Dialog
    if (showHelpDialog) {
        AlertDialog(
            onDismissRequest = { showHelpDialog = false },
            title = { Text("Meesho 24x7 Customer Support") },
            text = {
                Column {
                    Text(
                        text = "Need help with an order, return, refund or bank margin transfer?",
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Surface(
                        color = MeeshoTealLight,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "Toll-Free Helpline: 1800 123 4567",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = MeeshoTeal
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Email: help@meesho.com • Instant Chat Support",
                                fontSize = 11.sp,
                                color = MeeshoTeal
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showHelpDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = MeeshoPink)
                ) {
                    Text("Got It")
                }
            }
        )
    }
}

@Composable
private fun AccountMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MeeshoPink,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(18.dp)
        )
    }
}
