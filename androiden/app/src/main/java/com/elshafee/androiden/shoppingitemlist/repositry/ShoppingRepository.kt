package com.elshafee.androiden.shoppingitemlist.repositry

import com.elshafee.androiden.shoppingitemlist.db.ShoppingDatabase
import com.elshafee.androiden.shoppingitemlist.model.ShoppingItem

class ShoppingRepository(private val db:ShoppingDatabase) {

    fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)
    fun getAllShoppingItem() =  db.getShoppingDao().getAllShoppingItem()
}