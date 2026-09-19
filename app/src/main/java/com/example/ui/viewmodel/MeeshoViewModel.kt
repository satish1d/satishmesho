package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.entity.CartItemEntity
import com.example.data.local.entity.OrderEntity
import com.example.data.local.entity.WishlistItemEntity
import com.example.data.model.Banner
import com.example.data.model.Category
import com.example.data.model.Product
import com.example.data.repository.MeeshoRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class Screen {
    object Home : Screen()
    object Categories : Screen()
    object Orders : Screen()
    object Help : Screen()
    object Resell : Screen()
    object Account : Screen()
    data class ProductDetail(val product: Product) : Screen()
    object Cart : Screen()
    data class CheckoutSuccess(val orderId: String, val amount: Int, val paymentMethod: String) : Screen()
}

enum class SortOption {
    RELEVANCE,
    PRICE_LOW_HIGH,
    PRICE_HIGH_LOW,
    RATING
}

class MeeshoViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    val repository = MeeshoRepository(
        database.cartDao(),
        database.wishlistDao(),
        database.orderDao()
    )

    // Navigation State
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Home)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    // Banners & Categories
    val banners: List<Banner> = repository.getBanners()
    val categories: List<Category> = repository.getCategories()
    private val masterProducts: List<Product> = repository.getAllProducts()
    val allProducts: List<Product> get() = masterProducts

    // Search & Filter State
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow("all")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _selectedGender = MutableStateFlow("all")
    val selectedGender: StateFlow<String> = _selectedGender.asStateFlow()

    private val _selectedSubCategory = MutableStateFlow<String?>(null)
    val selectedSubCategory: StateFlow<String?> = _selectedSubCategory.asStateFlow()

    private val _sortOption = MutableStateFlow(SortOption.RELEVANCE)
    val sortOption: StateFlow<SortOption> = _sortOption.asStateFlow()

    private val _filterUnder199 = MutableStateFlow(false)
    val filterUnder199: StateFlow<Boolean> = _filterUnder199.asStateFlow()

    private val _filterUnder299 = MutableStateFlow(false)
    val filterUnder299: StateFlow<Boolean> = _filterUnder299.asStateFlow()

    private val _filterAssuredOnly = MutableStateFlow(false)
    val filterAssuredOnly: StateFlow<Boolean> = _filterAssuredOnly.asStateFlow()

    // Snackbar notifications
    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent.asSharedFlow()

    // Filtered Products
    private val _filteredProducts = MutableStateFlow<List<Product>>(masterProducts)
    val filteredProducts: StateFlow<List<Product>> = _filteredProducts.asStateFlow()

    // Cart, Wishlist, Orders from Room
    val cartItems: StateFlow<List<CartItemEntity>> = repository.cartItems
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val wishlistItems: StateFlow<List<WishlistItemEntity>> = repository.wishlistItems
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val wishlistedIds: StateFlow<List<String>> = repository.wishlistedIds
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val orders: StateFlow<List<OrderEntity>> = repository.orders
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        updateFilteredProducts()
    }

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        updateFilteredProducts()
    }

    fun selectCategory(categoryId: String) {
        _selectedCategory.value = categoryId
        _selectedSubCategory.value = null
        updateFilteredProducts()
    }

    fun selectGender(gender: String) {
        _selectedGender.value = gender
        updateFilteredProducts()
    }

    fun selectSubCategory(subCategory: String?) {
        _selectedSubCategory.value = subCategory
        updateFilteredProducts()
    }

    fun setSortOption(sort: SortOption) {
        _sortOption.value = sort
        updateFilteredProducts()
    }

    fun toggleFilterUnder199() {
        _filterUnder199.value = !_filterUnder199.value
        if (_filterUnder199.value) _filterUnder299.value = false
        updateFilteredProducts()
    }

    fun toggleFilterUnder299() {
        _filterUnder299.value = !_filterUnder299.value
        if (_filterUnder299.value) _filterUnder199.value = false
        updateFilteredProducts()
    }

    fun toggleFilterAssured() {
        _filterAssuredOnly.value = !_filterAssuredOnly.value
        updateFilteredProducts()
    }

    private fun updateFilteredProducts() {
        var list = masterProducts

        // Category filter
        val cat = _selectedCategory.value
        if (cat != "all") {
            list = list.filter { it.category.equals(cat, ignoreCase = true) }
        }

        // Gender filter
        val gender = _selectedGender.value
        if (gender != "all") {
            list = list.filter { p ->
                val t = p.title.lowercase()
                when (gender) {
                    "women" -> p.category == "ethnic" || p.category == "western" || p.category == "jewellery" || p.category == "beauty" || t.contains("women") || t.contains("saree") || t.contains("kurti")
                    "men" -> p.category == "men" || t.contains("men") || t.contains("shirt") || t.contains("kurta")
                    "kids" -> p.category == "kids" || t.contains("kid")
                    "unisex" -> p.category == "home" || p.category == "electronics"
                    else -> true
                }
            }
        }

        // Subcategory filter
        val sub = _selectedSubCategory.value
        if (sub != null && sub != "All Items") {
            list = list.filter { it.subCategory.contains(sub, ignoreCase = true) || it.title.contains(sub, ignoreCase = true) }
        }

        // Search query
        val q = _searchQuery.value.trim()
        if (q.isNotEmpty()) {
            list = list.filter {
                it.title.contains(q, ignoreCase = true) ||
                it.category.contains(q, ignoreCase = true) ||
                it.subCategory.contains(q, ignoreCase = true) ||
                it.description.contains(q, ignoreCase = true) ||
                it.supplierName.contains(q, ignoreCase = true)
            }
        }

        // Quick filters
        if (_filterUnder199.value) {
            list = list.filter { it.price <= 199 }
        } else if (_filterUnder299.value) {
            list = list.filter { it.price <= 299 }
        }

        if (_filterAssuredOnly.value) {
            list = list.filter { it.isMeeshoAssured }
        }

        // Sorting
        list = when (_sortOption.value) {
            SortOption.RELEVANCE -> list
            SortOption.PRICE_LOW_HIGH -> list.sortedBy { it.price }
            SortOption.PRICE_HIGH_LOW -> list.sortedByDescending { it.price }
            SortOption.RATING -> list.sortedByDescending { it.rating }
        }

        _filteredProducts.value = list
    }

    fun addToCart(product: Product, size: String = "Free Size", quantity: Int = 1) {
        viewModelScope.launch {
            repository.addToCart(product, size, quantity)
            _toastEvent.emit("Added to Cart!")
        }
    }

    fun updateCartQuantity(id: Long, qty: Int) {
        viewModelScope.launch {
            repository.updateCartQuantity(id, qty)
        }
    }

    fun updateCartResellPrice(id: Long, customerPrice: Int) {
        viewModelScope.launch {
            repository.updateResellMargin(id, customerPrice)
        }
    }

    fun removeFromCart(id: Long) {
        viewModelScope.launch {
            repository.removeFromCart(id)
            _toastEvent.emit("Item removed from Cart")
        }
    }

    fun toggleWishlist(product: Product) {
        viewModelScope.launch {
            val isWish = wishlistedIds.value.contains(product.id)
            repository.toggleWishlist(product, isWish)
            _toastEvent.emit(if (isWish) "Removed from Wishlist" else "Saved to Wishlist ❤️")
        }
    }

    fun placeOrder(
        items: List<CartItemEntity>,
        paymentMethod: String,
        deliveryAddress: String,
        isResell: Boolean = false,
        customerMargin: Int = 0
    ) {
        viewModelScope.launch {
            val orderId = repository.placeOrder(items, paymentMethod, deliveryAddress, isResell, customerMargin)
            val total = items.sumOf { it.price * it.quantity }
            _currentScreen.value = Screen.CheckoutSuccess(orderId, total, paymentMethod)
            _toastEvent.emit("Order placed successfully! 🎉")
        }
    }
}

class MeeshoViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MeeshoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MeeshoViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
