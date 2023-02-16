package com.elshafee.androiden.fireStorage.uitls

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elshafee.androiden.databinding.ItemClothesBinding

class ClothesImageAdapter(var urls:List<String>):RecyclerView.Adapter<ClothesImageAdapter.ClothesImagesViewHolder>() {
    inner class ClothesImagesViewHolder(val binding:ItemClothesBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesImagesViewHolder {
        return ClothesImagesViewHolder(
            ItemClothesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ClothesImagesViewHolder, position: Int) {
        val url = urls[position]
        Glide.with(holder.itemView).load(url).centerCrop().into(holder.binding.ivClothesImageItem)
    }

    override fun getItemCount(): Int {
        return urls.size
    }
}