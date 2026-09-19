
    // Product Catalog Database (Mirrors the Kotlin Repository)
    const PRODUCTS = [
      {
        id: "m1",
        title: "Kanjivaram Soft Silk Zari Woven Saree With Blouse Piece",
        category: "ethnic",
        subCategory: "Sarees",
        price: 349,
        originalPrice: 1299,
        rating: 4.4,
        reviewCount: 14200,
        supplierName: "Surat Silk Mills",
        description: "Exquisite traditional Kanjivaram soft lichi silk saree with rich jacquard golden zari woven border and heavy pallu. Comes with unstitched blouse piece.",
        fabric: "Soft Lichi Silk",
        pattern: "Zari Woven Jacquard",
        isMeeshoAssured: true,
        imageUrl: "https://images.unsplash.com/photo-1610030469983-98e550d6193c?w=600&auto=format&fit=crop&q=80"
      },
      {
        id: "m2",
        title: "Women Floral Printed Pure Cotton Flared Anarkali Kurti",
        category: "ethnic",
        subCategory: "Kurtis",
        price: 269,
        originalPrice: 899,
        rating: 4.3,
        reviewCount: 8900,
        supplierName: "Jaipur Ethnic Hub",
        description: "Breathable 100% pure cotton Anarkali flared kurti featuring traditional Jaipuri block floral prints, 3/4 sleeves, and round neckline with gota patti work.",
        fabric: "100% Pure Cotton",
        pattern: "Jaipuri Floral Print",
        isMeeshoAssured: true,
        imageUrl: "https://images.unsplash.com/photo-1609357605129-26f69add5d6e?w=600&auto=format&fit=crop&q=80"
      },
      {
        id: "m3",
        title: "Women High Rise Flared Wide Leg Cotton Denim Jeans",
        category: "western",
        subCategory: "Jeans",
        price: 389,
        originalPrice: 1199,
        rating: 4.2,
        reviewCount: 6540,
        supplierName: "Urban Glam Fashion",
        description: "Trendy comfortable high-waist loose fit wide leg boyfriend flared jeans. Premium stretchable cotton denim.",
        fabric: "Stretch Denim Cotton",
        pattern: "Solid Clean Wash",
        isMeeshoAssured: false,
        imageUrl: "https://images.unsplash.com/photo-1541099649105-f69ad21f3246?w=600&auto=format&fit=crop&q=80"
      },
      {
        id: "m4",
        title: "Men Regular Fit Cotton Blend Spread Collar Casual Shirt",
        category: "men",
        subCategory: "Shirts",
        price: 299,
        originalPrice: 799,
        rating: 4.5,
        reviewCount: 19300,
        supplierName: "Urban Garments",
        description: "Stylish everyday button-down casual shirt crafted from breathable cotton blend fabric. Curved hemline and classic spread collar.",
        fabric: "Cotton Blend",
        pattern: "Solid Minimalist",
        isMeeshoAssured: true,
        imageUrl: "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?w=600&auto=format&fit=crop&q=80"
      },
      {
        id: "m5",
        title: "18K Gold Plated Austrian Crystal Floral Choker Jewellery Set",
        category: "jewellery",
        subCategory: "Necklaces",
        price: 189,
        originalPrice: 799,
        rating: 4.6,
        reviewCount: 22400,
        supplierName: "Rajwada Ornaments",
        description: "Intricate wedding bridal choker necklace set studded with sparkling Austrian crystal stones and matching hanging drop earrings with maang tikka.",
        fabric: "Brass Alloy & Austrian Crystals",
        pattern: "Royal Floral Filigree",
        isMeeshoAssured: true,
        imageUrl: "https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?w=600&auto=format&fit=crop&q=80"
      },
      {
        id: "m6",
        title: "Glance Pure Cotton Double Bedsheet With 2 Pillow Covers",
        category: "home",
        subCategory: "Bedsheets",
        price: 249,
        originalPrice: 699,
        rating: 4.1,
        reviewCount: 11200,
        supplierName: "Panipat Handloom",
        description: "Super soft king size 140 TC glace cotton bedsheet with vibrant geometric prints and 2 matching envelope pillow covers.",
        fabric: "Glace Cotton (140 TC)",
        pattern: "Geometric Bohemian",
        isMeeshoAssured: false,
        imageUrl: "https://images.unsplash.com/photo-1522771739844-6a9f6d5f14af?w=600&auto=format&fit=crop&q=80"
      },
      {
        id: "m7",
        title: "Velvet Matte Non-Transfer Waterproof Liquid Lipstick Set of 4",
        category: "beauty",
        subCategory: "Lipsticks",
        price: 149,
        originalPrice: 499,
        rating: 4.5,
        reviewCount: 31000,
        supplierName: "BeautyGlow Cosmetics",
        description: "Long-lasting 16hr smudge-proof transfer-resistant matte liquid lipsticks infused with Vitamin E.",
        fabric: "Herbal Extract & Vitamin E",
        pattern: "Matte Nude Palette",
        isMeeshoAssured: true,
        imageUrl: "https://images.unsplash.com/photo-1586495777744-4413f21062fa?w=600&auto=format&fit=crop&q=80"
      },
      {
        id: "m8",
        title: "Kids Soft Breathable Cotton Printed T-Shirt & Shorts Set",
        category: "kids",
        subCategory: "Boys Clothing",
        price: 199,
        originalPrice: 599,
        rating: 4.3,
        reviewCount: 4200,
        supplierName: "TinyTots Clothing",
        description: "Cute comfy 2-piece summer casual clothing set for toddler boys and girls. 100% bio-washed combed cotton.",
        fabric: "100% Combed Cotton",
        pattern: "Cartoon Animal Print",
        isMeeshoAssured: false,
        imageUrl: "https://images.unsplash.com/photo-1519457431-44ccd64a579b?w=600&auto=format&fit=crop&q=80"
      },
      {
        id: "m9",
        title: "Bluetooth Wireless Neckband Earphones with 40H Battery Life",
        category: "electronics",
        subCategory: "Bluetooth Earphones",
        price: 249,
        originalPrice: 999,
        rating: 4.4,
        reviewCount: 45000,
        supplierName: "TechVibe Electronics",
        description: "Deep heavy bass wireless in-ear neckband headset with magnetic earbuds, Type-C fast charge and IPX5 sweat resistance.",
        fabric: "Silicone & Metal Alloy",
        pattern: "Ergonomic Flex Neckband",
        isMeeshoAssured: true,
        imageUrl: "https://images.unsplash.com/photo-1590658268037-6bf12165a8df?w=600&auto=format&fit=crop&q=80"
      },
      {
        id: "m10",
        title: "Women Embroidered Ethnic Mojari Flats with Memory Cushion",
        category: "footwear",
        subCategory: "Women Flats",
        price: 229,
        originalPrice: 699,
        rating: 4.3,
        reviewCount: 7800,
        supplierName: "AeroStep Footwear",
        description: "Traditional Rajasthani thread embroidered slip-on Punjabi jutti bellies with extra padded bite-free memory foam insoles.",
        fabric: "Synthetic Leather & Velvet",
        pattern: "Zari Thread Handwork",
        isMeeshoAssured: true,
        imageUrl: "https://images.unsplash.com/photo-1543163521-1bf539c55dd2?w=600&auto=format&fit=crop&q=80"
      },
      {
        id: "m11",
        title: "Surat Georgette Heavy Bandhani Foil Printed Saree",
        category: "ethnic",
        subCategory: "Sarees",
        price: 289,
        originalPrice: 999,
        rating: 4.4,
        reviewCount: 11200,
        supplierName: "Surat Silk Mills",
        description: "Jaipuri traditional Bandhani print lightweight georgette saree with woven border and matching unstitched blouse piece.",
        fabric: "Pure Georgette",
        pattern: "Bandhani Foil Work",
        isMeeshoAssured: true,
        imageUrl: "https://images.unsplash.com/photo-1617627143750-d86bc21e42bb?w=600&auto=format&fit=crop&q=80"
      },
      {
        id: "m12",
        title: "A-Line Knee Length Puff Sleeve Floral Summer Casual Dress",
        category: "western",
        subCategory: "Dresses",
        price: 299,
        originalPrice: 999,
        rating: 4.5,
        reviewCount: 6900,
        supplierName: "Urban Glam",
        description: "Chic sweetheart neck A-line casual western dress with elasticated puff sleeves and breathable cotton inner lining.",
        fabric: "Crepe Cotton",
        pattern: "Daisy Floral Print",
        isMeeshoAssured: false,
        imageUrl: "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=600&auto=format&fit=crop&q=80"
      },
      {
        id: "m13",
        title: "Men Pure Cotton Jacquard Festive Kurta Pyjama Set",
        category: "men",
        subCategory: "Ethnic Wear",
        price: 399,
        originalPrice: 1499,
        rating: 4.5,
        reviewCount: 8700,
        supplierName: "Jaipur Hub",
        description: "Self-woven jacquard pure cotton knee-length kurta with side pockets and matching white cotton churidar pyjama with drawstring closure.",
        fabric: "Jacquard Cotton",
        pattern: "Self Weave Butti",
        isMeeshoAssured: true,
        imageUrl: "https://images.unsplash.com/photo-1583391733956-3750e0ff4e8b?w=600&auto=format&fit=crop&q=80"
      },
      {
        id: "m14",
        title: "TWS Wireless Earbuds with ENC Noise Cancellation",
        category: "electronics",
        subCategory: "Bluetooth Earphones",
        price: 349,
        originalPrice: 1499,
        rating: 4.4,
        reviewCount: 28900,
        supplierName: "SoundPro",
        description: "13mm dynamic bass drivers, Environmental Noise Cancellation (ENC) for crystal clear voice calls, and 40H battery backup.",
        fabric: "Matte Polycarbonate",
        pattern: "Compact Pocket Case",
        isMeeshoAssured: true,
        imageUrl: "https://images.unsplash.com/photo-1590658268037-6bf12165a8df?w=600&auto=format&fit=crop&q=80"
      }
    ];

    const CATEGORIES = [
      {
        id: "ethnic",
        name: "Ethnic Wear",
        icon: "🥻",
        image: "https://images.unsplash.com/photo-1610030469983-98e550d6193c?w=400&auto=format&fit=crop&q=80",
        badge: "HOT",
        startingPrice: 149,
        discount: "Min 60% Off",
        info: "Direct from Surat & Jaipur Weavers",
        subs: ["All Items", "Sarees", "Kurtis", "Lehengas", "Suits", "Dupattas"]
      },
      {
        id: "western",
        name: "Western Dresses",
        icon: "👗",
        image: "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=400&auto=format&fit=crop&q=80",
        badge: null,
        startingPrice: 129,
        discount: "Min 50% Off",
        info: "Trending Korean & Party Wear Styles",
        subs: ["All Items", "Dresses", "Tops", "Jeans", "T-Shirts", "Jumpsuits"]
      },
      {
        id: "men",
        name: "Menswear",
        icon: "👔",
        image: "https://images.unsplash.com/photo-1521572267360-ee0c2909d518?w=400&auto=format&fit=crop&q=80",
        badge: null,
        startingPrice: 199,
        discount: "Flat 60% Off",
        info: "100% Bio-Washed Cotton T-Shirts & Shirts",
        subs: ["All Items", "Shirts", "T-Shirts", "Jeans", "Ethnic Wear"]
      },
      {
        id: "footwear",
        name: "Footwear",
        icon: "👟",
        image: "https://images.unsplash.com/photo-1595950653106-6c9ebd614d3a?w=400&auto=format&fit=crop&q=80",
        badge: null,
        startingPrice: 179,
        discount: "Min 60% Off",
        info: "Cushioned Memory Foam Insole",
        subs: ["All Items", "Sneakers", "Women Flats", "Heels", "Slippers"]
      },
      {
        id: "home",
        name: "Home Decor",
        icon: "🏺",
        image: "https://images.unsplash.com/photo-1581783342308-f792dbdd27c5?w=400&auto=format&fit=crop&q=80",
        badge: null,
        startingPrice: 119,
        discount: "Up to 75% Off",
        info: "Panipat Handloom & Aesthetic Vases",
        subs: ["All Items", "Bedsheets", "Curtains", "Storage", "Decor"]
      },
      {
        id: "beauty",
        name: "Beauty",
        icon: "💄",
        image: "https://images.unsplash.com/photo-1586495777744-4413f21062fa?w=400&auto=format&fit=crop&q=80",
        badge: null,
        startingPrice: 89,
        discount: "Flat 50% Off",
        info: "Dermatologically Tested & Herbal",
        subs: ["All Items", "Lipsticks", "Skin Care", "Hair Care", "Perfumes"]
      },
      {
        id: "accessories",
        name: "Accessories",
        icon: "👜",
        image: "https://images.unsplash.com/photo-1590874103328-eac38a683ce7?w=400&auto=format&fit=crop&q=80",
        badge: null,
        startingPrice: 149,
        discount: "Min 60% Off",
        info: "Trendy Bags, Belts & Eyewear",
        subs: ["All Items", "Handbags", "Wallets", "Belts", "Sunglasses"]
      },
      {
        id: "grocery",
        name: "Grocery",
        icon: "🧺",
        image: "https://images.unsplash.com/photo-1542838132-92c53300491e?w=400&auto=format&fit=crop&q=80",
        badge: null,
        startingPrice: 49,
        discount: "Up to 50% Off",
        info: "Fresh Daily Essentials & Groceries",
        subs: ["All Items", "Daily Essentials", "Snacks", "Dry Fruits", "Spices"]
      },
      {
        id: "jewellery",
        name: "Jewellery",
        icon: "💍",
        image: "https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?w=400&auto=format&fit=crop&q=80",
        badge: "TRENDING",
        startingPrice: 69,
        discount: "Min 70% Off",
        info: "Handcrafted Kundan, Meenakari & Silver",
        subs: ["All Items", "Necklaces", "Earrings", "Bangles", "Rings"]
      },
      {
        id: "kids",
        name: "Kids Wear",
        icon: "🧸",
        image: "https://images.unsplash.com/photo-1519457431-44ccd64a579b?w=400&auto=format&fit=crop&q=80",
        badge: null,
        startingPrice: 149,
        discount: "Min 55% Off",
        info: "Super Soft Combed Cotton For Toddlers",
        subs: ["All Items", "Boys Clothing", "Girls Dresses", "Toys"]
      },
      {
        id: "electronics",
        name: "Electronics",
        icon: "🎧",
        image: "https://images.unsplash.com/photo-1590658268037-6bf12165a8df?w=600&auto=format&fit=crop&q=80",
        badge: "SALE",
        startingPrice: 149,
        discount: "Up to 80% Off",
        info: "6-Month Replacement Warranty",
        subs: ["All Items", "Bluetooth Earphones", "Chargers"]
      }
    ];

    // App State Management (with LocalStorage)
    let cart = JSON.parse(localStorage.getItem('meesho_cart') || '[]');
    let wishlist = JSON.parse(localStorage.getItem('meesho_wishlist') || '[]');
    let orders = JSON.parse(localStorage.getItem('meesho_orders') || '[]');
    const DEFAULT_ADDRESSES = [
      {
        id: "addr_1",
        name: "",
        phone: "",
        house: "",
        area: "",
        cityState: "",
        pincode: "",
        selected: true
      }
    ];
    let savedAddresses = JSON.parse(localStorage.getItem('meesho_addresses') || 'null') || DEFAULT_ADDRESSES;
    let selectedCategory = CATEGORIES[0];
    let selectedSubCategory = "All Items";
    let selectedPayment = "UPI";
    let currentTab = "home";

    // Circled Meesho Filter Bar State (Sort, Category, Gender, Filters)
    let homeSort = 'relevance';
    let homeCategoryFilter = 'all';
    let homeGenderFilter = 'all';
    let homeMaxPrice = null;
    let homeMinRating = null;
    let homeAssuredOnly = false;

    function openSortSheet() {
      document.getElementById('sort-sheet').classList.add('open');
    }
    function openCategoryFilterSheet() {
      document.getElementById('cat-filter-sheet').classList.add('open');
    }
    function openGenderFilterSheet() {
      document.getElementById('gender-filter-sheet').classList.add('open');
    }
    function openAllFiltersSheet() {
      document.getElementById('all-filter-sheet').classList.add('open');
    }
    function closeSheet(sheetId) {
      document.getElementById(sheetId).classList.remove('open');
    }

    function chooseSort(sortType) {
      homeSort = sortType;
      const items = document.querySelectorAll('#sort-options-list .filter-opt-item');
      const sortTypes = ['relevance', 'price_low', 'price_high', 'rating', 'discount'];
      items.forEach((item, idx) => {
        item.classList.toggle('selected', sortTypes[idx] === sortType);
      });
      const sortLabels = {
        'relevance': 'Sort',
        'price_low': 'Price: Low',
        'price_high': 'Price: High',
        'rating': 'Rating',
        'discount': 'Discount'
      };
      const labelEl = document.getElementById('label-sort');
      if (labelEl) labelEl.innerText = sortLabels[sortType] || 'Sort';
      const btnEl = document.getElementById('btn-sort');
      if (btnEl) btnEl.classList.toggle('is-active', sortType !== 'relevance');
      closeSheet('sort-sheet');
      applyHomeFilters();
    }

    function chooseCategoryFilter(catId) {
      homeCategoryFilter = catId;
      const catItems = document.querySelectorAll('#category-options-list .filter-opt-item');
      const catIds = ['all', 'ethnic', 'western', 'men', 'jewellery', 'home', 'beauty', 'kids', 'electronics', 'footwear'];
      catItems.forEach((item, idx) => {
        item.classList.toggle('selected', catIds[idx] === catId);
      });
      const cat = CATEGORIES.find(c => c.id === catId);
      const labelEl = document.getElementById('label-cat');
      if (labelEl) labelEl.innerText = cat ? cat.name.split(' ')[0] : 'Category';
      const btnEl = document.getElementById('btn-category');
      if (btnEl) btnEl.classList.toggle('is-active', catId !== 'all');
      closeSheet('cat-filter-sheet');
      applyHomeFilters();
    }

    function chooseGenderFilter(gender) {
      homeGenderFilter = gender;
      const genderItems = document.querySelectorAll('#gender-options-list .filter-opt-item');
      const genders = ['all', 'women', 'men', 'kids', 'unisex'];
      genderItems.forEach((item, idx) => {
        item.classList.toggle('selected', genders[idx] === gender);
      });
      const genderLabels = {
        'all': 'Gender',
        'women': 'Women',
        'men': 'Men',
        'kids': 'Kids',
        'unisex': 'Unisex'
      };
      const labelEl = document.getElementById('label-gender');
      if (labelEl) labelEl.innerText = genderLabels[gender] || 'Gender';
      const btnEl = document.getElementById('btn-gender');
      if (btnEl) btnEl.classList.toggle('is-active', gender !== 'all');
      closeSheet('gender-filter-sheet');
      applyHomeFilters();
    }

    function toggleBudgetFilter(maxPrice) {
      homeMaxPrice = (homeMaxPrice === maxPrice) ? null : maxPrice;
      ['all-price', '199', '299', '499'].forEach(id => {
        const el = document.getElementById(`filter-chip-${id}`);
        if (!el) return;
        if (id === 'all-price') el.classList.toggle('active', homeMaxPrice === null);
        else if (id === '199') el.classList.toggle('active', homeMaxPrice === 199);
        else if (id === '299') el.classList.toggle('active', homeMaxPrice === 299);
        else if (id === '499') el.classList.toggle('active', homeMaxPrice === 499);
      });
    }

    function toggleRatingFilter(minRating) {
      homeMinRating = (homeMinRating === minRating) ? null : minRating;
      const chipAll = document.getElementById('filter-chip-all-rating');
      const chip4 = document.getElementById('filter-chip-4');
      const chip43 = document.getElementById('filter-chip-43');
      if (chipAll) chipAll.classList.toggle('active', homeMinRating === null);
      if (chip4) chip4.classList.toggle('active', homeMinRating === 4.0);
      if (chip43) chip43.classList.toggle('active', homeMinRating === 4.3);
    }

    function toggleAssuredFilter() {
      homeAssuredOnly = !homeAssuredOnly;
      const chip = document.getElementById('filter-chip-assured');
      if (chip) chip.classList.toggle('active', homeAssuredOnly);
    }

    function resetAllFilters() {
      homeMaxPrice = null;
      homeMinRating = null;
      homeAssuredOnly = false;
      toggleBudgetFilter(null);
      toggleRatingFilter(null);
      const chip = document.getElementById('filter-chip-assured');
      if (chip) chip.classList.remove('active');
      const btnEl = document.getElementById('btn-filters');
      if (btnEl) btnEl.classList.remove('is-active');
      const labelEl = document.getElementById('label-filters');
      if (labelEl) labelEl.innerText = 'Filters';
      closeSheet('all-filter-sheet');
      applyHomeFilters();
    }

    function applyAndCloseFilters() {
      const filterCount = (homeMaxPrice ? 1 : 0) + (homeMinRating ? 1 : 0) + (homeAssuredOnly ? 1 : 0);
      const btnEl = document.getElementById('btn-filters');
      if (btnEl) btnEl.classList.toggle('is-active', filterCount > 0);
      const labelEl = document.getElementById('label-filters');
      if (labelEl) labelEl.innerText = filterCount > 0 ? `Filters (${filterCount})` : 'Filters';
      closeSheet('all-filter-sheet');
      applyHomeFilters();
    }

    function applyHomeFilters() {
      let list = [...PRODUCTS];

      // 1. Category Filter
      if (homeCategoryFilter !== 'all') {
        list = list.filter(p => p.category === homeCategoryFilter);
      }

      // 2. Gender Filter
      if (homeGenderFilter !== 'all') {
        list = list.filter(p => {
          const t = p.title.toLowerCase();
          if (homeGenderFilter === 'women') {
            return p.category === 'ethnic' || p.category === 'western' || p.category === 'jewellery' || p.category === 'beauty' || t.includes('women') || t.includes('saree') || t.includes('kurti') || t.includes('nosepin');
          } else if (homeGenderFilter === 'men') {
            return p.category === 'men' || t.includes('men') || t.includes('shirt') || t.includes('kurta');
          } else if (homeGenderFilter === 'kids') {
            return p.category === 'kids' || t.includes('kid') || t.includes('toddler');
          } else if (homeGenderFilter === 'unisex') {
            return p.category === 'home' || p.category === 'electronics';
          }
          return true;
        });
      }

      // 3. Price Filter
      if (homeMaxPrice) {
        list = list.filter(p => p.price <= homeMaxPrice);
      }

      // 4. Rating Filter
      if (homeMinRating) {
        list = list.filter(p => p.rating >= homeMinRating);
      }

      // 5. Assured Filter
      if (homeAssuredOnly) {
        list = list.filter(p => p.isMeeshoAssured);
      }

      // 6. Sort
      if (homeSort === 'price_low') {
        list.sort((a, b) => a.price - b.price);
      } else if (homeSort === 'price_high') {
        list.sort((a, b) => b.price - a.price);
      } else if (homeSort === 'rating') {
        list.sort((a, b) => b.rating - a.rating);
      } else if (homeSort === 'discount') {
        list.sort((a, b) => {
          const discA = (a.originalPrice - a.price) / a.originalPrice;
          const discB = (b.originalPrice - b.price) / b.originalPrice;
          return discB - discA;
        });
      }

      renderProducts(list, 'home-product-grid');
    }

    // Initialize App
    function init() {
      renderCategoryCircles();
      renderProducts(PRODUCTS, 'home-product-grid');
      renderCategoriesRail();
      updateBadges();
      renderOrders();
      updateDeliverToDisplay();

      // Seed 1 order if none exists so orders tab is lively
      if (orders.length === 0) {
        orders.push({
          orderId: "MEESHO-ORD-89421",
          date: "Yesterday",
          items: [{ title: PRODUCTS[0].title, price: PRODUCTS[0].price, qty: 1, image: PRODUCTS[0].imageUrl }],
          total: PRODUCTS[0].price,
          status: "Shipped",
          paymentMethod: "UPI (Google Pay)"
        });
        localStorage.setItem('meesho_orders', JSON.stringify(orders));
      }
    }

    // Save to LocalStorage
    function saveState() {
      localStorage.setItem('meesho_cart', JSON.stringify(cart));
      localStorage.setItem('meesho_wishlist', JSON.stringify(wishlist));
      localStorage.setItem('meesho_orders', JSON.stringify(orders));
      localStorage.setItem('meesho_addresses', JSON.stringify(savedAddresses));
      updateBadges();
    }

    function updateBadges() {
      const cartCount = cart.reduce((acc, item) => acc + item.qty, 0);
      const cartBadge = document.getElementById('cart-count');
      const wishBadge = document.getElementById('wishlist-count');

      if (cartBadge) {
        cartBadge.innerText = cartCount;
        cartBadge.style.display = cartCount > 0 ? 'flex' : 'none';
      }
      if (wishBadge) {
        const wishCount = wishlist.length;
        wishBadge.innerText = wishCount;
        wishBadge.style.display = wishCount > 0 ? 'flex' : 'none';
      }

      const cartSheetCount = document.getElementById('cart-sheet-count');
      if (cartSheetCount) cartSheetCount.innerText = cartCount;

      const pdpCartBadge = document.getElementById('pdp-nav-cart-badge');
      if (pdpCartBadge) {
        pdpCartBadge.innerText = cartCount;
        pdpCartBadge.style.display = cartCount > 0 ? 'flex' : 'none';
      }

      const wishSheetCount = document.getElementById('wishlist-sheet-count');
      if (wishSheetCount) wishSheetCount.innerText = wishlist.length;
    }

    // Tab Switching
    function switchTab(tab) {
      currentTab = tab;
      ['home', 'categories', 'orders', 'help'].forEach(t => {
        document.getElementById(`view-${t}`).style.display = (t === tab) ? 'block' : 'none';
        const navEl = document.getElementById(`nav-${t}`);
        if (navEl) navEl.classList.toggle('active', t === tab);
      });
      window.scrollTo(0, 0);
      if (tab === 'categories') {
        renderCategoryProducts();
      } else if (tab === 'orders') {
        renderOrders();
      }
    }

    // Render Category Circle Items (Meesho Arched Image Cards)
    function renderCategoryCircles() {
      const container = document.getElementById('home-category-circles');
      container.innerHTML = CATEGORIES.map(cat => `
        <div class="cat-arch-card" onclick="selectCategoryFromHome('${cat.id}')">
          <div class="cat-arch-thumb">
            <img src="${cat.image}" alt="${cat.name}" loading="lazy" />
          </div>
          <span class="cat-name">${cat.name}</span>
        </div>
      `).join('');
    }

    function selectCategoryFromHome(catId) {
      const cat = CATEGORIES.find(c => c.id === catId);
      if (cat) {
        selectedCategory = cat;
        selectedSubCategory = "All Items";
        switchTab('categories');
        renderCategoriesRail();
        renderCategoryProducts();
      }
    }

    // Render Product Grid
    function renderProducts(productList, containerId) {
      const container = document.getElementById(containerId);
      if (!container) return;

      if (productList.length === 0) {
        container.innerHTML = `<div style="grid-column: span 2; text-align: center; padding: 40px 10px; color: #777;">
          <div style="font-size: 36px;">🔍</div>
          <p style="font-size: 13px; font-weight: bold; margin-top: 8px;">No products found</p>
          <p style="font-size: 11px;">Try searching for sarees, kurtis or tops</p>
        </div>`;
        return;
      }

      container.innerHTML = productList.map(p => {
        const isWish = wishlist.some(w => w.id === p.id);
        const disc = Math.round(((p.originalPrice - p.price) / p.originalPrice) * 100);
        return `
          <div class="product-card" onclick="openProductDetail('${p.id}')">
            <div class="product-thumb-box">
              <img src="${p.imageUrl}" alt="${p.title}" loading="lazy" />
              <button class="wish-btn" onclick="toggleWishlist(event, '${p.id}')">${isWish ? '❤️' : '🤍'}</button>
              ${p.isMeeshoAssured ? '<span class="assured-badge">✓ Trusted</span>' : ''}
            </div>
            <div class="product-info">
              <div>
                <div class="product-title">${p.title}</div>
                <div class="price-row">
                  <span class="cur-price">₹${p.price}</span>
                  <span class="orig-price">₹${p.originalPrice}</span>
                  <span class="disc-percent">${disc}% off</span>
                </div>
                <div class="delivery-tag">Free Delivery</div>
                <div class="rating-pill">★ ${p.rating}</div>
              </div>
            </div>
          </div>
        `;
      }).join('');

      if (containerId === 'home-product-grid') {
        document.getElementById('product-count-label').innerText = `${productList.length} Items`;
      }
    }

    // Real-Time Search Handler
    function handleSearch(query) {
      const q = query.trim().toLowerCase();
      if (!q) {
        renderProducts(PRODUCTS, 'home-product-grid');
        return;
      }
      const filtered = PRODUCTS.filter(p => 
        p.title.toLowerCase().includes(q) ||
        p.category.toLowerCase().includes(q) ||
        p.subCategory.toLowerCase().includes(q) ||
        p.description.toLowerCase().includes(q) ||
        p.fabric.toLowerCase().includes(q)
      );
      renderProducts(filtered, 'home-product-grid');
    }

    // Categories Rail & Main Pane
    function renderCategoriesRail() {
      const rail = document.getElementById('cat-sidebar-list');
      rail.innerHTML = CATEGORIES.map(cat => `
        <div class="cat-side-item ${cat.id === selectedCategory.id ? 'active' : ''}" onclick="pickCategory('${cat.id}')">
          <span class="cat-side-icon">${cat.icon}</span>
          <span>${cat.name}</span>
        </div>
      `).join('');
      renderCategoryProducts();
    }

    function pickCategory(catId) {
      selectedCategory = CATEGORIES.find(c => c.id === catId) || CATEGORIES[0];
      selectedSubCategory = "All Items";
      renderCategoriesRail();
    }

    function renderCategoryProducts() {
      // Banner Box
      const banner = document.getElementById('cat-banner-box');
      banner.innerHTML = `
        <div style="display: flex; justify-content: space-between; align-items: center;">
          <strong style="color: var(--primary); font-size: 14px;">${selectedCategory.name}</strong>
          <span style="background: var(--primary); color: white; font-size: 9px; font-weight: bold; padding: 2px 6px; border-radius: 4px;">From ₹${selectedCategory.startingPrice}</span>
        </div>
        <div style="font-size: 11px; color: #666; margin-top: 3px;">${selectedCategory.info}</div>
      `;

      // Subchips
      const chips = document.getElementById('cat-sub-chips');
      chips.innerHTML = selectedCategory.subs.map(sub => `
        <div class="chip ${sub === selectedSubCategory ? 'active' : ''}" onclick="pickSubCat('${sub}')">${sub}</div>
      `).join('');

      // Products in category
      let list = PRODUCTS.filter(p => p.category === selectedCategory.id);
      if (selectedSubCategory !== "All Items") {
        list = list.filter(p => p.subCategory.toLowerCase().includes(selectedSubCategory.toLowerCase()));
      }
      renderProducts(list, 'cat-product-grid');
    }

    function pickSubCat(sub) {
      selectedSubCategory = sub;
      renderCategoryProducts();
    }

    // Cart Operations
    function addToCart(e, prodId) {
      if (e) e.stopPropagation();
      const prod = PRODUCTS.find(p => p.id === prodId);
      if (!prod) return;

      const existing = cart.find(item => item.id === prodId);
      if (existing) {
        existing.qty += 1;
      } else {
        cart.push({ ...prod, qty: 1 });
      }
      saveState();
      renderCartSheet();
      showToast(`Added to cart: ${prod.title.slice(0, 24)}...`);
    }

    function openCart() {
      renderCartSheet();
      document.getElementById('cart-sheet').classList.add('open');
    }

    function closeCart() {
      document.getElementById('cart-sheet').classList.remove('open');
    }

    function renderCartSheet() {
      const container = document.getElementById('cart-items-container');
      if (cart.length === 0) {
        if (orders && orders.length > 0) {
          const ordersHtml = orders.map(ord => `
            <div class="order-card" style="margin-bottom: 12px; background: white; border: 1px solid var(--border); border-radius: 8px; padding: 12px;">
              <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid var(--border); padding-bottom: 8px; margin-bottom: 8px;">
                <div>
                  <div style="font-size: 12px; font-weight: 800; color: #333;">${ord.orderId}</div>
                  <div style="font-size: 10px; color: #888;">Placed on ${ord.date} • ${ord.paymentMethod}</div>
                </div>
                <span class="order-status-pill" style="background: #E8F5E9; color: #2E7D32; border: 1px solid #A5D6A7; font-size: 10px; padding: 2px 6px; border-radius: 4px; font-weight: bold;">${ord.status}</span>
              </div>
              <div>
                ${(ord.items || []).map(it => `
                  <div style="display: flex; gap: 8px; align-items: center; margin-bottom: 6px;">
                    <img src="${it.imageUrl || it.image}" style="width: 44px; height: 44px; border-radius: 4px; object-fit: cover;" />
                    <div style="flex: 1;">
                      <div style="font-size: 12px; font-weight: 600; line-height: 15px;">${it.title}</div>
                      <div style="font-size: 12px; font-weight: bold; color: #111;">₹${it.price} <span style="font-size: 10px; color: #888; font-weight: normal;">Qty: ${it.qty || 1}</span></div>
                    </div>
                  </div>
                `).join('')}
              </div>
              <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 8px; padding-top: 8px; border-top: 1px dashed var(--border);">
                <div style="font-size: 12px; font-weight: 800;">Total Paid: <span style="color: var(--primary);">₹${ord.total}</span></div>
                <button onclick="alert('Order Status: Confirmed & Under Packing. Expected delivery in 3-4 days.')" style="background: none; border: 1px solid var(--primary); color: var(--primary); padding: 4px 8px; border-radius: 4px; font-size: 10px; font-weight: bold; cursor: pointer;">Track Order</button>
              </div>
            </div>
          `).join('');

          container.innerHTML = `
            <div style="padding: 6px 0;">
              <div style="background: #E8F5E9; border: 1px solid #C8E6C9; border-radius: 8px; padding: 12px; margin-bottom: 12px; display: flex; align-items: center; justify-content: space-between;">
                <div>
                  <div style="font-size: 13px; font-weight: 700; color: #2E7D32;">🎉 Order Placed in Real-Time!</div>
                  <div style="font-size: 11px; color: #555; margin-top: 2px;">Your order has been confirmed successfully.</div>
                </div>
                <button onclick="closeCart(); switchTab('home');" style="background: var(--primary); color: white; border: none; padding: 6px 12px; border-radius: 4px; font-size: 11px; font-weight: bold; cursor: pointer;">Shop More</button>
              </div>
              <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                <div style="font-size: 13px; font-weight: 800; color: #222;">📦 Placed Orders in Cart:</div>
                <span style="font-size: 11px; color: var(--primary); font-weight: 600; cursor: pointer;" onclick="closeCart(); switchTab('orders');">View in My Orders →</span>
              </div>
              ${ordersHtml}
            </div>
          `;

          const lastOrd = orders[0];
          if (document.getElementById('bill-subtotal')) document.getElementById('bill-subtotal').innerText = `₹${lastOrd.total}`;
          if (document.getElementById('bill-total')) document.getElementById('bill-total').innerText = `₹${lastOrd.total}`;
          if (document.getElementById('bottom-total-price')) document.getElementById('bottom-total-price').innerText = `₹${lastOrd.total} (Paid)`;

          const btn = document.getElementById('stepper-continue-btn');
          if (btn) {
            btn.disabled = false;
            btn.style.opacity = '1';
            btn.innerText = 'Shop More Products';
            btn.onclick = function() { closeCart(); switchTab('home'); };
          }
          return;
        }

        container.innerHTML = `
          <div style="text-align: center; padding: 40px 14px; color: #888;">
            <div style="font-size: 40px;">🛒</div>
            <h4 style="margin-top: 8px; font-size: 14px; color: #333;">Your Cart is Empty</h4>
            <p style="font-size: 11px; margin-top: 4px;">Explore lowest wholesale price collections now!</p>
            <button onclick="closeCart(); switchTab('home');" style="margin-top: 14px; background: var(--primary); color: white; border: none; padding: 8px 16px; border-radius: 6px; font-size: 12px; font-weight: bold; cursor: pointer;">Start Shopping</button>
          </div>
        `;
        if (document.getElementById('bill-subtotal')) document.getElementById('bill-subtotal').innerText = '₹0';
        if (document.getElementById('bill-subtotal-summary')) document.getElementById('bill-subtotal-summary').innerText = '₹0';

        if (document.getElementById('bill-total')) document.getElementById('bill-total').innerText = '₹0';
        if (document.getElementById('bill-total-summary')) document.getElementById('bill-total-summary').innerText = '₹0';
        if (document.getElementById('bottom-total-price')) document.getElementById('bottom-total-price').innerText = '₹0';

        document.getElementById('stepper-continue-btn').disabled = true;
        document.getElementById('stepper-continue-btn').style.opacity = '0.5';
        return;
      }

      document.getElementById('stepper-continue-btn').disabled = false;
      document.getElementById('stepper-continue-btn').style.opacity = '1';

      container.innerHTML = cart.map(item => `
        <div class="cart-item">
          <img src="${item.imageUrl}" class="cart-thumb" alt="${item.title}" />
          <div class="cart-details">
            <div>
              <div style="font-size: 12px; font-weight: 600; line-height: 15px;">${item.title}</div>
              <div style="font-size: 13px; font-weight: 800; color: #111; margin-top: 4px;">₹${item.price} <span style="font-size: 10px; color: #888; text-decoration: line-through;">₹${item.originalPrice}</span></div>
            </div>
            <div class="qty-controls">
              <button class="qty-btn" onclick="updateQty('${item.id}', -1)">-</button>
              <span style="font-size: 12px; font-weight: bold; min-width: 18px; text-align: center;">${item.qty}</span>
              <button class="qty-btn" onclick="updateQty('${item.id}', 1)">+</button>
              <button onclick="removeCartItem('${item.id}')" style="margin-left: auto; background: none; border: none; color: #D32F2F; font-size: 11px; cursor: pointer; font-weight: 600;">Remove</button>
            </div>
          </div>
        </div>
      `).join('');

      const total = cart.reduce((acc, item) => acc + (item.price * item.qty), 0);
      if (document.getElementById('bill-subtotal')) document.getElementById('bill-subtotal').innerText = `₹${total}`;
      const mrp = cart.reduce((acc, item) => acc + (item.originalPrice * item.qty), 0);
      const discount = mrp - total;
      
      const elSubSum = document.getElementById('bill-subtotal-summary');
      if (elSubSum) elSubSum.innerText = `₹${mrp}`;
      
      const elDiscount = document.querySelector('#cart-step-4 .bill-card .bill-row:nth-child(3) span:last-child');
      if (elDiscount) elDiscount.innerText = `- ₹${discount}`;

      if (document.getElementById('bill-total')) document.getElementById('bill-total').innerText = `₹${total}`;
      const elTotalSum = document.getElementById('bill-total-summary');
      if (elTotalSum) elTotalSum.innerText = `₹${total}`;
      const elBottom = document.getElementById('bottom-total-price');
      if (elBottom) elBottom.innerText = `₹${total}`;

      updateDeliverToDisplay();
    }

    function updateQty(id, delta) {
      const item = cart.find(i => i.id === id);
      if (!item) return;
      item.qty += delta;
      if (item.qty <= 0) {
        cart = cart.filter(i => i.id !== id);
      }
      saveState();
      renderCartSheet();
    }

    function removeCartItem(id) {
      cart = cart.filter(i => i.id !== id);
      saveState();
      renderCartSheet();
    }

    // Payment Selection Handler (Prepaid Only - No COD)
    function selectPayment(method) {
      selectedPayment = method;
      document.querySelectorAll('.pay-option').forEach(el => el.classList.remove('selected'));
      if (method === 'UPI') document.getElementById('pay-upi').classList.add('selected');
      if (method === 'Debit / Credit Card') document.getElementById('pay-card').classList.add('selected');
      if (method === 'Net Banking') document.getElementById('pay-netbanking').classList.add('selected');
      if (method === 'Wallet') document.getElementById('pay-wallet').classList.add('selected');
    }

    // Reselling Calculations
    function toggleResell(checked) {
      document.getElementById('resell-input-box').style.display = checked ? 'block' : 'none';
      if (!checked) document.getElementById('resell-margin-result').innerText = '';
    }

    function calculateResellMargin() {
      const val = parseInt(document.getElementById('resell-price-input').value) || 0;
      const total = cart.reduce((acc, item) => acc + (item.price * item.qty), 0);
      const res = document.getElementById('resell-margin-result');
      if (val > total) {
        res.innerText = `Your Profit / Margin: ₹${val - total} (will be deposited to your bank account)`;
      } else if (val > 0) {
        res.innerText = `Customer price must be greater than order total (₹${total})`;
      } else {
        res.innerText = '';
      }
    }

    // Address Management (Meesho Style Matching Screenshots)
    function getSelectedAddress() {
      return savedAddresses.find(a => a.selected) || savedAddresses[0];
    }

    function formatAddressSingleLine(addr) {
      if (!addr) return "Please provide an address";
      const parts = [
        addr.house,
        addr.area,
        addr.cityState ? `${addr.cityState} - ${addr.pincode}` : addr.pincode
      ].filter(Boolean).join(', ');
      return `${addr.name} • ${parts}`;
    }

    function updateDeliverToDisplay() {
      const active = getSelectedAddress();
      const el = document.getElementById('delivery-address-text');
      if (el && active) {
        el.innerText = formatAddressSingleLine(active);
      }
    }

    function openChangeAddressSheet() {
      renderSavedAddresses();
      document.getElementById('change-address-sheet').classList.add('open');
    }

    function editAddress() {
      openChangeAddressSheet();
    }

    function toggleAddAddressForm() {
      const box = document.getElementById('new-address-form-box');
      if (box.style.display === 'block') {
        box.style.display = 'none';
      } else {
        box.style.display = 'block';
        document.getElementById('new-addr-name').value = '';
        document.getElementById('new-addr-phone').value = '';
        document.getElementById('new-addr-house').value = '';
        document.getElementById('new-addr-area').value = '';
        document.getElementById('new-addr-pincode').value = '';
        document.getElementById('new-addr-city').value = '';
        document.getElementById('new-addr-name').focus();
      }
    }

    function saveNewAddress() {
      const name = (document.getElementById('new-addr-name').value || '').trim();
      const phone = (document.getElementById('new-addr-phone').value || '').trim();
      const house = (document.getElementById('new-addr-house').value || '').trim();
      const area = (document.getElementById('new-addr-area').value || '').trim();
      const pincode = (document.getElementById('new-addr-pincode').value || '').trim();
      const city = (document.getElementById('new-addr-city').value || '').trim();

      if (!name || !house || !area || !pincode) {
        alert('Please fill in required fields (Name, House/Flat, Area, Pincode)');
        return;
      }

      savedAddresses.forEach(a => a.selected = false);
      const newAddr = {
        id: "addr_" + Date.now(),
        name: name,
        phone: phone || "9876543210",
        house: house,
        area: area,
        cityState: city || "Surat, Gujarat",
        pincode: pincode,
        selected: true
      };

      savedAddresses.unshift(newAddr);
      saveState();
      updateDeliverToDisplay();
      document.getElementById('new-address-form-box').style.display = 'none';
      closeSheet('change-address-sheet');
      showToast('Delivery address updated successfully');
    }

    function selectAddress(id) {
      savedAddresses.forEach(a => a.selected = (a.id === id));
      saveState();
      updateDeliverToDisplay();
      closeSheet('change-address-sheet');
      showToast('Delivery address changed');
    }

    function renderSavedAddresses() {
      const container = document.getElementById('saved-addresses-container');
      if (!container) return;

      if (savedAddresses.length === 0) {
        container.innerHTML = `<div style="text-align: center; padding: 20px; color: #888; font-size: 13px;">No saved addresses. Click '+ ADD NEW ADDRESS' above.</div>`;
        return;
      }

      container.innerHTML = savedAddresses.map(addr => `
        <div class="address-item-card ${addr.selected ? 'selected' : ''}" onclick="selectAddress('${addr.id}')">
          <div class="address-radio">
            <div class="address-radio-inner"></div>
          </div>
          <div style="flex: 1;">
            <div class="address-item-name">${addr.name}</div>
            <div class="address-item-details">${addr.house}, ${addr.area}, ${addr.cityState} - ${addr.pincode}</div>
            <div class="address-item-phone">📱 ${addr.phone || '9876543210'}</div>
          </div>
        </div>
      `).join('');
    }

    // Checkout & Order Placement
    function executeCheckout() {
      if (cart.length === 0) return;

      const orderTotal = cart.reduce((acc, item) => acc + (item.price * item.qty), 0);
      const newOrderId = "MEESHO-ORD-" + Math.floor(100000 + Math.random() * 900000);
      const newOrder = {
        orderId: newOrderId,
        date: "Just Now",
        items: [...cart],
        total: orderTotal,
        status: "Confirmed (Prepaid)",
        paymentMethod: selectedPayment
      };

      orders.unshift(newOrder);
      cart = [];
      saveState();
      renderOrders();
      renderCartSheet();

      // Close all overlays/sheets completely
      document.querySelectorAll('.sheet-overlay, .pdp-fullpage').forEach(el => el.classList.remove('open'));
      currentCheckoutStep = 1;
      if (typeof updateStepperUI === 'function') updateStepperUI();

      // Switch directly to Home tab and scroll to top
      switchTab('home');
      window.scrollTo(0, 0);

      alert(`🎉 Order Confirmed Successfully!\n\nOrder ID: ${newOrderId}\nTotal: ₹${orderTotal}\nPayment: ${selectedPayment} (Prepaid Verified)\n\nThank you for shopping on Meesho!`);
    }

    // Orders Screen Rendering
    let ordersSearchQuery = '';

    function filterOrders(query) {
      ordersSearchQuery = (query || '').trim().toLowerCase();
      renderOrders();
    }

    function openOrdersFilterSheet() {
      showToast('Filters: All Orders, Delivered, Shipped, In-Transit');
    }

    function renderOrders() {
      const container = document.getElementById('orders-list-container');
      const filtered = orders.filter(ord => {
        if (!ordersSearchQuery) return true;
        const matchesId = ord.orderId.toLowerCase().includes(ordersSearchQuery);
        const matchesStatus = (ord.status || '').toLowerCase().includes(ordersSearchQuery);
        const matchesItem = ord.items && ord.items.some(it => (it.title || '').toLowerCase().includes(ordersSearchQuery));
        return matchesId || matchesStatus || matchesItem;
      });

      if (filtered.length === 0) {
        container.innerHTML = `
          <div style="text-align: center; padding: 50px 14px; color: #888;">
            <div style="font-size: 40px;">📦</div>
            <h4 style="margin-top: 8px; font-size: 14px; color: #333;">${ordersSearchQuery ? 'No Matching Orders' : 'No Orders Yet'}</h4>
            <p style="font-size: 11px; margin-top: 4px;">${ordersSearchQuery ? 'Try searching by a different order ID or product name.' : 'You haven\'t placed any orders yet.'}</p>
          </div>
        `;
        return;
      }

      container.innerHTML = filtered.map(ord => `
        <div class="order-card">
          <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid var(--border); padding-bottom: 8px; margin-bottom: 8px;">
            <div>
              <div style="font-size: 11px; font-weight: 800; color: #333;">${ord.orderId}</div>
              <div style="font-size: 10px; color: #888;">Placed on ${ord.date} • ${ord.paymentMethod}</div>
            </div>
            <span class="order-status-pill">${ord.status}</span>
          </div>
          <div>
            ${ord.items.map(it => `
              <div style="display: flex; gap: 8px; align-items: center; margin-bottom: 6px;">
                <img src="${it.imageUrl || it.image}" style="width: 42px; height: 42px; border-radius: 4px; object-fit: cover;" />
                <div style="flex: 1;">
                  <div style="font-size: 11px; font-weight: 600; line-height: 14px;">${it.title}</div>
                  <div style="font-size: 11px; color: #555;">₹${it.price} × ${it.qty || 1}</div>
                </div>
              </div>
            `).join('')}
          </div>
          <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 8px; padding-top: 8px; border-top: 1px dashed var(--border);">
            <div style="font-size: 12px; font-weight: 800;">Total Paid: <span style="color: var(--primary);">₹${ord.total}</span></div>
            <button onclick="alert('Order tracking status: On Schedule. Delivery expected within 3-4 working days.')" style="background: none; border: 1px solid var(--primary); color: var(--primary); padding: 4px 8px; border-radius: 4px; font-size: 10px; font-weight: bold; cursor: pointer;">Track Package</button>
          </div>
        </div>
      `).join('');
    }

    // Wishlist Operations
    function toggleWishlist(e, prodId) {
      if (e) e.stopPropagation();
      const prod = PRODUCTS.find(p => p.id === prodId);
      if (!prod) return;

      const idx = wishlist.findIndex(w => w.id === prodId);
      if (idx >= 0) {
        wishlist.splice(idx, 1);
        showToast("Removed from wishlist");
      } else {
        wishlist.push(prod);
        showToast("Added to wishlist ❤️");
      }
      saveState();
      if (currentTab === 'home') renderProducts(PRODUCTS, 'home-product-grid');
      if (currentTab === 'categories') renderCategoryProducts();
      renderWishlistSheet();
    }

    function openWishlist() {
      renderWishlistSheet();
      document.getElementById('wishlist-sheet').classList.add('open');
    }

    function closeWishlist() {
      document.getElementById('wishlist-sheet').classList.remove('open');
    }

    function renderWishlistSheet() {
      const container = document.getElementById('wishlist-items-container');
      if (wishlist.length === 0) {
        container.innerHTML = `
          <div style="text-align: center; padding: 40px 14px; color: #888;">
            <div style="font-size: 40px;">❤️</div>
            <h4 style="margin-top: 8px; font-size: 14px; color: #333;">Wishlist is Empty</h4>
            <p style="font-size: 11px; margin-top: 4px;">Save your favorite sarees, kurtis and dresses here.</p>
          </div>
        `;
        return;
      }

      container.innerHTML = wishlist.map(p => `
        <div class="cart-item">
          <img src="${p.imageUrl}" class="cart-thumb" alt="${p.title}" />
          <div class="cart-details">
            <div>
              <div style="font-size: 12px; font-weight: 600;">${p.title}</div>
              <div style="font-size: 13px; font-weight: 800; margin-top: 4px;">₹${p.price}</div>
            </div>
            <div style="display: flex; gap: 8px; margin-top: 6px;">
              <button onclick="addToCart(null, '${p.id}'); toggleWishlist(null, '${p.id}');" style="background: var(--primary); color: white; border: none; padding: 4px 10px; border-radius: 4px; font-size: 11px; font-weight: bold; cursor: pointer;">Move to Cart</button>
              <button onclick="toggleWishlist(null, '${p.id}')" style="background: none; border: 1px solid #CCC; padding: 4px 8px; border-radius: 4px; font-size: 11px; cursor: pointer;">Remove</button>
            </div>
          </div>
        </div>
      `).join('');
    }

    // Current PDP state
    let currentPdpProduct = null;
    let selectedPdpSize = null;

    // Open Full-Page Product Detail View
    function openProductDetail(prodId) {
      const p = PRODUCTS.find(prod => prod.id === prodId);
      if (!p) return;

      currentPdpProduct = p;

      // Determine available sizes based on category/title
      let availableSizes = ['Free Size'];
      const titleLower = p.title.toLowerCase();
      const subLower = (p.subCategory || '').toLowerCase();
      if (subLower.includes('jeans') || titleLower.includes('jeans')) {
        availableSizes = ['28', '30', '32', '34', '36'];
      } else if (p.category === 'footwear') {
        availableSizes = ['IND-5', 'IND-6', 'IND-7', 'IND-8', 'IND-9'];
      } else if (p.category === 'western' || p.category === 'men' || p.category === 'kids') {
        availableSizes = ['S', 'M', 'L', 'XL', 'XXL'];
      } else if (p.category === 'ethnic') {
        availableSizes = ['Free Size', 'S', 'M', 'L', 'XL'];
      }
      selectedPdpSize = availableSizes[0];

      // Update Top Nav Header
      const headerTitle = document.getElementById('sheet-prod-category');
      if (headerTitle) headerTitle.innerText = p.subCategory || p.category.toUpperCase();

      const isWish = wishlist.some(w => w.id === p.id);
      const wishNavBtn = document.getElementById('pdp-nav-wish-btn');
      if (wishNavBtn) wishNavBtn.innerHTML = isWish ? '❤️' : '🤍';

      const cartBadge = document.getElementById('pdp-nav-cart-badge');
      if (cartBadge) {
        const totalItems = cart.reduce((acc, item) => acc + item.qty, 0);
        cartBadge.innerText = totalItems;
        cartBadge.style.display = totalItems > 0 ? 'flex' : 'none';
      }

      // Ratings & Reviews calculation matching Screenshot 2
      let totalRatings = 59996;
      let totalReviews = 17029;
      let countExc = 35191;
      let countVg = 11708;
      let countGood = 5840;
      let countAvg = 1994;
      let countPoor = 5263;

      if (p.id !== 'm3') {
        const factor = Math.max(1, Math.round(p.reviewCount / 6500));
        totalRatings = Math.round(59996 * factor);
        totalReviews = Math.round(17029 * factor);
        countExc = Math.round(35191 * factor);
        countVg = Math.round(11708 * factor);
        countGood = Math.round(5840 * factor);
        countAvg = Math.round(1994 * factor);
        countPoor = Math.round(5263 * factor);
      }

      const disc = Math.round(((p.originalPrice - p.price) / p.originalPrice) * 100);
      const body = document.getElementById('product-sheet-body');

      body.innerHTML = `
        <!-- Main Product Image Frame -->
        <div class="pdp-img-frame">
          <img src="${p.imageUrl}" class="pdp-main-photo" alt="${p.title}" />
          <div class="pdp-photo-counter">1/3</div>
        </div>

        <!-- Product Title & Pricing Card -->
        <div class="pdp-section-card">
          <div class="pdp-product-title">${p.title}</div>
          <div class="pdp-price-strip">
            <span class="pdp-now-price">₹${p.price}</span>
            <span class="pdp-strike-price">₹${p.originalPrice}</span>
            <span class="pdp-discount-badge">${disc}% off</span>
          </div>

          <div class="pdp-summary-row">
            <span class="pdp-green-pill">★ ${p.rating}</span>
            <span class="pdp-reviews-text">${totalRatings.toLocaleString()} Ratings, ${totalReviews.toLocaleString()} Reviews</span>
            <span class="pdp-free-tag">Free Delivery</span>
          </div>

          <div class="pdp-assured-strip">
            <svg width="15" height="15" viewBox="0 0 24 24" fill="none" stroke="#166534" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round">
              <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path>
            </svg>
            <span>Meesho Assured • Direct from Verified Hubs • 7 Days Return</span>
          </div>
        </div>

        <!-- Select Size Card -->
        <div class="pdp-section-card">
          <div class="pdp-size-header-row">
            <span class="pdp-subheading">Select Size</span>
            <button type="button" class="pdp-size-chart-btn" onclick="showToast('Size chart is standard Indian fit')">Size Chart</button>
          </div>
          <div class="pdp-size-chips-wrap" id="pdp-size-chips-container">
            ${availableSizes.map(sz => `
              <button type="button" class="pdp-size-chip-btn ${sz === selectedPdpSize ? 'selected' : ''}" onclick="selectPdpSize('${sz}')">
                ${sz}
              </button>
            `).join('')}
          </div>
        </div>

        <!-- Product Highlights Card -->
        <div class="pdp-section-card">
          <span class="pdp-subheading">Product Highlights</span>
          <table class="pdp-highlights-table">
            <tbody>
              <tr>
                <td class="pdp-highlights-label">Fabric</td>
                <td class="pdp-highlights-val">${p.fabric || 'Premium Stretch Denim'}</td>
              </tr>
              <tr>
                <td class="pdp-highlights-label">Pattern</td>
                <td class="pdp-highlights-val">${p.pattern || 'Clean Solid'}</td>
              </tr>
              <tr>
                <td class="pdp-highlights-label">Fit / Type</td>
                <td class="pdp-highlights-val">${p.subCategory || 'Regular'}</td>
              </tr>
              <tr>
                <td class="pdp-highlights-label">Length</td>
                <td class="pdp-highlights-val">Standard Full Length</td>
              </tr>
              <tr>
                <td class="pdp-highlights-label">Occasion</td>
                <td class="pdp-highlights-val">Casual / Daily Wear</td>
              </tr>
              <tr>
                <td class="pdp-highlights-label">Multipack</td>
                <td class="pdp-highlights-val">Single (1 Unit)</td>
              </tr>
              <tr>
                <td class="pdp-highlights-label">Country of Origin</td>
                <td class="pdp-highlights-val">India (Make in India)</td>
              </tr>
              <tr>
                <td class="pdp-highlights-label">Dispatch</td>
                <td class="pdp-highlights-val">Within 1 Day</td>
              </tr>
            </tbody>
          </table>

          <div class="pdp-desc-text">
            <strong>Product Description:</strong><br/>
            ${p.description}
          </div>
        </div>

        <!-- Sold By Card -->
        <div class="pdp-section-card">
          <span style="font-size: 12px; color: #717b8f; font-weight: 500;">Sold By</span>
          <div class="pdp-supplier-card-body">
            <div>
              <div class="pdp-supplier-title">${p.supplierName} (Verified)</div>
              <div class="pdp-supplier-meta">
                <span class="pdp-green-pill" style="font-size: 10.5px; padding: 2px 6px;">4.2 ★</span>
                <span>24,500+ Followers • 180+ Products</span>
              </div>
            </div>
            <button type="button" class="pdp-view-shop-outline-btn" onclick="showToast('Viewing shop for ${p.supplierName}')">View Shop</button>
          </div>

          <div class="pdp-supplier-perks">
            <div class="pdp-perk-item">
              <span style="color: var(--rating-green);">✓</span> 100% Original
            </div>
            <div class="pdp-perk-item">
              <span style="color: var(--rating-green);">✓</span> Verified Supplier
            </div>
            <div class="pdp-perk-item">
              <span style="color: var(--rating-green);">✓</span> Fast Dispatch
            </div>
          </div>
        </div>

        <!-- Product Ratings & Reviews Card - EXACT MATCH WITH IMAGE 2 -->
        <div class="pdp-reviews-box">
          <div class="pdp-reviews-heading">Product Ratings & Reviews</div>

          <div class="pdp-reviews-grid-row">
            <!-- Left Score Column -->
            <div class="pdp-rating-left-stat">
              <div class="pdp-hero-score">
                <span>${p.rating}</span>
                <span class="pdp-hero-score-star">★</span>
              </div>
              <div class="pdp-hero-counts-col">
                <div>${totalRatings} Ratings,</div>
                <div>${totalReviews} Reviews</div>
              </div>
            </div>

            <!-- Right Breakdown Bars Column -->
            <div class="pdp-rating-right-bars">
              <!-- Excellent -->
              <div class="pdp-bar-item-row">
                <span class="pdp-bar-label">Excellent</span>
                <div class="pdp-bar-track">
                  <div class="pdp-bar-fill" style="width: 72%; background-color: #10b981;"></div>
                </div>
                <span class="pdp-bar-count-num">${countExc}</span>
              </div>

              <!-- Very Good -->
              <div class="pdp-bar-item-row">
                <span class="pdp-bar-label">Very Good</span>
                <div class="pdp-bar-track">
                  <div class="pdp-bar-fill" style="width: 26%; background-color: #22c55e;"></div>
                </div>
                <span class="pdp-bar-count-num">${countVg}</span>
              </div>

              <!-- Good -->
              <div class="pdp-bar-item-row">
                <span class="pdp-bar-label">Good</span>
                <div class="pdp-bar-track">
                  <div class="pdp-bar-fill" style="width: 14%; background-color: #f59e0b;"></div>
                </div>
                <span class="pdp-bar-count-num">${countGood}</span>
              </div>

              <!-- Average -->
              <div class="pdp-bar-item-row">
                <span class="pdp-bar-label">Average</span>
                <div class="pdp-bar-track">
                  <div class="pdp-bar-fill" style="width: 5%; background-color: #f97316;"></div>
                </div>
                <span class="pdp-bar-count-num">${countAvg}</span>
              </div>

              <!-- Poor -->
              <div class="pdp-bar-item-row">
                <span class="pdp-bar-label">Poor</span>
                <div class="pdp-bar-track">
                  <div class="pdp-bar-fill" style="width: 12%; background-color: #ef4444;"></div>
                </div>
                <span class="pdp-bar-count-num">${countPoor}</span>
              </div>
            </div>
          </div>

          <!-- Customer Reviews List -->
          <div class="pdp-user-reviews-list">
            <div class="pdp-single-review">
              <div class="pdp-single-review-header">
                <div class="pdp-single-review-user">
                  <span>Pooja Sharma</span>
                  <span class="pdp-verified-check">✓ Verified Buyer</span>
                </div>
                <span class="pdp-single-review-date">12 Sep 2024</span>
              </div>
              <div class="pdp-single-review-rating-row">
                <span class="pdp-green-pill" style="font-size: 11px; padding: 2px 6px;">★ 5</span>
              </div>
              <div class="pdp-single-review-comment">Fitting is awesome and fabric quality is very good at this price range. Must buy!</div>
            </div>

            <div class="pdp-single-review">
              <div class="pdp-single-review-header">
                <div class="pdp-single-review-user">
                  <span>Ritu Verma</span>
                  <span class="pdp-verified-check">✓ Verified Buyer</span>
                </div>
                <span class="pdp-single-review-date">08 Sep 2024</span>
              </div>
              <div class="pdp-single-review-rating-row">
                <span class="pdp-green-pill" style="font-size: 11px; padding: 2px 6px;">★ 4</span>
              </div>
              <div class="pdp-single-review-comment">Loved the quality and design. Same as shown in the picture, perfect fit for regular use.</div>
            </div>
          </div>
        </div>
      `;

      // Open PDP Fullpage Screen
      document.getElementById('product-sheet').classList.add('open');
      body.scrollTop = 0;
    }

    function selectPdpSize(sz) {
      selectedPdpSize = sz;
      const chips = document.querySelectorAll('.pdp-size-chip-btn');
      chips.forEach(btn => {
        if (btn.innerText.trim() === sz) {
          btn.classList.add('selected');
        } else {
          btn.classList.remove('selected');
        }
      });
      showToast(`Selected Size: ${sz}`);
    }

    function addCurrentProductToCart() {
      if (!currentPdpProduct) return;
      addToCart(null, currentPdpProduct.id);
      showToast(`Added to Cart (${selectedPdpSize || 'Free Size'})`);
      const cartBadge = document.getElementById('pdp-nav-cart-badge');
      if (cartBadge) {
        const totalItems = cart.reduce((acc, item) => acc + item.qty, 0);
        cartBadge.innerText = totalItems;
        cartBadge.style.display = totalItems > 0 ? 'flex' : 'none';
      }
    }

    function buyCurrentProductNow() {
      if (!currentPdpProduct) return;
      addToCart(null, currentPdpProduct.id);
      closeProductSheet();
      openCart();
    }

    function toggleCurrentWishlist(e) {
      if (!currentPdpProduct) return;
      toggleWishlist(e, currentPdpProduct.id);
      const isWish = wishlist.some(w => w.id === currentPdpProduct.id);
      const wishNavBtn = document.getElementById('pdp-nav-wish-btn');
      if (wishNavBtn) wishNavBtn.innerHTML = isWish ? '❤️' : '🤍';
    }

    function shareCurrentProduct() {
      if (!currentPdpProduct) return;
      if (navigator.share) {
        navigator.share({
          title: currentPdpProduct.title,
          text: `Check out ${currentPdpProduct.title} on Meesho at just ₹${currentPdpProduct.price}!`,
          url: window.location.href
        }).catch(() => {});
      } else {
        showToast('Product link copied to clipboard!');
      }
    }

    function closeProductSheet() {
      document.getElementById('product-sheet').classList.remove('open');
      currentPdpProduct = null;
    }

    function closeSheetOnBackdrop(e, id) {
      if (e.target.id === id) {
        document.getElementById(id).classList.remove('open');
      }
    }

    function showToast(msg) {
      const toast = document.getElementById('toast');
      toast.innerText = msg;
      toast.classList.add('show');
      setTimeout(() => toast.classList.remove('show'), 2200);
    }

    // Launch application on load
    window.addEventListener('DOMContentLoaded', init);
  
  // Hook openCart to reset stepper
  const originalOpenCartHook = window.openCart;
  window.openCart = function() {
      currentCheckoutStep = 1;
      updateStepperUI();
      originalOpenCartHook();
  };

  // Checkout Stepper Logic
  let currentCheckoutStep = 1;
  const totalSteps = 4;

  function updateStepperUI() {
    // Titles
    const titleEl = document.getElementById('cart-sheet-title');
    if (currentCheckoutStep === 1) {
      if (cart.length === 0 && orders && orders.length > 0) {
        titleEl.innerHTML = `Shopping Cart • Placed Orders (${orders.length})`;
      } else {
        titleEl.innerHTML = `Shopping Cart (<span id="cart-sheet-count">${cart.length}</span>)`;
      }
    }
    else if (currentCheckoutStep === 2) titleEl.innerText = 'ADD DELIVERY ADDRESS';
    else if (currentCheckoutStep === 3) titleEl.innerText = 'PAYMENT';
    else if (currentCheckoutStep === 4) titleEl.innerText = 'ORDER SUMMARY';

    // Steps rendering
    for (let i = 1; i <= totalSteps; i++) {
        const circle = document.getElementById(`step-circle-${i}`);
        const label = document.getElementById(`step-label-${i}`);
        const line = document.getElementById(`step-line-${i}`);
        const stepContent = document.getElementById(`cart-step-${i}`);
        
        if (circle) {
            if (i < currentCheckoutStep) {
                // Completed
                circle.style.background = '#5C83F6';
                circle.style.borderColor = '#5C83F6';
                circle.style.color = 'white';
                circle.innerHTML = '✔';
                label.style.color = '#333';
                label.style.fontWeight = 'bold';
            } else if (i === currentCheckoutStep) {
                // Active
                circle.style.background = '#5C83F6';
                circle.style.borderColor = '#5C83F6';
                circle.style.color = 'white';
                circle.innerHTML = i;
                label.style.color = '#333';
                label.style.fontWeight = 'bold';
            } else {
                // Pending
                circle.style.background = 'white';
                circle.style.borderColor = '#D1D5DB';
                circle.style.color = '#D1D5DB';
                circle.innerHTML = i;
                label.style.color = 'gray';
                label.style.fontWeight = 'normal';
            }
        }
        
        if (line) {
            if (i < currentCheckoutStep) {
                line.style.background = '#5C83F6';
            } else {
                line.style.background = '#D1D5DB';
            }
        }
        
        // Content visibility
        if (stepContent) {
            stepContent.style.display = (i === currentCheckoutStep) ? 'block' : 'none';
        }
    }

    // Button states
    const btn = document.getElementById('stepper-continue-btn');
    const priceInfo = document.getElementById('bottom-price-info');
    if (currentCheckoutStep === 1) {
        if (cart.length === 0 && orders && orders.length > 0) {
            btn.innerText = 'Shop More Products';
            btn.disabled = false;
            btn.style.opacity = '1';
            btn.style.width = 'auto';
            priceInfo.style.display = 'flex';
            btn.style.background = 'var(--primary)';
            btn.onclick = function() { closeCart(); switchTab('home'); };
        } else {
            btn.innerText = 'Continue';
            btn.disabled = cart.length === 0;
            btn.style.opacity = cart.length === 0 ? '0.5' : '1';
            btn.style.width = 'auto';
            priceInfo.style.display = 'flex';
            btn.style.background = 'var(--primary)';
            btn.onclick = function() { nextCheckoutStep(); };
        }
    } else if (currentCheckoutStep === 2) {
        btn.innerText = 'Save Address and Continue';
        btn.style.width = '100%';
        priceInfo.style.display = 'none';
        btn.style.background = '#900C3F';
    } else if (currentCheckoutStep === 3) {
        btn.innerText = 'Continue';
        btn.style.width = 'auto';
        priceInfo.style.display = 'flex';
        btn.style.background = 'var(--primary)';
    } else if (currentCheckoutStep === 4) {
        btn.innerText = 'Place Order';
        btn.style.width = 'auto';
        priceInfo.style.display = 'flex';
        btn.style.background = 'var(--primary)';
    }
  }

  function goToStep(step) {
      if (step >= 1 && step <= totalSteps) {
          currentCheckoutStep = step;
          
          if (step === 4) {
              // Update summary fields
              const name = document.getElementById('addr-name').value;
              const house = document.getElementById('addr-house').value;
              const road = document.getElementById('addr-road').value;
              const city = document.getElementById('addr-city').value;
              const state = document.getElementById('addr-state').value;
              const pin = document.getElementById('addr-pin').value;
              const phone = document.getElementById('addr-phone').value;
              
              document.getElementById('summary-address-text').innerText = `${name} • ${house}, ${road}, ${city}, ${state} - ${pin} • Mobile: ${phone}`;
              
              const selectedPay = document.querySelector('input[name="payment-method"]:checked').parentElement.querySelector('h4').innerText;
              document.getElementById('summary-payment-text').innerText = selectedPay;
          }
          
          updateStepperUI();
      }
  }

  function nextCheckoutStep() {
      if (currentCheckoutStep === 4) {
          executeCheckout();
      } else {
          goToStep(currentCheckoutStep + 1);
      }
  }

  // Override closeCart
  const originalCloseCart = window.closeCart;
  window.closeCart = function() {
      if (currentCheckoutStep > 1) {
          goToStep(currentCheckoutStep - 1);
      } else {
          document.getElementById('cart-sheet').classList.remove('open');
      }
  };
  
  // Expose to window
  window.goToStep = goToStep;
  window.nextCheckoutStep = nextCheckoutStep;
  window.updateStepperUI = updateStepperUI;
