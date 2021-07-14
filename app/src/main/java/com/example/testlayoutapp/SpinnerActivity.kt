package com.example.testlayoutapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner

class SpinnerActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    companion object{
        fun navigateToPage(context : Context){
            val intent = Intent(context, SpinnerActivity::class.java)
            intent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner_layout)

        val itemList = prepareSpinnerList()
        initSpinner(itemList)
    }

    private fun prepareSpinnerList() : List<String> {
        val spnList : List<String> = ArrayList<String>().apply {
            this.add("Suraj")
            this.add("Geeta")
            this.add("Pooja")
            this.add("Pradeep")
            this.add("Bindu")
        }

        return spnList
    }

    private fun initSpinner(itemList: List<String>) {
        val spnFilter = findViewById<AppCompatSpinner>(R.id.spn_filter)
        spnFilter.onItemSelectedListener = this
        val spnAdapter = ArrayAdapter<String>(this@SpinnerActivity, android.R.layout.simple_spinner_item, itemList)
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnFilter.adapter = spnAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val selectedItem = parent?.selectedItem as String
            Toast.makeText(baseContext, "Selected Item = $selectedItem", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

}