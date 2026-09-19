package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey val orderId: String,
    val orderDate: Long = System.currentTimeMillis(),
    val totalAmount: Int,
    val itemCount: Int,
    val paymentMethod: String,
    val deliveryAddress: String,
    val deliveryStatus: String,
    val estimatedDeliveryDate: String,
    val summaryTitle: String,
    val summaryImageUrl: String,
    val isResellOrder: Boolean = false,
    val customerMargin: Int = 0
)
