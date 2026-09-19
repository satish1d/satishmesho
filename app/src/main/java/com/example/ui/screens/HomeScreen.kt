package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PublishedWithChanges
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.model.Banner
import com.example.data.model.Category
import com.example.data.model.Product
import com.example.ui.components.ProductCard
import com.example.ui.theme.MeeshoOrange
import com.example.ui.theme.MeeshoPink
import com.example.ui.theme.MeeshoPinkAccent
import com.example.ui.theme.MeeshoPinkSoft
import com.example.ui.theme.MeeshoTeal
import com.example.ui.theme.MeeshoTealLight
import com.example.ui.viewmodel.MeeshoViewModel
import com.example.ui.viewmodel.Screen
import com.example.ui.viewmodel.SortOption
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    viewModel: MeeshoViewModel,
    onProductClick: (Product) -> Unit
) {
    val products by viewModel.filteredProducts.collectAsState()
    val wishlistedIds by viewModel.wishlistedIds.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val sortOption by viewModel.sortOption.collectAsState()
    val filterUnder199 by viewModel.filterUnder199.collectAsState()
    val filterUnder299 by viewModel.filterUnder299.collectAsState()
    val filterAssured by viewModel.filterAssuredOnly.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedGender by viewModel.selectedGender.collectAsState()

    var sortMenuExpanded by remember { mutableStateOf(false) }
    var categoryMenuExpanded by remember { mutableStateOf(false) }
    var genderMenuExpanded by remember { mutableStateOf(false) }
    var filtersMenuExpanded by remember { mutableStateOf(false) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .testTag("home_product_grid"),
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 80.dp, top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Only show Banners & Category row when NOT in active text search
        if (searchQuery.isEmpty()) {
            // 1. Hero Promo Carousel Banner
            item(span = { GridItemSpan(2) }) {
                BannerCarousel(
                    banners = viewModel.banners,
                    onBannerClick = { banner ->
                        viewModel.selectCategory(banner.targetCategory)
                    }
                )
            }

            // 2. Horizontal Category Bubbles
            item(span = { GridItemSpan(2) }) {
                CategoryStrip(
                    categories = viewModel.categories,
                    selectedCategoryId = selectedCategory,
                    onSelectCategory = { catId -> viewModel.selectCategory(catId) }
                )
            }

            // 3. Meesho Trust & Value Guarantee Strip
            item(span = { GridItemSpan(2) }) {
                TrustGuaranteeStrip()
            }
        }

        // 4. Circled Meesho Filter Bar: Sort | Category | Gender | Filters
        item(span = { GridItemSpan(2) }) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE7E8EB))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 1. Sort
                        Box(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable { sortMenuExpanded = true }
                                    .testTag("sort_filter_btn"),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.FilterList,
                                    contentDescription = "Sort",
                                    tint = if (sortOption != SortOption.RELEVANCE) MeeshoPink else Color(0xFF333333),
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Sort",
                                    fontSize = 13.sp,
                                    fontWeight = if (sortOption != SortOption.RELEVANCE) FontWeight.Bold else FontWeight.Medium,
                                    color = if (sortOption != SortOption.RELEVANCE) MeeshoPink else Color(0xFF333333)
                                )
                            }
                            DropdownMenu(
                                expanded = sortMenuExpanded,
                                onDismissRequest = { sortMenuExpanded = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Relevance (Popular)") },
                                    onClick = {
                                        viewModel.setSortOption(SortOption.RELEVANCE)
                                        sortMenuExpanded = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Price: Low to High") },
                                    onClick = {
                                        viewModel.setSortOption(SortOption.PRICE_LOW_HIGH)
                                        sortMenuExpanded = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Price: High to Low") },
                                    onClick = {
                                        viewModel.setSortOption(SortOption.PRICE_HIGH_LOW)
                                        sortMenuExpanded = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Rating: High to Low") },
                                    onClick = {
                                        viewModel.setSortOption(SortOption.RATING)
                                        sortMenuExpanded = false
                                    }
                                )
                            }
                        }

                        Box(modifier = Modifier.width(1.dp).height(24.dp).background(Color(0xFFEDF0F2)))

                        // 2. Category
                        Box(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable { categoryMenuExpanded = true }
                                    .testTag("category_filter_btn"),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = if (selectedCategory == "all") "Category" else selectedCategory.replaceFirstChar { it.uppercase() },
                                    fontSize = 13.sp,
                                    fontWeight = if (selectedCategory != "all") FontWeight.Bold else FontWeight.Medium,
                                    color = if (selectedCategory != "all") MeeshoPink else Color(0xFF333333)
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = null,
                                    tint = if (selectedCategory != "all") MeeshoPink else Color(0xFF666666),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            DropdownMenu(
                                expanded = categoryMenuExpanded,
                                onDismissRequest = { categoryMenuExpanded = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("All Categories") },
                                    onClick = {
                                        viewModel.selectCategory("all")
                                        categoryMenuExpanded = false
                                    }
                                )
                                viewModel.categories.forEach { cat ->
                                    DropdownMenuItem(
                                        text = { Text(cat.name) },
                                        onClick = {
                                            viewModel.selectCategory(cat.id)
                                            categoryMenuExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        Box(modifier = Modifier.width(1.dp).height(24.dp).background(Color(0xFFEDF0F2)))

                        // 3. Gender
                        Box(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable { genderMenuExpanded = true }
                                    .testTag("gender_filter_btn"),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = when (selectedGender) {
                                        "women" -> "Women"
                                        "men" -> "Men"
                                        "kids" -> "Kids"
                                        else -> "Gender"
                                    },
                                    fontSize = 13.sp,
                                    fontWeight = if (selectedGender != "all") FontWeight.Bold else FontWeight.Medium,
                                    color = if (selectedGender != "all") MeeshoPink else Color(0xFF333333)
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = null,
                                    tint = if (selectedGender != "all") MeeshoPink else Color(0xFF666666),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            DropdownMenu(
                                expanded = genderMenuExpanded,
                                onDismissRequest = { genderMenuExpanded = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("All Genders") },
                                    onClick = {
                                        viewModel.selectGender("all")
                                        genderMenuExpanded = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Women") },
                                    onClick = {
                                        viewModel.selectGender("women")
                                        genderMenuExpanded = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Men") },
                                    onClick = {
                                        viewModel.selectGender("men")
                                        genderMenuExpanded = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Kids") },
                                    onClick = {
                                        viewModel.selectGender("kids")
                                        genderMenuExpanded = false
                                    }
                                )
                            }
                        }

                        Box(modifier = Modifier.width(1.dp).height(24.dp).background(Color(0xFFEDF0F2)))

                        // 4. Filters
                        Box(modifier = Modifier.weight(1f)) {
                            val activeCount = (if (filterUnder199) 1 else 0) +
                                    (if (filterUnder299) 1 else 0) +
                                    (if (filterAssured) 1 else 0)
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable { filtersMenuExpanded = true }
                                    .testTag("all_filters_btn"),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PublishedWithChanges,
                                    contentDescription = "Filters",
                                    tint = if (activeCount > 0) MeeshoPink else Color(0xFF333333),
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (activeCount > 0) "Filters ($activeCount)" else "Filters",
                                    fontSize = 13.sp,
                                    fontWeight = if (activeCount > 0) FontWeight.Bold else FontWeight.Medium,
                                    color = if (activeCount > 0) MeeshoPink else Color(0xFF333333)
                                )
                            }
                            DropdownMenu(
                                expanded = filtersMenuExpanded,
                                onDismissRequest = { filtersMenuExpanded = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text(if (filterUnder199) "✓ Under ₹199" else "Under ₹199") },
                                    onClick = { viewModel.toggleFilterUnder199() }
                                )
                                DropdownMenuItem(
                                    text = { Text(if (filterUnder299) "✓ Under ₹299" else "Under ₹299") },
                                    onClick = { viewModel.toggleFilterUnder299() }
                                )
                                DropdownMenuItem(
                                    text = { Text(if (filterAssured) "✓ M-Trusted Only" else "M-Trusted Only") },
                                    onClick = { viewModel.toggleFilterAssured() }
                                )
                                DropdownMenuItem(
                                    text = { Text("Reset Filters") },
                                    onClick = {
                                        if (filterUnder199) viewModel.toggleFilterUnder199()
                                        if (filterUnder299) viewModel.toggleFilterUnder299()
                                        if (filterAssured) viewModel.toggleFilterAssured()
                                        filtersMenuExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                // Results count and active filter indicator
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (searchQuery.isNotEmpty()) "Search results for \"$searchQuery\" (${products.size})"
                               else "Products For You (${products.size})",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (selectedCategory != "all" || filterUnder199 || filterUnder299 || filterAssured || searchQuery.isNotEmpty()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable {
                                    viewModel.selectCategory("all")
                                    viewModel.setSearchQuery("")
                                    if (filterUnder199) viewModel.toggleFilterUnder199()
                                    if (filterUnder299) viewModel.toggleFilterUnder299()
                                    if (filterAssured) viewModel.toggleFilterAssured()
                                }
                                .padding(4.dp)
                        ) {
                            Text(
                                text = "Clear filters",
                                color = MeeshoPink,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear",
                                tint = MeeshoPink,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }
        }

        // 5. Empty State (if no products matched)
        if (products.isEmpty()) {
            item(span = { GridItemSpan(2) }) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, bottom = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(MeeshoPinkSoft),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingBag,
                            contentDescription = null,
                            tint = MeeshoPink,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No products found",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Try searching with different keywords or clearing filters",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            viewModel.selectCategory("all")
                            viewModel.setSearchQuery("")
                            if (filterUnder199) viewModel.toggleFilterUnder199()
                            if (filterUnder299) viewModel.toggleFilterUnder299()
                            if (filterAssured) viewModel.toggleFilterAssured()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MeeshoPink)
                    ) {
                        Text("Reset All Filters")
                    }
                }
            }
        }

        // 6. Product Grid Cards
        items(products, key = { it.id }) { product ->
            val isWish = wishlistedIds.contains(product.id)
            ProductCard(
                product = product,
                isWishlisted = isWish,
                onProductClick = onProductClick,
                onWishlistToggle = { viewModel.toggleWishlist(product) }
            )
        }
    }
}

@Composable
private fun BannerCarousel(
    banners: List<Banner>,
    onBannerClick: (Banner) -> Unit
) {
    var activeIndex by remember { mutableIntStateOf(0) }

    // Auto rotate banners every 4 seconds
    LaunchedEffect(banners) {
        while (true) {
            delay(4000)
            if (banners.isNotEmpty()) {
                activeIndex = (activeIndex + 1) % banners.size
            }
        }
    }

    val currentBanner = banners.getOrNull(activeIndex) ?: return

    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(14.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(145.dp)
                .clickable { onBannerClick(currentBanner) }
                .testTag("promo_banner")
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color(currentBanner.bgGradientStart),
                                Color(currentBanner.bgGradientEnd)
                            )
                        )
                    )
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxWidth(0.72f)
                ) {
                    Surface(
                        color = MeeshoOrange,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = currentBanner.discountTag,
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = currentBanner.title,
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Black,
                        lineHeight = 20.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = currentBanner.subtitle,
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 11.sp,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            text = currentBanner.ctaText,
                            color = Color(currentBanner.bgGradientStart),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }

                // Decorative graphic element on right
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.CenterEnd)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "₹99*",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }

        // Indicator dots
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            banners.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(width = if (index == activeIndex) 16.dp else 6.dp, height = 6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(
                            if (index == activeIndex) MeeshoPink else MaterialTheme.colorScheme.outline
                        )
                )
            }
        }
    }
}

