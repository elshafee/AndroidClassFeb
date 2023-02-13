package com.elshafee.androiden.shoppingitemlist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elshafee.androiden.databinding.ActivityShoppingItemListBinding
import com.elshafee.androiden.shoppingitemlist.db.ShoppingDatabase
import com.elshafee.androiden.shoppingitemlist.model.ShoppingItem
import com.elshafee.androiden.shoppingitemlist.repositry.ShoppingRepository
import com.elshafee.androiden.shoppingitemlist.ui.utils.ShoppingItemAdapter

class ShoppingItemListActivity : AppCompatActivity() {
    private lateinit var binding:ActivityShoppingItemListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = ShoppingDatabase(this)
        val repository =ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProvider(this,factory).get(ShoppingViewModel::class.java)

        val shoppingItemAdapter = ShoppingItemAdapter(listOf(),viewModel)
        binding.rvShoppingItems.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItems.adapter = shoppingItemAdapter


        viewModel.getAllShoppingItem().observe(this, {shoppingItemsList ->
            shoppingItemAdapter.items = shoppingItemsList
            shoppingItemAdapter.notifyDataSetChanged()

        })

        binding.fabShoppingItem.setOnClickListener {

            AddShoppingItemDialog(this,object : AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upset(item)
                }
            }).show()

        }

    }
}