package com.elshafee.androiden.shoppingitemlist.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.elshafee.androiden.databinding.ActivityAddShoppingItemDialogBinding
import com.elshafee.androiden.shoppingitemlist.model.ShoppingItem

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) : AppCompatDialog(context) {
    lateinit var binding:ActivityAddShoppingItemDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddShoppingItemDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnShoppingDialogAdd.setOnClickListener {
            var shoppingItemName = binding.etShoppingItemDialogName.text.toString()
            var shoppingItemAmount = binding.etShoppingItemDialogAmount.text.toString()

            if (shoppingItemName.isNotEmpty() && shoppingItemAmount.isNotEmpty()){
                var item = ShoppingItem(shoppingItemName,shoppingItemAmount.toInt())
                addDialogListener.onAddButtonClicked(item)
                dismiss()
            }else{
                Toast.makeText(context,"Please enter item name and amount",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        binding.btnShoppingDialogCancel.setOnClickListener {
            cancel()
        }
    }
}