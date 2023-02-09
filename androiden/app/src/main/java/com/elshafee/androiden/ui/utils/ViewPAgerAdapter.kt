package com.elshafee.androiden.ui.utils

import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elshafee.androiden.databinding.ItemPagerBinding

class ViewPAgerAdapter(var images:List<Int>):RecyclerView.Adapter<ViewPAgerAdapter.ViewPagerViewHolder> (){
    inner class ViewPagerViewHolder(val binding: ItemPagerBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(
            ItemPagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentEventImage = images[position]
        holder.binding.ivEventImage.setImageResource(currentEventImage)
    }
    override fun getItemCount(): Int {
        return images.size
    }
}