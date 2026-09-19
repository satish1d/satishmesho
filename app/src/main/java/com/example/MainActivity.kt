package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.ui.components.BottomNavBar
import com.example.ui.components.TopBar
import com.example.ui.screens.AccountScreen
import com.example.ui.screens.CartScreen
import com.example.ui.screens.CategoriesScreen
import com.example.ui.screens.CheckoutSuccessScreen
import com.example.ui.screens.HelpScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.OrdersScreen
import com.example.ui.screens.ProductDetailScreen
import com.example.ui.screens.ResellScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.MeeshoViewModel
import com.example.ui.viewmodel.MeeshoViewModelFactory
import com.example.ui.viewmodel.Screen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: MeeshoViewModel by viewModels {
        MeeshoViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                val context = LocalContext.current
                val currentScreen by viewModel.currentScreen.collectAsState()
                val cartItems by viewModel.cartItems.collectAsState()
                val wishlistItems by viewModel.wishlistItems.collectAsState()
                val orders by viewModel.orders.collectAsState()
                val searchQuery by viewModel.searchQuery.collectAsState()

                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                // Toast/Snackbar listener
                LaunchedEffect(Unit) {
                    viewModel.toastEvent.collectLatest { message ->
                        scope.launch {
                            snackbarHostState.showSnackbar(message)
                        }
                    }
                }

                // Android hardware/gesture back press handler
                BackHandler(enabled = currentScreen !is Screen.Home) {
                    when (currentScreen) {
                        is Screen.ProductDetail, is Screen.Cart, is Screen.CheckoutSuccess -> {
                            viewModel.navigateTo(Screen.Home)
                        }
                        is Screen.Categories, is Screen.Orders, is Screen.Help, is Screen.Resell, is Screen.Account -> {
                            viewModel.navigateTo(Screen.Home)
                        }
                        else -> { }
                    }
                }

                val showTopBar = currentScreen is Screen.Home

                val showBottomNav = currentScreen is Screen.Home ||
                        currentScreen is Screen.Categories ||
                        currentScreen is Screen.Orders ||
                        currentScreen is Screen.Help ||
                        currentScreen is Screen.Account

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    topBar = {
                        if (showTopBar) {
                            TopBar(
                                searchQuery = searchQuery,
                                onSearchQueryChange = { viewModel.setSearchQuery(it) },
                                cartItemCount = cartItems.sumOf { it.quantity },
                                wishlistItemCount = wishlistItems.size,
                                onCartClick = { viewModel.navigateTo(Screen.Cart) },
                                onWishlistClick = {
                                    Toast.makeText(context, "Showing Wishlist items", Toast.LENGTH_SHORT).show()
                                },
                                onVoiceSearchClick = {
                                    Toast.makeText(context, "Voice search: Say a product name...", Toast.LENGTH_SHORT).show()
                                },
                                onCameraSearchClick = {
                                    Toast.makeText(context, "Camera search: Scan outfit or product...", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    },
                    bottomBar = {
                        if (showBottomNav) {
                            BottomNavBar(
                                currentScreen = currentScreen,
                                orderCount = orders.size,
                                onNavigate = { screen -> viewModel.navigateTo(screen) }
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        AnimatedContent(
                            targetState = currentScreen,
                            transitionSpec = { fadeIn() togetherWith fadeOut() },
                            label = "ScreenTransition"
                        ) { targetScreen ->
                            when (targetScreen) {
                                is Screen.Home -> {
                                    HomeScreen(
                                        viewModel = viewModel,
                                        onProductClick = { product ->
                                            viewModel.navigateTo(Screen.ProductDetail(product))
                                        }
                                    )
                                }
                                is Screen.Categories -> {
                                    CategoriesScreen(
                                        viewModel = viewModel,
                                        onSelectSubCategory = {
                                            viewModel.navigateTo(Screen.Home)
                                        }
                                    )
                                }
                                is Screen.Orders -> {
                                    OrdersScreen(viewModel = viewModel)
                                }
                                is Screen.Help -> {
                                    HelpScreen(viewModel = viewModel)
                                }
                                is Screen.Resell -> {
                                    ResellScreen(viewModel = viewModel)
                                }
                                is Screen.Account -> {
                                    AccountScreen(viewModel = viewModel)
                                }
                                is Screen.ProductDetail -> {
                                    ProductDetailScreen(
                                        product = targetScreen.product,
                                        viewModel = viewModel,
                                        onBack = { viewModel.navigateTo(Screen.Home) }
                                    )
                                }
                                is Screen.Cart -> {
                                    CartScreen(
                                        viewModel = viewModel,
                                        onBack = { viewModel.navigateTo(Screen.Home) }
                                    )
                                }
                                is Screen.CheckoutSuccess -> {
                                    CheckoutSuccessScreen(
                                        orderId = targetScreen.orderId,
                                        amount = targetScreen.amount,
                                        paymentMethod = targetScreen.paymentMethod,
                                        viewModel = viewModel
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
