package com.example.data.model

data class Product(
    val id: String,
    val title: String,
    val category: String,
    val subCategory: String,
    val price: Int,
    val originalPrice: Int,
    val rating: Float,
    val reviewCount: Int,
    val supplierName: String,
    val supplierRating: Float,
    val isMeeshoAssured: Boolean = true,
    val freeDelivery: Boolean = true,
    val isCodAvailable: Boolean = true,
    val sizes: List<String> = listOf("Free Size", "S", "M", "L", "XL"),
    val colors: List<String> = listOf("Maroon", "Navy Blue", "Bottle Green", "Mustard"),
    val description: String,
    val fabric: String = "Cotton Silk",
    val pattern: String = "Embroidered / Printed",
    val dispatchDays: Int = 1,
    val imageUrl: String
) {
    val discountPercent: Int
        get() = if (originalPrice > price) (((originalPrice - price).toFloat() / originalPrice) * 100).toInt() else 0
}

data class Category(
    val id: String,
    val name: String,
    val iconName: String,
    val badge: String? = null,
    val subcategories: List<String> = emptyList(),
    val startingPrice: Int = 99,
    val discountText: String = "Up to 80% Off",
    val highlightInfo: String = "Direct from Verified Hubs • Free Delivery",
    val topBrands: List<String> = emptyList(),
    val imageUrl: String = ""
)

data class Banner(
    val id: String,
    val title: String,
    val subtitle: String,
    val discountTag: String,
    val bgGradientStart: Long,
    val bgGradientEnd: Long,
    val ctaText: String = "Shop Now",
    val targetCategory: String = "All"
)
