package com.example.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Headphones
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MeeshoPink
import com.example.ui.theme.MeeshoPinkSoft
import com.example.ui.viewmodel.Screen

@Composable
fun BottomNavBar(
    currentScreen: Screen,
    orderCount: Int,
    onNavigate: (Screen) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .testTag("bottom_nav_bar"),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        // 1. Home
        val isHome = currentScreen is Screen.Home
        NavigationBarItem(
            selected = isHome,
            onClick = { onNavigate(Screen.Home) },
            icon = {
                Icon(
                    imageVector = if (isHome) Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = "Home"
                )
            },
            label = {
                Text(
                    text = "Home",
                    fontSize = 11.sp,
                    fontWeight = if (isHome) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MeeshoPink,
                selectedTextColor = MeeshoPink,
                indicatorColor = MeeshoPinkSoft,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier.testTag("nav_item_home")
        )

        // 2. Categories
        val isCategories = currentScreen is Screen.Categories
        NavigationBarItem(
            selected = isCategories,
            onClick = { onNavigate(Screen.Categories) },
            icon = {
                Icon(
                    imageVector = if (isCategories) Icons.Filled.Category else Icons.Outlined.Category,
                    contentDescription = "Categories"
                )
            },
            label = {
                Text(
                    text = "Categories",
                    fontSize = 11.sp,
                    fontWeight = if (isCategories) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MeeshoPink,
                selectedTextColor = MeeshoPink,
                indicatorColor = MeeshoPinkSoft,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier.testTag("nav_item_categories")
        )

        // 3. My Orders
        val isOrders = currentScreen is Screen.Orders
        NavigationBarItem(
            selected = isOrders,
            onClick = { onNavigate(Screen.Orders) },
            icon = {
                BadgedBox(
                    badge = {
                        if (orderCount > 0) {
                            Badge(
                                containerColor = MeeshoPink,
                                contentColor = Color.White
                            ) {
                                Text(text = "$orderCount", fontSize = 9.sp)
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (isOrders) Icons.Filled.ShoppingBag else Icons.Outlined.ShoppingBag,
                        contentDescription = "My Orders"
                    )
                }
            },
            label = {
                Text(
                    text = "My Orders",
                    fontSize = 11.sp,
                    fontWeight = if (isOrders) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MeeshoPink,
                selectedTextColor = MeeshoPink,
                indicatorColor = MeeshoPinkSoft,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier.testTag("nav_item_orders")
        )

        // 4. Help
        val isHelp = currentScreen is Screen.Help
        NavigationBarItem(
            selected = isHelp,
            onClick = { onNavigate(Screen.Help) },
            icon = {
                Icon(
                    imageVector = if (isHelp) Icons.Filled.Headphones else Icons.Outlined.Headphones,
                    contentDescription = "Help"
                )
            },
            label = {
                Text(
                    text = "Help",
                    fontSize = 11.sp,
                    fontWeight = if (isHelp) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MeeshoPink,
                selectedTextColor = MeeshoPink,
                indicatorColor = MeeshoPinkSoft,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier.testTag("nav_item_help")
        )

        // 5. Account
        val isAccount = currentScreen is Screen.Account
        NavigationBarItem(
            selected = isAccount,
            onClick = { onNavigate(Screen.Account) },
            icon = {
                Icon(
                    imageVector = if (isAccount) Icons.Filled.Person else Icons.Outlined.Person,
                    contentDescription = "Account"
                )
            },
            label = {
                Text(
                    text = "Account",
                    fontSize = 11.sp,
                    fontWeight = if (isAccount) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MeeshoPink,
                selectedTextColor = MeeshoPink,
                indicatorColor = MeeshoPinkSoft,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier.testTag("nav_item_account")
        )
    }
}
