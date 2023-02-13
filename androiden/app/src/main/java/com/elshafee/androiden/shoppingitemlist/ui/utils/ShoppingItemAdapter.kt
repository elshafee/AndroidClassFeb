package com.elshafee.androiden.shoppingitemlist.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.elshafee.androiden.databinding.ItemShoppingBinding
import com.elshafee.androiden.shoppingitemlist.model.ShoppingItem
import com.elshafee.androiden.shoppingitemlist.ui.ShoppingViewModel

class ShoppingItemAdapter(var items: List<ShoppingItem>, private val viewModel: ShoppingViewModel) :
    RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(val binding: ItemShoppingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(
            ItemShoppingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        var currentShoppingItem = items[position]
        holder.binding.txShoppingItemName.text = currentShoppingItem.name
        holder.binding.txShoppingItemAmount.text = "${currentShoppingItem.amount}"

        holder.binding.ivShoppingItemDelete.setOnClickListener {
            viewModel.delete(currentShoppingItem)
        }
        holder.binding.ivShoppingItemAdd.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upset(currentShoppingItem)
        }
        holder.binding.ivShoppingItemMinus.setOnClickListener {
            if (currentShoppingItem.amount > 1) {
                currentShoppingItem.amount--
                viewModel.upset(currentShoppingItem)
            } else if (currentShoppingItem.amount <= 1) {
                viewModel.delete(currentShoppingItem)
            }
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }
}