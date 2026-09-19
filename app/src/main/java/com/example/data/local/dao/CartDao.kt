package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.CartItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items ORDER BY id DESC")
    fun getAllCartItems(): Flow<List<CartItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: CartItemEntity): Long

    @Update
    suspend fun updateItem(item: CartItemEntity)

    @Query("UPDATE cart_items SET quantity = :quantity WHERE id = :id")
    suspend fun updateQuantity(id: Long, quantity: Int)

    @Query("UPDATE cart_items SET customerResellPrice = :price WHERE id = :id")
    suspend fun updateResellPrice(id: Long, price: Int)

    @Query("DELETE FROM cart_items WHERE id = :id")
    suspend fun deleteItemById(id: Long)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    @Query("SELECT * FROM cart_items WHERE productId = :productId AND size = :size LIMIT 1")
    suspend fun findByProductAndSize(productId: String, size: String): CartItemEntity?
}
