package com.elshafee.androiden.shoppingitemlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.elshafee.androiden.shoppingitemlist.repositry.ShoppingRepository

class ShoppingViewModelFactory(private val repository: ShoppingRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T
    }
}