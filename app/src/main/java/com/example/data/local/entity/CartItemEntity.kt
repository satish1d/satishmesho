package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val productId: String,
    val title: String,
    val imageUrl: String,
    val price: Int,
    val originalPrice: Int,
    val size: String,
    val quantity: Int = 1,
    val customerResellPrice: Int = 0,
    val category: String = ""
)
