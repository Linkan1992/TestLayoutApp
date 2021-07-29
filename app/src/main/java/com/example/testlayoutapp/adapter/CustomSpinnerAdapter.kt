package com.example.testlayoutapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.testlayoutapp.R
import com.example.testlayoutapp.model.StateDTO

class CustomSpinnerAdapter(private val dataList : List<StateDTO>) : BaseAdapter() {

    override fun getCount(): Int {
       return dataList.size
    }

    override fun getItem(position: Int): StateDTO {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
      return dataList[position].stateId
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.custom_spinner_main_item, parent, false)
        val selectedItemView = view.findViewById<TextView>(R.id.tv_selected)
        selectedItemView.text = getItem(position).stateName
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.custom_spinner_dropdown_item, parent, false)
        val selectedItemView = view.findViewById<TextView>(R.id.tv_drop_selected)
        selectedItemView.text = getItem(position).stateName
        return view
    }


}