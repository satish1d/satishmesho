package com.example

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.example.data.repository.MeeshoRepository
import com.example.ui.components.ProductCard
import com.example.ui.theme.MyApplicationTheme
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = RobolectricDeviceQualifiers.Pixel8, sdk = [36])
class GreetingScreenshotTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun product_card_screenshot() {
    val sampleProduct = com.example.data.model.Product(
      id = "test_p1",
      title = "Surat Silk Saree with Blouse Piece",
      category = "ethnic",
      subCategory = "Sarees",
      price = 289,
      originalPrice = 999,
      rating = 4.4f,
      reviewCount = 1200,
      supplierName = "Surat Silk Mills",
      supplierRating = 4.4f,
      description = "Authentic printed saree",
      fabric = "Silk",
      pattern = "Printed",
      imageUrl = "https://images.unsplash.com/photo-1610030469983-98e550d6193c?w=600"
    )
    composeTestRule.setContent {
      MyApplicationTheme {
        ProductCard(
          product = sampleProduct,
          isWishlisted = false,
          onProductClick = {},
          onWishlistToggle = {}
        )
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/greeting.png")
  }
}
