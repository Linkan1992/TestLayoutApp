package com.example.testlayoutapp.adapter

import android.view.View
import android.widget.TextView
import com.example.testlayoutapp.R
import com.example.testlayoutapp.model.StateDTO

class StateSpinnerAdapter(private val dataList: List<StateDTO>) : GenericSpinnerAdapter<StateDTO>(
    dataList,
    R.layout.custom_spinner_main_item,
    R.layout.custom_spinner_dropdown_item
) {
    override fun getMainView(view: View, item : StateDTO) {
        val selectedItemView = view.findViewById<TextView>(R.id.tv_selected)
        selectedItemView.text = item.stateName
    }

    override fun getItemId(position: Int): Long {
        return dataList[position].stateId
    }

    override fun getDropDownView(view: View, item : StateDTO) {
        val selectedItemView = view.findViewById<TextView>(R.id.tv_drop_selected)
        selectedItemView.text = item.stateName

        if (dataList.indexOf(item) == count - 1)
            view.findViewById<View>(R.id.view_seperator).visibility = View.GONE
    }


}