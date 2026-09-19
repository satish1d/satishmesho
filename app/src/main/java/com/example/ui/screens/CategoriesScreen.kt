package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.model.Category
import com.example.data.model.Product
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
fun CategoriesScreen(
    viewModel: MeeshoViewModel,
    onSelectSubCategory: () -> Unit = {}
) {
    val context = LocalContext.current
    val categories = viewModel.categories.filter { it.id != "all" }
    var selectedCategoryId by remember { mutableStateOf(categories.firstOrNull()?.id ?: "ethnic") }
    val currentCategory = categories.firstOrNull { it.id == selectedCategoryId } ?: categories.first()

    var selectedSubCategory by remember { mutableStateOf<String?>("All Items") }
    var categorySearchQuery by remember { mutableStateOf("") }
    var filterUnder299 by remember { mutableStateOf(false) }
    var filterAssured by remember { mutableStateOf(false) }

    val allProducts = viewModel.allProducts
    val wishlistedIds by viewModel.wishlistedIds.collectAsState()

    // Real-time dynamic filtering for this category
    val displayedProducts by remember(
        selectedCategoryId,
        selectedSubCategory,
        categorySearchQuery,
        filterUnder299,
        filterAssured
    ) {
        derivedStateOf {
            var list = allProducts.filter { it.category.equals(selectedCategoryId, ignoreCase = true) }

            // Subcategory filter
            if (selectedSubCategory != null && selectedSubCategory != "All Items") {
                list = list.filter {
                    it.subCategory.contains(selectedSubCategory!!, ignoreCase = true) ||
                    it.title.contains(selectedSubCategory!!, ignoreCase = true)
                }
            }

            // In-category search query
            val q = categorySearchQuery.trim()
            if (q.isNotEmpty()) {
                list = list.filter {
                    it.title.contains(q, ignoreCase = true) ||
                    it.subCategory.contains(q, ignoreCase = true) ||
                    it.description.contains(q, ignoreCase = true) ||
                    it.fabric.contains(q, ignoreCase = true)
                }
            }

            // Quick filters
            if (filterUnder299) {
                list = list.filter { it.price <= 299 }
            }
            if (filterAssured) {
                list = list.filter { it.isMeeshoAssured }
            }

            list
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("categories_screen")
    ) {
        // Top Global Header for Categories
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 3.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "All Categories",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "${allProducts.size} Trending Products • Lowest Prices Guaranteed",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Surface(
                        color = MeeshoPinkSoft,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Up to 80% OFF",
                            color = MeeshoPink,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        // Two-Pane Content: Left Category Rail & Right Rich Products Browser
        Row(modifier = Modifier.fillMaxSize()) {
            // Left Rail: Category Icons & Badges
            LazyColumn(
                modifier = Modifier
                    .width(96.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    .testTag("category_rail")
            ) {
                items(categories, key = { it.id }) { category ->
                    val isSelected = category.id == selectedCategoryId
                    val backgroundColor by animateColorAsState(
                        targetValue = if (isSelected) MaterialTheme.colorScheme.surface else Color.Transparent,
                        label = "cat_bg"
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedCategoryId = category.id
                                selectedSubCategory = "All Items"
                                categorySearchQuery = ""
                            }
                            .background(backgroundColor)
                            .padding(vertical = 10.dp, horizontal = 4.dp)
                            .testTag("rail_category_${category.id}"),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) MeeshoPinkSoft else MaterialTheme.colorScheme.surface)
                                .border(
                                    width = if (isSelected) 2.dp else 1.dp,
                                    color = if (isSelected) MeeshoPink else MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = when (category.id) {
                                    "ethnic" -> "🥻"
                                    "western" -> "👗"
                                    "men" -> "👔"
                                    "jewellery" -> "💍"
                                    "home" -> "🏠"
                                    "beauty" -> "💄"
                                    "kids" -> "🧸"
                                    "electronics" -> "🎧"
                                    "footwear" -> "👟"
                                    else -> "🛍️"
                                },
                                fontSize = 22.sp
                            )

                            if (category.badge != null) {
                                Surface(
                                    color = MeeshoOrange,
                                    shape = RoundedCornerShape(3.dp),
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(end = 2.dp)
                                ) {
                                    Text(
                                        text = category.badge,
                                        color = Color.White,
                                        fontSize = 7.sp,
                                        fontWeight = FontWeight.Black,
                                        modifier = Modifier.padding(horizontal = 3.dp, vertical = 1.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = category.name,
                            fontSize = 10.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) MeeshoPink else MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center,
                            lineHeight = 12.sp,
                            maxLines = 2
                        )
                    }
                    Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                }
            }

            // Right Pane: Rich Category Info, Subcategory Tabs, and Real-Time Products Grid
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                // Category Banner & Trust Info
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = MeeshoPinkSoft.copy(alpha = 0.6f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = currentCategory.name,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Black,
                                color = MeeshoPinkDark
                            )
                            Surface(
                                color = MeeshoPink,
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "From ₹${currentCategory.startingPrice}",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = currentCategory.highlightInfo,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 13.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        // Top Brands / Suppliers chips
                        Row(
                            modifier = Modifier.horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            currentCategory.topBrands.forEach { brand ->
                                Surface(
                                    color = Color.White,
                                    shape = RoundedCornerShape(4.dp),
                                    border = androidx.compose.foundation.BorderStroke(0.5.dp, MeeshoPink.copy(alpha = 0.3f))
                                ) {
                                    Text(
                                        text = "🏷️ $brand",
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MeeshoPinkDark,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                // In-category Real-Time Search Bar
                OutlinedTextField(
                    value = categorySearchQuery,
                    onValueChange = { categorySearchQuery = it },
                    placeholder = { Text("Search in ${currentCategory.name}...", fontSize = 11.sp) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = MeeshoPink,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    trailingIcon = {
                        if (categorySearchQuery.isNotEmpty()) {
                            IconButton(onClick = { categorySearchQuery = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MeeshoPink,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                        focusedContainerColor = MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                        .height(44.dp)
                        .testTag("category_search_input")
                )

                // Subcategory Filter Chips Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    currentCategory.subcategories.forEach { subcat ->
                        val isSubSelected = (selectedSubCategory == subcat) || (subcat == "All Items" && selectedSubCategory == null)
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = if (isSubSelected) MeeshoPink else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                            modifier = Modifier
                                .clickable {
                                    selectedSubCategory = subcat
                                }
                                .testTag("subcat_chip_${subcat}")
                        ) {
                            Text(
                                text = subcat,
                                fontSize = 11.sp,
                                fontWeight = if (isSubSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSubSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                            )
                        }
                    }
                }

                // Quick Filters: Under 299 & Trusted
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Under 299 filter chip
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (filterUnder299) MeeshoPink else Color.Transparent,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (filterUnder299) MeeshoPink else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                        ),
                        modifier = Modifier.clickable { filterUnder299 = !filterUnder299 }
                    ) {
                        Text(
                            text = "Under ₹299",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (filterUnder299) Color.White else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    // Trusted filter chip
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (filterAssured) MeeshoTeal else Color.Transparent,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (filterAssured) MeeshoTeal else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                        ),
                        modifier = Modifier.clickable { filterAssured = !filterAssured }
                    ) {
                        Text(
                            text = "Trusted Only",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (filterAssured) Color.White else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "${displayedProducts.size} items",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Divider(
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f),
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                // Real-Time Products List
                if (displayedProducts.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "🛍️", fontSize = 36.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "No products found",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Try clearing filters to see all ${currentCategory.name}",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(
                                onClick = {
                                    selectedSubCategory = "All Items"
                                    categorySearchQuery = ""
                                    filterUnder299 = false
                                    filterAssured = false
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = MeeshoPink),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text("Reset Filters", fontSize = 11.sp)
                            }
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp)
                            .testTag("category_products_list"),
                        contentPadding = PaddingValues(bottom = 80.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(displayedProducts, key = { it.id }) { product ->
                            val isWishlisted = wishlistedIds.contains(product.id)
                            CategoryProductItem(
                                product = product,
                                isWishlisted = isWishlisted,
                                onProductClick = {
                                    viewModel.navigateTo(Screen.ProductDetail(product))
                                },
                                onWishlistToggle = {
                                    viewModel.toggleWishlist(product)
                                },
                                onAddToCart = {
                                    viewModel.addToCart(product)
                                    Toast.makeText(context, "Added to cart: ${product.title.take(24)}...", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryProductItem(
    product: Product,
    isWishlisted: Boolean,
    onProductClick: () -> Unit,
    onWishlistToggle: () -> Unit,
    onAddToCart: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onProductClick() }
            .testTag("category_product_${product.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Product Thumbnail Image
            Box(
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                if (product.isMeeshoAssured) {
                    Surface(
                        color = Color.White.copy(alpha = 0.95f),
                        shape = RoundedCornerShape(bottomEnd = 6.dp),
                        modifier = Modifier.align(Alignment.TopStart)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = MeeshoTeal,
                                modifier = Modifier.size(10.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "Trusted",
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold,
                                color = MeeshoTeal
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            // Details Column
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(3.dp))

                // Price & Discount Row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "₹${product.price}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "₹${product.originalPrice}",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textDecoration = TextDecoration.LineThrough
                    )
                    Text(
                        text = "${product.discountPercent}% off",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MeeshoRatingGreen
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                // Rating & Delivery Row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Surface(
                        color = MeeshoRatingGreen,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = "${product.rating}",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(9.dp)
                            )
                        }
                    }

                    Text(
                        text = "Free Delivery",
                        fontSize = 9.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Action Buttons Row: Add to Cart & Wishlist
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = MeeshoPinkSoft,
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.clickable { onAddToCart() }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddShoppingCart,
                                contentDescription = "Add to Cart",
                                tint = MeeshoPink,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Add to Cart",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = MeeshoPink
                            )
                        }
                    }

                    IconButton(
                        onClick = onWishlistToggle,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = if (isWishlisted) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Wishlist",
                            tint = if (isWishlisted) MeeshoPink else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}
