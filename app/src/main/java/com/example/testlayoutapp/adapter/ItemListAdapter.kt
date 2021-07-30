package com.example.testlayoutapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemListAdapter(private val dataList : List<String>) : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ItemViewHolder : RecyclerView.ViewHolder() {

    }
}