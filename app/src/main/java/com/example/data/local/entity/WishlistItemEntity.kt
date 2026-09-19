package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist_items")
data class WishlistItemEntity(
    @PrimaryKey val productId: String,
    val title: String,
    val imageUrl: String,
    val price: Int,
    val originalPrice: Int,
    val rating: Float,
    val reviewCount: Int,
    val category: String,
    val addedAt: Long = System.currentTimeMillis()
)
