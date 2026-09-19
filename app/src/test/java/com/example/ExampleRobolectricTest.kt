package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Meesho", appName)
  }

  @Test
  fun `verify all categories have detailed info and products`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val db = com.example.data.local.AppDatabase.getDatabase(context)
    val repository = com.example.data.repository.MeeshoRepository(
      db.cartDao(),
      db.wishlistDao(),
      db.orderDao()
    )
    val categories = repository.getCategories()
    val products = repository.getAllProducts()

    assert(categories.size >= 10)
    categories.forEach { cat ->
      assert(cat.startingPrice > 0)
      assert(cat.highlightInfo.isNotEmpty())
      assert(cat.topBrands.isNotEmpty())
    }
    assert(products.size >= 40)
  }
}
