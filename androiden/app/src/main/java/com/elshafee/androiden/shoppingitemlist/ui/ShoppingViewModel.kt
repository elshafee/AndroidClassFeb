package com.elshafee.androiden.shoppingitemlist.ui

import androidx.lifecycle.ViewModel
import com.elshafee.androiden.shoppingitemlist.model.ShoppingItem
import com.elshafee.androiden.shoppingitemlist.repositry.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository):ViewModel() {

    fun upset(item: ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.delete(item)
    }

    fun getAllShoppingItem() = repository.getAllShoppingItem()
}