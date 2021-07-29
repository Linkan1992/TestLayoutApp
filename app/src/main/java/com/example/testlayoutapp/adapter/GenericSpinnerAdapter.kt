package com.example.testlayoutapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.LayoutRes

abstract class GenericSpinnerAdapter<T>(
    private val dataList: List<T>,
    @LayoutRes private val  mainLayoutResource: Int,
    @LayoutRes private val dropDownLayoutResource: Int
) : BaseAdapter() {

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): T {
        return dataList[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context)
            .inflate(mainLayoutResource, parent, false)

        getMainView(view, getItem(position))
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context)
            .inflate(dropDownLayoutResource, parent, false)

        getDropDownView(view, getItem(position))
        return view
    }


    abstract fun getMainView(view : View, item : T) : Unit

    abstract fun getDropDownView(view : View, item : T)


}