@Composable
private fun CategoryStrip(
    categories: List<Category>,
    selectedCategoryId: String,
    onSelectCategory: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(categories) { category ->
            val isSelected = category.id == selectedCategoryId
            val archShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomStart = 12.dp, bottomEnd = 12.dp)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(66.dp)
                    .clickable { onSelectCategory(category.id) }
                    .testTag("category_${category.id}")
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 64.dp, height = 72.dp)
                        .clip(archShape)
                        .background(if (isSelected) MeeshoPink.copy(alpha = 0.15f) else Color(0xFFFAEEF3))
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = if (isSelected) MeeshoPink else Color(0xFFF1D4E0),
                            shape = archShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (category.imageUrl.isNotBlank()) {
                        AsyncImage(
                            model = category.imageUrl,
                            contentDescription = category.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        // Category Letter / Emoji Graphic
                        Text(
                            text = when (category.id) {
                                "all" -> "🔥"
                                "ethnic" -> "🥻"
                                "western" -> "👗"
                                "men" -> "👔"
                                "jewellery" -> "💍"
                                "home" -> "🏺"
                                "beauty" -> "💄"
                                "kids" -> "🧸"
                                "electronics" -> "🎧"
                                "footwear" -> "👟"
                                "accessories" -> "👜"
                                "grocery" -> "🧺"
                                else -> "🛍️"
                            },
                            fontSize = 24.sp
                        )
                    }

                    // Badge if any
                    category.badge?.let { badge ->
                        Surface(
                            color = MeeshoPinkAccent,
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(2.dp)
                        ) {
                            Text(
                                text = badge,
                                color = Color.White,
                                fontSize = 7.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 3.dp, vertical = 1.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = category.name,
                    fontSize = 11.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) MeeshoPink else MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
private fun TrustGuaranteeStrip() {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TrustItem(icon = Icons.Default.Savings, text = "Lowest Prices")
            Text("•", color = MaterialTheme.colorScheme.onSurfaceVariant)
            TrustItem(icon = Icons.Default.LocalShipping, text = "Free Delivery")
            Text("•", color = MaterialTheme.colorScheme.onSurfaceVariant)
            TrustItem(icon = Icons.Default.Payments, text = "Safe Online Pay")
            Text("•", color = MaterialTheme.colorScheme.onSurfaceVariant)
            TrustItem(icon = Icons.Default.PublishedWithChanges, text = "7-Day Returns")
        }
    }
}

@Composable
private fun TrustItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MeeshoTeal,
            modifier = Modifier.size(13.dp)
        )
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
