package com.example.data.repository

import com.example.data.local.dao.CartDao
import com.example.data.local.dao.OrderDao
import com.example.data.local.dao.WishlistDao
import com.example.data.local.entity.CartItemEntity
import com.example.data.local.entity.OrderEntity
import com.example.data.local.entity.WishlistItemEntity
import com.example.data.model.Banner
import com.example.data.model.Category
import com.example.data.model.Product
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MeeshoRepository(
    private val cartDao: CartDao,
    private val wishlistDao: WishlistDao,
    private val orderDao: OrderDao
) {
    // Cart Flow
    val cartItems: Flow<List<CartItemEntity>> = cartDao.getAllCartItems()

    suspend fun addToCart(product: Product, size: String = "Free Size", quantity: Int = 1) {
        val existing = cartDao.findByProductAndSize(product.id, size)
        if (existing != null) {
            cartDao.updateQuantity(existing.id, existing.quantity + quantity)
        } else {
            cartDao.insertItem(
                CartItemEntity(
                    productId = product.id,
                    title = product.title,
                    imageUrl = product.imageUrl,
                    price = product.price,
                    originalPrice = product.originalPrice,
                    size = size,
                    quantity = quantity,
                    customerResellPrice = product.price, // Default no margin
                    category = product.category
                )
            )
        }
    }

    suspend fun updateCartQuantity(id: Long, quantity: Int) {
        if (quantity <= 0) {
            cartDao.deleteItemById(id)
        } else {
            cartDao.updateQuantity(id, quantity)
        }
    }

    suspend fun updateResellMargin(id: Long, customerPrice: Int) {
        cartDao.updateResellPrice(id, customerPrice)
    }

    suspend fun removeFromCart(id: Long) {
        cartDao.deleteItemById(id)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }

    // Wishlist Flow
    val wishlistItems: Flow<List<WishlistItemEntity>> = wishlistDao.getAllWishlistItems()
    val wishlistedIds: Flow<List<String>> = wishlistDao.getAllWishlistedProductIds()

    suspend fun toggleWishlist(product: Product, isCurrentlyWishlisted: Boolean) {
        if (isCurrentlyWishlisted) {
            wishlistDao.deleteByProductId(product.id)
        } else {
            wishlistDao.insertItem(
                WishlistItemEntity(
                    productId = product.id,
                    title = product.title,
                    imageUrl = product.imageUrl,
                    price = product.price,
                    originalPrice = product.originalPrice,
                    rating = product.rating,
                    reviewCount = product.reviewCount,
                    category = product.category
                )
            )
        }
    }

    // Orders Flow
    val orders: Flow<List<OrderEntity>> = orderDao.getAllOrders()

    suspend fun placeOrder(
        items: List<CartItemEntity>,
        paymentMethod: String,
        deliveryAddress: String,
        isResell: Boolean = false,
        customerMargin: Int = 0
    ): String {
        val orderId = "SHOPN" + System.currentTimeMillis().toString().takeLast(8)
        val total = items.sumOf { it.price * it.quantity }
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val deliveryDate = SimpleDateFormat("EEE, dd MMM", Locale.getDefault())
            .format(Date(System.currentTimeMillis() + (4 * 24 * 60 * 60 * 1000L)))

        val primaryItem = items.firstOrNull()
        val summaryTitle = if (items.size > 1) {
            "${primaryItem?.title ?: "Meesho Order"} + ${items.size - 1} more items"
        } else {
            primaryItem?.title ?: "Meesho Order"
        }

        val order = OrderEntity(
            orderId = orderId,
            orderDate = System.currentTimeMillis(),
            totalAmount = total,
            itemCount = items.sumOf { it.quantity },
            paymentMethod = paymentMethod,
            deliveryAddress = deliveryAddress,
            deliveryStatus = "Order Placed",
            estimatedDeliveryDate = deliveryDate,
            summaryTitle = summaryTitle,
            summaryImageUrl = primaryItem?.imageUrl ?: "",
            isResellOrder = isResell,
            customerMargin = customerMargin
        )

        orderDao.insertOrder(order)
        cartDao.clearCart()
        return orderId
    }

    // Static Categories with rich supplier, discount and style info
    fun getCategories(): List<Category> = listOf(
        Category(
            id = "all",
            name = "All",
            iconName = "all",
            badge = null,
            subcategories = listOf("All Items", "Deals of the Day"),
            startingPrice = 99,
            discountText = "Up to 80% Off",
            highlightInfo = "India's Lowest Prices Across All Departments",
            topBrands = listOf("Meesho Assured", "Top Rated", "Best Sellers")
        ),
        Category(
            id = "ethnic",
            name = "Ethnic Wear",
            iconName = "saree",
            badge = "HOT",
            subcategories = listOf("All Items", "Sarees", "Kurtis", "Lehengas", "Suits", "Dupattas"),
            startingPrice = 149,
            discountText = "Min 60% Off",
            highlightInfo = "Direct from Surat, Varanasi & Jaipur Weavers • 7-Day Returns",
            topBrands = listOf("Surat Silk Mills", "Jaipur Hub", "Banarasi Heritage"),
            imageUrl = "https://images.unsplash.com/photo-1610030469983-98e550d6193c?w=400&auto=format&fit=crop&q=80"
        ),
        Category(
            id = "western",
            name = "Western Dresses",
            iconName = "dress",
            badge = null,
            subcategories = listOf("All Items", "Dresses", "Tops", "Jeans", "T-Shirts", "Jumpsuits"),
            startingPrice = 129,
            discountText = "Min 50% Off",
            highlightInfo = "Trending Korean & Party Wear Styles • Fresh Weekly Drops",
            topBrands = listOf("Urban Glam", "Street Chic", "Denim Republic"),
            imageUrl = "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=400&auto=format&fit=crop&q=80"
        ),
        Category(
            id = "men",
            name = "Menswear",
            iconName = "shirt",
            badge = null,
            subcategories = listOf("All Items", "Shirts", "T-Shirts", "Jeans", "Ethnic Wear", "Activewear"),
            startingPrice = 199,
            discountText = "Flat 60% Off",
            highlightInfo = "100% Bio-Washed Cotton • Breathable & Durable Fabrics",
            topBrands = listOf("Urban Garments", "Twill Club", "Surat Hub"),
            imageUrl = "https://images.unsplash.com/photo-1521572267360-ee0c2909d518?w=400&auto=format&fit=crop&q=80"
        ),
        Category(
            id = "footwear",
            name = "Footwear",
            iconName = "shoes",
            badge = null,
            subcategories = listOf("All Items", "Women Flats", "Men Casual Shoes", "Heels", "Slippers"),
            startingPrice = 179,
            discountText = "Min 60% Off",
            highlightInfo = "Cushioned Memory Foam • Anti-Skid Grippy Rubber Soles",
            topBrands = listOf("AeroStep", "RoyalJutti", "CloudWalk"),
            imageUrl = "https://images.unsplash.com/photo-1595950653106-6c9ebd614d3a?w=400&auto=format&fit=crop&q=80"
        ),
        Category(
            id = "home",
            name = "Home Decor",
            iconName = "home",
            badge = null,
            subcategories = listOf("All Items", "Bedsheets", "Curtains", "Cookware", "Storage", "Decor"),
            startingPrice = 119,
            discountText = "Up to 75% Off",
            highlightInfo = "Panipat Handloom & Aesthetic Vases",
            topBrands = listOf("Panipat Handloom", "KitchenCraft", "HomeComfort"),
            imageUrl = "https://images.unsplash.com/photo-1581783342308-f792dbdd27c5?w=400&auto=format&fit=crop&q=80"
        ),
        Category(
            id = "beauty",
            name = "Beauty",
            iconName = "sparkles",
            badge = null,
            subcategories = listOf("All Items", "Lipsticks", "Skin Care", "Hair Care", "Perfumes"),
            startingPrice = 89,
            discountText = "Flat 50% Off",
            highlightInfo = "Dermatologically Tested • 100% Herbal & Toxin Free",
            topBrands = listOf("BeautyGlow", "PureNaturals", "AromaBliss"),
            imageUrl = "https://images.unsplash.com/photo-1586495777744-4413f21062fa?w=400&auto=format&fit=crop&q=80"
        ),
        Category(
            id = "accessories",
            name = "Accessories",
            iconName = "bag",
            badge = null,
            subcategories = listOf("All Items", "Handbags", "Wallets", "Belts", "Sunglasses"),
            startingPrice = 149,
            discountText = "Min 60% Off",
            highlightInfo = "Trendy Handbags, Wallets & Belts",
            topBrands = listOf("LuxeCarry", "UrbanStyle", "LeatherCraft"),
            imageUrl = "https://images.unsplash.com/photo-1590874103328-eac38a683ce7?w=400&auto=format&fit=crop&q=80"
        ),
        Category(
            id = "grocery",
            name = "Grocery",
            iconName = "basket",
            badge = null,
            subcategories = listOf("All Items", "Daily Essentials", "Snacks", "Dry Fruits", "Spices"),
            startingPrice = 49,
            discountText = "Up to 50% Off",
            highlightInfo = "Fresh Daily Groceries & Kitchen Staples",
            topBrands = listOf("FreshFarm", "PurePantry", "NatureBasket"),
            imageUrl = "https://images.unsplash.com/photo-1542838132-92c53300491e?w=400&auto=format&fit=crop&q=80"
        ),
        Category(
            id = "jewellery",
            name = "Jewellery",
            iconName = "gem",
            badge = "TRENDING",
            subcategories = listOf("All Items", "Necklaces", "Earrings", "Bangles", "Anklets", "Rings"),
            startingPrice = 69,
            discountText = "Min 70% Off",
            highlightInfo = "Handcrafted Kundan, Meenakari & Oxidised Silver Sets",
            topBrands = listOf("Rajwada Ornaments", "Zaveri Crafts", "Silver Shine"),
            imageUrl = "https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?w=400&auto=format&fit=crop&q=80"
        ),
        Category(
            id = "kids",
            name = "Kids Wear",
            iconName = "baby",
            badge = null,
            subcategories = listOf("All Items", "Boys Clothing", "Girls Dresses", "Baby Care", "Toys"),
            startingPrice = 149,
            discountText = "Min 55% Off",
            highlightInfo = "Super Soft Combed Cotton • Hypoallergenic for Sensitive Skin",
            topBrands = listOf("TinyTots", "JuniorJoy", "PlaySmart"),
            imageUrl = "https://images.unsplash.com/photo-1519457431-44ccd64a579b?w=400&auto=format&fit=crop&q=80"
        ),
        Category(
            id = "electronics",
            name = "Electronics",
            iconName = "headphones",
            badge = "SALE",
            subcategories = listOf("All Items", "Bluetooth Earphones", "Smartwatches", "Chargers"),
            startingPrice = 149,
            discountText = "Up to 80% Off",
            highlightInfo = "6-Month Replacement Warranty • Fast Type-C Charging",
            topBrands = listOf("TechVibe", "SoundPro", "FitLife"),
            imageUrl = "https://images.unsplash.com/photo-1590658268037-6bf12165a8df?w=600&auto=format&fit=crop&q=80"
        )
    )

    // Hero Banners
    fun getBanners(): List<Banner> = listOf(
        Banner(
            id = "b1",
            title = "MEGA BLOCKBUSTER SALE",
            subtitle = "Starting at ₹99 + Free Delivery on 1st Order",
            discountTag = "UP TO 80% OFF",
            bgGradientStart = 0xFF9C276A,
            bgGradientEnd = 0xFF580C34,
            ctaText = "Shop Now",
            targetCategory = "all"
        ),
        Banner(
            id = "b2",
            title = "TRENDING SAREES & KURTIS",
            subtitle = "Direct from Surat & Jaipur Weavers",
            discountTag = "MIN 60% OFF",
            bgGradientStart = 0xFF880E4F,
            bgGradientEnd = 0xFFD81B60,
            ctaText = "Explore Ethnic",
            targetCategory = "ethnic"
        ),
        Banner(
            id = "b3",
            title = "MEN & ELECTRONICS DEALS",
            subtitle = "Shirts, T-Shirts, Earbuds & Smart Bands",
            discountTag = "FLAT ₹199 ONWARDS",
            bgGradientStart = 0xFF1565C0,
            bgGradientEnd = 0xFF0D47A1,
            ctaText = "Grab Deals",
            targetCategory = "men"
        ),
        Banner(
            id = "b4",
            title = "EARN ₹25,000/MONTH",
            subtitle = "Resell on WhatsApp with 0 Investment",
            discountTag = "EXTRA MARGIN",
            bgGradientStart = 0xFF00796B,
            bgGradientEnd = 0xFF004D40,
            ctaText = "Start Earning",
            targetCategory = "all"
        )
    )

    // Master Product Catalog
    fun getAllProducts(): List<Product> = listOf(
        Product(
            id = "m1",
            title = "Kanjivaram Woven Zari Soft Silk Saree with Blouse",
            category = "ethnic",
            subCategory = "Sarees",
            price = 329,
            originalPrice = 1499,
            rating = 4.4f,
            reviewCount = 18450,
            supplierName = "Shree Balaji Weavers",
            supplierRating = 4.3f,
            description = "Rich traditional woven zari brocade border soft art silk saree with unstitched contrast designer blouse piece. Comfortable for wedding, festive and party wear.",
            fabric = "Banarasi Soft Silk",
            pattern = "Floral Woven Zari",
            imageUrl = "https://images.unsplash.com/photo-1610030469983-98e550d6193c?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m2",
            title = "Embroidered Rayon Anarkali Kurti Pant & Dupatta Set",
            category = "ethnic",
            subCategory = "Kurtis",
            price = 449,
            originalPrice = 1399,
            rating = 4.5f,
            reviewCount = 9230,
            supplierName = "Jaipur Ethnic Hub",
            supplierRating = 4.5f,
            description = "Stitched premium 14kg heavy rayon printed and mirror work embroidered calf length Anarkali Kurta with matching straight trousers and chiffon printed dupatta.",
            fabric = "100% Pure Rayon",
            pattern = "Gota Patti & Embroidery",
            imageUrl = "https://images.unsplash.com/photo-1583391733956-3750e0ff4e8b?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m3",
            title = "Men Slim Fit Cotton Twill Casual Spread Collar Shirt",
            category = "men",
            subCategory = "Shirts",
            price = 279,
            originalPrice = 899,
            rating = 4.2f,
            reviewCount = 12400,
            supplierName = "Urban Garments Surat",
            supplierRating = 4.2f,
            description = "Men's breathable premium twill cotton casual shirt with curved hem, spread collar, and single patch pocket. Ideal for office and weekend outings.",
            fabric = "100% Cotton",
            pattern = "Solid / Micro Texture",
            imageUrl = "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m4",
            title = "Wireless Bluetooth Neckband with 35H Playtime & Extra Bass",
            category = "electronics",
            subCategory = "Bluetooth Earphones",
            price = 229,
            originalPrice = 1199,
            rating = 4.3f,
            reviewCount = 31200,
            supplierName = "TechVibe Electronics",
            supplierRating = 4.4f,
            description = "Magnetic ear tips, IPX5 sweat resistance, dual pairing, instant voice assistant, vibration alert for calls, and Type-C fast charge (10 min charge = 8 hr playtime).",
            fabric = "Silicone & Metal Alloy",
            pattern = "Ergonomic Neckband",
            imageUrl = "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m5",
            title = "Women Georgette Floral Tiered Flared Maxi Dress",
            category = "western",
            subCategory = "Dresses",
            price = 369,
            originalPrice = 1299,
            rating = 4.6f,
            reviewCount = 7800,
            supplierName = "Vogue Glamour Delhi",
            supplierRating = 4.5f,
            description = "Flowy georgette floral printed tiered long dress with square neck and breathable inner lining. Perfect for beach, parties, and casual brunch wear.",
            fabric = "Lightweight Georgette",
            pattern = "Bohemian Floral Print",
            imageUrl = "https://images.unsplash.com/photo-1572804013309-59a88b7e92f1?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m6",
            title = "Gold Plated Kundan Choker Necklace Set with Maangtikka",
            category = "jewellery",
            subCategory = "Necklaces",
            price = 179,
            originalPrice = 799,
            rating = 4.4f,
            reviewCount = 14200,
            supplierName = "Rajwada Ornaments",
            supplierRating = 4.3f,
            description = "Handcrafted traditional bridal antique gold polish Kundan studded choker with matching chandelier jhumki earrings and maangtikka with adjustable dori.",
            fabric = "Brass & Kundan Stone",
            pattern = "Meenakari Kundan Work",
            imageUrl = "https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m7",
            title = "Super Soft Glace Cotton King Size Bedsheet with 2 Pillow Covers",
            category = "home",
            subCategory = "Bedsheets",
            price = 299,
            originalPrice = 999,
            rating = 4.1f,
            reviewCount = 8900,
            supplierName = "Panipat Handloom Store",
            supplierRating = 4.2f,
            description = "King size (90x100 inches) high thread count glace cotton bedsheet with vibrant fast-color 3D floral reactive print and two matching zip pillow covers.",
            fabric = "Glace Cotton (210 TC)",
            pattern = "3D Botanical Print",
            imageUrl = "https://images.unsplash.com/photo-1522771739844-6a9f6d5f14af?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m8",
            title = "Long Lasting Waterproof Matte Liquid Lipstick Combo Pack of 4",
            category = "beauty",
            subCategory = "Lipsticks",
            price = 149,
            originalPrice = 599,
            rating = 4.6f,
            reviewCount = 22100,
            supplierName = "BeautyGlow Cosmetics",
            supplierRating = 4.6f,
            description = "Transfer-proof, smudge-free velvet matte finish liquid lipsticks enriched with Vitamin E. Beautiful Indian skin nude and festive red/mauve shades.",
            fabric = "Enriched with Argan Oil",
            pattern = "Velvet Matte Finish",
            imageUrl = "https://images.unsplash.com/photo-1586495777744-4413f21062fa?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m9",
            title = "Men Ultra Lightweight Breathable Walking & Running Shoes",
            category = "footwear",
            subCategory = "Men Casual Shoes",
            price = 349,
            originalPrice = 1199,
            rating = 4.2f,
            reviewCount = 16800,
            supplierName = "AeroStep Footwear Agra",
            supplierRating = 4.3f,
            description = "Flyknit breathable mesh upper with high-cushion EVA bounce sole. Shock absorbent, anti-skid bottom tread, and memory foam comfort insole.",
            fabric = "Breathable Knit Mesh",
            pattern = "Athletic Sporty",
            imageUrl = "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m10",
            title = "Kids Unisex 100% Pure Cotton Printed Dungaree & T-Shirt Set",
            category = "kids",
            subCategory = "Baby Care",
            price = 199,
            originalPrice = 699,
            rating = 4.3f,
            reviewCount = 6400,
            supplierName = "TinyTots Kids World",
            supplierRating = 4.4f,
            description = "Soft combed bio-washed cotton dungaree with animal embroidery and inner round neck half sleeve t-shirt. Hypoallergenic and gentle on delicate skin.",
            fabric = "100% Combed Cotton",
            pattern = "Cute Animal Cartoon",
            imageUrl = "https://images.unsplash.com/photo-1522771930-78848d9293e8?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m11",
            title = "Heavy Non-Stick Flat Dosa Tawa & Deep Frying Kadai Combo",
            category = "home",
            subCategory = "Cookware",
            price = 389,
            originalPrice = 1099,
            rating = 4.4f,
            reviewCount = 9500,
            supplierName = "KitchenCraft Industries",
            supplierRating = 4.3f,
            description = "PFOA-free 3-layer German granite coating with heat-resistant Bakelite cool-touch handles. Induction and gas stove compatible.",
            fabric = "Aluminium with Granite Finish",
            pattern = "Granite Marble Grey",
            imageUrl = "https://images.unsplash.com/photo-1584269600464-37b1b58a9fe7?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m12",
            title = "Smart Fitness Band with SpO2, Heart Rate & Sleep Tracker",
            category = "electronics",
            subCategory = "Smartwatches",
            price = 449,
            originalPrice = 1899,
            rating = 4.1f,
            reviewCount = 15300,
            supplierName = "FitLife Devices",
            supplierRating = 4.2f,
            description = "Full touch color AMOLED display, 14-day battery life, 50+ sports modes, real-time WhatsApp & call notification alerts, and water resistance up to 50m.",
            fabric = "TPU Strap & Glass",
            pattern = "Sleek Matte Black",
            imageUrl = "https://images.unsplash.com/photo-1575311373937-040b8e1fd5b6?w=600&auto=format&fit=crop&q=80"
        ),
        // ETHNIC WEAR
        Product(
            id = "m13",
            title = "Surat Georgette Heavy Bandhani Foil Printed Saree",
            category = "ethnic",
            subCategory = "Sarees",
            price = 289,
            originalPrice = 999,
            rating = 4.4f,
            reviewCount = 11200,
            supplierName = "Surat Silk Mills",
            supplierRating = 4.4f,
            description = "Jaipuri traditional Bandhani print lightweight georgette saree with woven border and matching unstitched blouse piece. Drape easily for daily and festive wear.",
            fabric = "Pure Georgette",
            pattern = "Bandhani Foil Work",
            imageUrl = "https://images.unsplash.com/photo-1617627143750-d86bc21e42bb?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m14",
            title = "Jaipuri Pure Cotton Lucknowi Chikankari Straight Kurti",
            category = "ethnic",
            subCategory = "Kurtis",
            price = 299,
            originalPrice = 899,
            rating = 4.5f,
            reviewCount = 14800,
            supplierName = "Jaipur Ethnic Hub",
            supplierRating = 4.5f,
            description = "Intricate hand-embroidered Lucknowi Chikankari threadwork on soft breathable 60-60 pure cambric cotton. Side slits and round neck.",
            fabric = "100% Cambric Cotton",
            pattern = "Chikankari Handwork",
            imageUrl = "https://images.unsplash.com/photo-1609357605129-26f69add5d6e?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m15",
            title = "Semi-Stitched Heavy Dori Embroidered Velvet Bridal Lehenga",
            category = "ethnic",
            subCategory = "Lehengas",
            price = 699,
            originalPrice = 2999,
            rating = 4.6f,
            reviewCount = 5400,
            supplierName = "Banarasi Heritage",
            supplierRating = 4.5f,
            description = "Royal semi-stitched bridal velvet lehenga choli with rich dori, sequence embroidery, double can-can inner lining and contrast net dupatta.",
            fabric = "Heavy Micro Velvet",
            pattern = "Zari & Sequence Floral",
            imageUrl = "https://images.unsplash.com/photo-1594744803329-e58b31de8bf5?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m16",
            title = "Chanderi Cotton Unstitched Salwar Suit with Banarasi Dupatta",
            category = "ethnic",
            subCategory = "Suits",
            price = 399,
            originalPrice = 1299,
            rating = 4.3f,
            reviewCount = 8200,
            supplierName = "Surat Silk Mills",
            supplierRating = 4.3f,
            description = "Unstitched 3-piece suit material: Chanderi cotton top with neck embroidery, santoon bottom, and rich gold woven Banarasi silk dupatta.",
            fabric = "Chanderi Silk Cotton",
            pattern = "Neckline Embroidered",
            imageUrl = "https://images.unsplash.com/photo-1610030469983-98e550d6193c?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m17",
            title = "Heavy Embroidered Chiffon Phulkari Dupatta with Gota Patti",
            category = "ethnic",
            subCategory = "Dupattas",
            price = 149,
            originalPrice = 499,
            rating = 4.4f,
            reviewCount = 6300,
            supplierName = "Amritsar Crafts",
            supplierRating = 4.4f,
            description = "Authentic Punjabi Phulkari multi-color silk thread embroidery on soft chiffon fabric with golden gota patti four-side border lace (Length: 2.25m).",
            fabric = "Soft Chiffon",
            pattern = "Phulkari Geometric Work",
            imageUrl = "https://images.unsplash.com/photo-1609357605129-26f69add5d6e?w=600&auto=format&fit=crop&q=80"
        ),

        // WESTERN WEAR
        Product(
            id = "m18",
            title = "A-Line Knee Length Puff Sleeve Floral Summer Casual Dress",
            category = "western",
            subCategory = "Dresses",
            price = 299,
            originalPrice = 999,
            rating = 4.5f,
            reviewCount = 6900,
            supplierName = "Urban Glam",
            supplierRating = 4.4f,
            description = "Chic sweetheart neck A-line casual western dress with elasticated puff sleeves and breathable cotton inner lining. Flattering silhouette.",
            fabric = "Crepe Cotton",
            pattern = "Daisy Floral Print",
            imageUrl = "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m19",
            title = "Women Ribbed Knit High Neck Full Sleeve Slim Fit Top",
            category = "western",
            subCategory = "Tops",
            price = 169,
            originalPrice = 599,
            rating = 4.4f,
            reviewCount = 12900,
            supplierName = "Street Chic",
            supplierRating = 4.5f,
            description = "Stretchable premium ribbed knit fabric, high turtleneck collar, full sleeves. Ideal for college, work, layering under jackets or pairing with denim.",
            fabric = "Ribbed Cotton Spandex",
            pattern = "Solid Ribbed",
            imageUrl = "https://images.unsplash.com/photo-1503342217505-b0a15ec3261c?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m20",
            title = "Women High Waist Stretchable Wide Leg Bootcut Denim Jeans",
            category = "western",
            subCategory = "Jeans",
            price = 399,
            originalPrice = 1299,
            rating = 4.3f,
            reviewCount = 9800,
            supplierName = "Denim Republic",
            supplierRating = 4.3f,
            description = "Flattering high-rise wide leg flared bootcut jeans crafted from cotton rich stretch denim. 5 pockets, durable zip fly, and ankle length fit.",
            fabric = "Stretchable Denim Cotton",
            pattern = "Clean Solid Vintage Wash",
            imageUrl = "https://images.unsplash.com/photo-1541099649105-f69ad21f3246?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m21",
            title = "Oversized Drop Shoulder Bio-Wash Graphic Print T-Shirt",
            category = "western",
            subCategory = "T-Shirts",
            price = 199,
            originalPrice = 699,
            rating = 4.5f,
            reviewCount = 15300,
            supplierName = "Street Chic",
            supplierRating = 4.4f,
            description = "Trendy loose baggy fit oversized graphic tee in 220 GSM super combed bio-washed cotton. Ribbed neck collar and vibrant non-fading back print.",
            fabric = "100% Combed Cotton (220 GSM)",
            pattern = "Urban Anime Graphic",
            imageUrl = "https://images.unsplash.com/photo-1521572267360-ee0c2909d518?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m22",
            title = "Belted Wide Leg Cotton Linen Sleeveless Casual Jumpsuit",
            category = "western",
            subCategory = "Jumpsuits",
            price = 349,
            originalPrice = 1199,
            rating = 4.4f,
            reviewCount = 4200,
            supplierName = "Urban Glam",
            supplierRating = 4.3f,
            description = "Breathable cotton linen sleeveless jumpsuit with removable fabric waist tie belt, V-neckline, two functional side pockets and ankle length wide legs.",
            fabric = "Cotton Linen Blend",
            pattern = "Solid Earth Tone",
            imageUrl = "https://images.unsplash.com/photo-1572804013309-59a88b7e92f1?w=600&auto=format&fit=crop&q=80"
        ),

        // MEN FASHION
        Product(
            id = "m23",
            title = "Men Linen Blend Mandarin Collar Solid Casual Shirt",
            category = "men",
            subCategory = "Shirts",
            price = 299,
            originalPrice = 899,
            rating = 4.3f,
            reviewCount = 7600,
            supplierName = "Urban Garments",
            supplierRating = 4.3f,
            description = "Lightweight airy cotton linen blend shirt with Chinese band collar, roll-up sleeves and curved hemline. Perfect for casual evenings and smart casual wear.",
            fabric = "Cotton Linen",
            pattern = "Solid Pastel",
            imageUrl = "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m24",
            title = "Men 100% Bio-Wash Pure Cotton Solid Round Neck T-Shirt",
            category = "men",
            subCategory = "T-Shirts",
            price = 179,
            originalPrice = 599,
            rating = 4.4f,
            reviewCount = 18900,
            supplierName = "Twill Club",
            supplierRating = 4.4f,
            description = "Pre-shrunk 180 GSM ring spun pure combed cotton tee. Anti-pilling fabric, soft rib knit crew neck collar and durable double-needle sleeve hem.",
            fabric = "100% Pure Combed Cotton",
            pattern = "Solid Clean",
            imageUrl = "https://images.unsplash.com/photo-1521572267360-ee0c2909d518?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m25",
            title = "Men Slim Fit Stretchable Clean Blue Denim Jeans",
            category = "men",
            subCategory = "Jeans",
            price = 379,
            originalPrice = 1299,
            rating = 4.2f,
            reviewCount = 14100,
            supplierName = "Denim Republic",
            supplierRating = 4.3f,
            description = "Cotton elastane stretchable denim jeans. Mid-rise waistband, slim tapered leg profile, sturdy metal rivets and brass zipper fly.",
            fabric = "98% Cotton 2% Elastane",
            pattern = "Classic Dark Indigo",
            imageUrl = "https://images.unsplash.com/photo-1541099649105-f69ad21f3246?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m26",
            title = "Men Pure Cotton Jacquard Festive Kurta Pyjama Set",
            category = "men",
            subCategory = "Ethnic Wear",
            price = 399,
            originalPrice = 1499,
            rating = 4.5f,
            reviewCount = 8700,
            supplierName = "Jaipur Hub",
            supplierRating = 4.5f,
            description = "Self-woven jacquard pure cotton knee-length kurta with side pockets and matching white cotton churidar pyjama with drawstring closure.",
            fabric = "Jacquard Cotton",
            pattern = "Self Weave Butti",
            imageUrl = "https://images.unsplash.com/photo-1583391733956-3750e0ff4e8b?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m27",
            title = "Men Quick Dry Athletic Gym Running Track Pants with Zip Pockets",
            category = "men",
            subCategory = "Activewear",
            price = 249,
            originalPrice = 799,
            rating = 4.3f,
            reviewCount = 11200,
            supplierName = "Twill Club",
            supplierRating = 4.2f,
            description = "4-way stretchable breathable poly-spandex dry-fit fabric. Two deep side pockets with high quality zip lock, elastic waistband with inside drawcord.",
            fabric = "Polyester Spandex Dry-Fit",
            pattern = "Sporty Side Stripe",
            imageUrl = "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=600&auto=format&fit=crop&q=80"
        ),

        // JEWELLERY
        Product(
            id = "m28",
            title = "Rose Gold Minimalist American Diamond Solitaire Pendant Set",
            category = "jewellery",
            subCategory = "Necklaces",
            price = 129,
            originalPrice = 599,
            rating = 4.6f,
            reviewCount = 9800,
            supplierName = "Silver Shine",
            supplierRating = 4.5f,
            description = "Delicate rose gold-plated link chain with brilliant AAA quality cubic zirconia solitaire stone pendant and matching stud earrings. Anti-tarnish coated.",
            fabric = "Rose Gold Plated Alloy",
            pattern = "Cubic Zirconia Solitaire",
            imageUrl = "https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m29",
            title = "Traditional Oxidised Silver Kashmiri Floral Jhumki Earrings",
            category = "jewellery",
            subCategory = "Earrings",
            price = 89,
            originalPrice = 399,
            rating = 4.5f,
            reviewCount = 19400,
            supplierName = "Zaveri Crafts",
            supplierRating = 4.4f,
            description = "Antique German silver oxidised jhumka earrings featuring peacock and ghungroo bell motifs. Skin friendly, lightweight and ideal for ethnic wear.",
            fabric = "German Silver Alloy",
            pattern = "Peacock Ghungroo Bell",
            imageUrl = "https://images.unsplash.com/photo-1535632066927-ab7c9ab60908?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m30",
            title = "Traditional Velvet Kada & Chooda Glass Bangles Set of 24",
            category = "jewellery",
            subCategory = "Bangles",
            price = 149,
            originalPrice = 499,
            rating = 4.3f,
            reviewCount = 7600,
            supplierName = "Rajwada Ornaments",
            supplierRating = 4.3f,
            description = "Set of 24 pieces velvet coated rich bangles with golden crystal stone kadas. Available in size 2.4, 2.6, and 2.8. Adds royal charm to Indian attire.",
            fabric = "Velvet & Brass Stone",
            pattern = "Velvet Matte with Gold Kada",
            imageUrl = "https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m31",
            title = "Silver Plated Ghungroo Charm Daily Wear Payal Anklet Pair",
            category = "jewellery",
            subCategory = "Anklets",
            price = 99,
            originalPrice = 399,
            rating = 4.4f,
            reviewCount = 8900,
            supplierName = "Silver Shine",
            supplierRating = 4.3f,
            description = "Pair of 2 silver plated traditional Indian payals with melodic tiny bells and secure S-hook clasp. Durable plating suitable for daily home use.",
            fabric = "Silver Plated Brass",
            pattern = "Dainty Chain with Bells",
            imageUrl = "https://images.unsplash.com/photo-1535632066927-ab7c9ab60908?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m32",
            title = "Adjustable Crystal Butterfly & Solitaire Couple Rings",
            category = "jewellery",
            subCategory = "Rings",
            price = 79,
            originalPrice = 299,
            rating = 4.5f,
            reviewCount = 11400,
            supplierName = "Zaveri Crafts",
            supplierRating = 4.4f,
            description = "Free size adjustable silver finish couple ring set featuring delicate crystal pavé butterfly and minimalist band. Rhodium polished for long lasting luster.",
            fabric = "Rhodium Plated Copper",
            pattern = "Crystal Pavé Butterfly",
            imageUrl = "https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?w=600&auto=format&fit=crop&q=80"
        ),

        // HOME & KITCHEN
        Product(
            id = "m33",
            title = "Pack of 2 Heavy Jacquard 7Ft Eyelet Door Curtains",
            category = "home",
            subCategory = "Curtains",
            price = 279,
            originalPrice = 899,
            rating = 4.3f,
            reviewCount = 6800,
            supplierName = "Panipat Handloom",
            supplierRating = 4.2f,
            description = "Pair of 2 premium jacquard woven blackout curtains (4x7 feet). Rust-resistant metal eyelet rings, block 80% sunlight, machine washable.",
            fabric = "Heavy Polyester Jacquard",
            pattern = "Woven Damask Leaf",
            imageUrl = "https://images.unsplash.com/photo-1513694203232-719a280e022f?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m34",
            title = "Fabric Foldable Wardrobe Clothes Organizer & Saree Cover Set of 6",
            category = "home",
            subCategory = "Storage",
            price = 189,
            originalPrice = 599,
            rating = 4.4f,
            reviewCount = 9200,
            supplierName = "HomeComfort",
            supplierRating = 4.3f,
            description = "Durable 90 GSM non-woven fabric saree and blanket organizers with clear transparent front window, heavy two-way zippers, and side carry handles.",
            fabric = "Eco Non-Woven Fabric",
            pattern = "Geometric Polka Print",
            imageUrl = "https://images.unsplash.com/photo-1522771739844-6a9f6d5f14af?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m35",
            title = "Handcrafted Wooden Wall Hanging Floating Shelves & LED Fairy Lights",
            category = "home",
            subCategory = "Decor",
            price = 229,
            originalPrice = 799,
            rating = 4.5f,
            reviewCount = 8400,
            supplierName = "HomeComfort",
            supplierRating = 4.4f,
            description = "Set of 3 engineered wood wall mount display shelves for living room and bedroom decor. Includes 10-meter warm white USB LED string lights and wall plugs.",
            fabric = "Engineered Pine Wood",
            pattern = "Rustic Walnut Finish",
            imageUrl = "https://images.unsplash.com/photo-1513694203232-719a280e022f?w=600&auto=format&fit=crop&q=80"
        ),

        // BEAUTY & CARE
        Product(
            id = "m36",
            title = "Vitamin C Brightening Face Serum with Hyaluronic Acid & Niacinamide",
            category = "beauty",
            subCategory = "Skin Care",
            price = 179,
            originalPrice = 699,
            rating = 4.5f,
            reviewCount = 16700,
            supplierName = "PureNaturals",
            supplierRating = 4.6f,
            description = "Potent 10% Vitamin C serum helps fade dark spots, boost skin glow and improve uneven skin tone. Lightweight fast-absorbing non-greasy formula.",
            fabric = "Herbal Extract with Hyaluronic Acid",
            pattern = "Glow Boost Formulation",
            imageUrl = "https://images.unsplash.com/photo-1620916566398-39f1143ab7be?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m37",
            title = "Organic Cold-Pressed Rosemary & Red Onion Hair Oil for Hair Growth",
            category = "beauty",
            subCategory = "Hair Care",
            price = 159,
            originalPrice = 499,
            rating = 4.6f,
            reviewCount = 14300,
            supplierName = "PureNaturals",
            supplierRating = 4.5f,
            description = "Infused with cold pressed rosemary essential oil, red onion sulfur, castor and amla oil. Controls hair fall, strengthens hair roots and removes dandruff.",
            fabric = "100% Pure Cold Pressed Herbal Oils",
            pattern = "Natural Herbal Infusion",
            imageUrl = "https://images.unsplash.com/photo-1608248597359-00f7236d8d64?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m38",
            title = "Luxury French Long Lasting Eau De Parfum Floral & Amber Body Mist",
            category = "beauty",
            subCategory = "Perfumes",
            price = 199,
            originalPrice = 699,
            rating = 4.4f,
            reviewCount = 11500,
            supplierName = "AromaBliss",
            supplierRating = 4.4f,
            description = "Long lasting 100ml Eau De Parfum with notes of Madagascar vanilla, jasmine blossom, and rich warm amber. Keeps you fresh for up to 12 hours.",
            fabric = "Alcohol Denat & Essential Oils",
            pattern = "Amber Floral Mist",
            imageUrl = "https://images.unsplash.com/photo-1541643600914-78b084683601?w=600&auto=format&fit=crop&q=80"
        ),

        // KIDS WEAR & TOYS
        Product(
            id = "m39",
            title = "Boys Cotton Shirt & Denim Jeans with Bow-Tie 3-Piece Party Set",
            category = "kids",
            subCategory = "Boys Clothing",
            price = 289,
            originalPrice = 899,
            rating = 4.4f,
            reviewCount = 5900,
            supplierName = "JuniorJoy",
            supplierRating = 4.4f,
            description = "Smart 3-piece birthday party set for boys aged 1-6 years. Full sleeve cotton collared shirt, clip-on bowtie, and stretch denim trousers with adjustable waist.",
            fabric = "100% Breathable Cotton & Soft Denim",
            pattern = "Classic Check & Solid",
            imageUrl = "https://images.unsplash.com/photo-1519457431-44ccd64a579b?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m40",
            title = "Girls Fluffy Layered Tulle Net Birthday Princess Party Frock",
            category = "kids",
            subCategory = "Girls Dresses",
            price = 319,
            originalPrice = 999,
            rating = 4.6f,
            reviewCount = 7800,
            supplierName = "TinyTots",
            supplierRating = 4.5f,
            description = "Multi-tier ruffled tulle net frock with satin bow belt, floral corsage applique, and soft non-scratchy cotton lining for sensitive toddler skin.",
            fabric = "Soft Net & Cotton Lining",
            pattern = "Princess Ruffle Tier",
            imageUrl = "https://images.unsplash.com/photo-1518831959646-742c3a14ebf7?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m41",
            title = "Educational Magnetic 3D Building Blocks & Puzzle Track Set",
            category = "kids",
            subCategory = "Toys",
            price = 349,
            originalPrice = 1199,
            rating = 4.5f,
            reviewCount = 6700,
            supplierName = "PlaySmart",
            supplierRating = 4.6f,
            description = "36-piece non-toxic ABS magnetic building tiles and geometric shapes. Develops child's creativity, spatial cognition, motor skills and STEM logic.",
            fabric = "BPA-Free Food Grade ABS Plastic",
            pattern = "Rainbow Translucent Tiles",
            imageUrl = "https://images.unsplash.com/photo-1587654780291-39c9404d746b?w=600&auto=format&fit=crop&q=80"
        ),

        // ELECTRONICS
        Product(
            id = "m42",
            title = "TWS Wireless Earbuds with ENC Noise Cancellation & Low Latency",
            category = "electronics",
            subCategory = "Bluetooth Earphones",
            price = 349,
            originalPrice = 1499,
            rating = 4.4f,
            reviewCount = 28900,
            supplierName = "SoundPro",
            supplierRating = 4.5f,
            description = "13mm dynamic bass drivers, Environmental Noise Cancellation (ENC) for crystal clear voice calls, 45ms gaming mode, and 40H battery backup with charging case.",
            fabric = "Matte Polycarbonate",
            pattern = "Compact Pocket Case",
            imageUrl = "https://images.unsplash.com/photo-1590658268037-6bf12165a8df?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m43",
            title = "Fast 33W Dual Port USB & Type-C PD Mobile Fast Wall Charger",
            category = "electronics",
            subCategory = "Chargers",
            price = 199,
            originalPrice = 699,
            rating = 4.3f,
            reviewCount = 14200,
            supplierName = "TechVibe",
            supplierRating = 4.3f,
            description = "Quick Charge 3.0 & Power Delivery (PD) 33W super-fast wall charger with built-in surge and overheat protection. Charges phone from 0 to 60% in 28 minutes.",
            fabric = "Fireproof ABS Plastic",
            pattern = "Compact White Adapter",
            imageUrl = "https://images.unsplash.com/photo-1583863788434-e58a36330cf0?w=600&auto=format&fit=crop&q=80"
        ),

        // FOOTWEAR
        Product(
            id = "m44",
            title = "Women Handcrafted Embroidered Rajasthani Jutti & Kolhapuri",
            category = "footwear",
            subCategory = "Women Flats",
            price = 219,
            originalPrice = 799,
            rating = 4.5f,
            reviewCount = 12400,
            supplierName = "RoyalJutti",
            supplierRating = 4.4f,
            description = "Traditional pure leather Punjabi mojari jutti with silk thread zari embroidery and double padded cushion insole for all-day bite-free comfort.",
            fabric = "Synthetic Leather & Thread",
            pattern = "Rajasthani Floral Zari",
            imageUrl = "https://images.unsplash.com/photo-1543163521-1bf539c55dd2?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m45",
            title = "Women Block Heel Ankle Strap Stylish Peep-Toe Sandals",
            category = "footwear",
            subCategory = "Heels",
            price = 379,
            originalPrice = 1199,
            rating = 4.4f,
            reviewCount = 7900,
            supplierName = "CloudWalk",
            supplierRating = 4.3f,
            description = "Comfortable 2-inch chunky block heel sandals with adjustable ankle buckle strap, soft memory foam insole and anti-slip ribbed resin sole.",
            fabric = "Faux Suede / Vegan Leather",
            pattern = "Solid Elegant Minimal",
            imageUrl = "https://images.unsplash.com/photo-1543163521-1bf539c55dd2?w=600&auto=format&fit=crop&q=80"
        ),
        Product(
            id = "m46",
            title = "Orthopedic Extra Soft Cloud Slides Daily Home Slippers",
            category = "footwear",
            subCategory = "Slippers",
            price = 179,
            originalPrice = 599,
            rating = 4.5f,
            reviewCount = 19100,
            supplierName = "CloudWalk",
            supplierRating = 4.5f,
            description = "Thick 4cm cushioned EVA pillow slides for men and women. Waterproof, ultra-lightweight, ergonomic footbed relief for heel pain and tired feet.",
            fabric = "High Density EVA Foam",
            pattern = "Textured Cloud Slide",
            imageUrl = "https://images.unsplash.com/photo-1560769629-975ec94e6a86?w=600&auto=format&fit=crop&q=80"
        )
    )
}
