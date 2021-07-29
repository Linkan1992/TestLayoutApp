package com.example.testlayoutapp.adapter

import android.view.View
import android.widget.TextView
import com.example.testlayoutapp.R
import com.example.testlayoutapp.model.CityDTO

class CitySpinnerAdapter(private val dataList: List<CityDTO>) : GenericSpinnerAdapter<CityDTO>(
    dataList,
    R.layout.custom_spinner_main_item,
    R.layout.custom_spinner_dropdown_item
) {
    override fun getMainView(view: View, item : CityDTO) {
        val selectedItemView = view.findViewById<TextView>(R.id.tv_selected)
        selectedItemView.text = item.cityName
    }

    override fun getItemId(position: Int): Long {
        return dataList[position].cityId
    }

    override fun getDropDownView(view: View, item : CityDTO) {
        val selectedItemView = view.findViewById<TextView>(R.id.tv_drop_selected)
        selectedItemView.text = item.cityName

        if (dataList.indexOf(item) == count - 1)
            view.findViewById<View>(R.id.view_seperator).visibility = View.GONE
    }


}