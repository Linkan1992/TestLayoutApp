package com.example.testlayoutapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testlayoutapp.R
import com.example.testlayoutapp.model.ItemModel
import com.google.android.material.textview.MaterialTextView

class ItemListAdapter(private val dataList : List<ItemModel>) : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
      //  holder.imgItem.setImageResource(dataList[position].sourceImage)

        val imgDrawable = ContextCompat.getDrawable(holder.itemView.context, dataList[position].sourceImage)

     //   holder.imgItem.setImageDrawable(imgDrawable)
        holder.imgItem.background = imgDrawable

        holder.tvTitle.text = dataList[position].title
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    open inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItem by lazy { itemView.findViewById<AppCompatImageView>(R.id.image_Report) }
        val tvTitle by lazy { itemView.findViewById<MaterialTextView>(R.id.text_Report) }
    }
}