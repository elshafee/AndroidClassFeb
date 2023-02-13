package com.elshafee.androiden.shoppingitemlist.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elshafee.androiden.shoppingitemlist.model.ShoppingItem


@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(item:ShoppingItem)

    @Delete
    fun delete(item:ShoppingItem)


    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItem():LiveData<List<ShoppingItem>>
}