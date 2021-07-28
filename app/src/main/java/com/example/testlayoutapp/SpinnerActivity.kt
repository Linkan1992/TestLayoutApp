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

    private val jeansSizeList : List<String> by lazy { prepareAvailableSizeSpinnerList() }

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

        initSortSpinner(prepareSortSpinnerList())

      //  initJeansSpinner(jeansSizeList)
    }

    private fun prepareAvailableSizeSpinnerList() : List<String> {
        val spnList : List<String> = ArrayList<String>().apply {

            this.add("Select your brand size")

            this.add("Wrangler - 30")
            this.add("Wrangler - 32")
            this.add("Wrangler - 34")
            this.add("Wrangler - 36")
            this.add("Wrangler - 38")

            this.add("Pepe Jeans - 30")
            this.add("Pepe Jeans - 32")
            this.add("Pepe Jeans - 34")
            this.add("Pepe Jeans - 36")
            this.add("Pepe Jeans - 38")

            this.add("Spykar - 30")
            this.add("Spykar - 32")
            this.add("Spykar - 34")
            this.add("Spykar - 36")
            this.add("Spykar - 38")

            this.add("US Polo - 30")
            this.add("US Polo - 32")
            this.add("US Polo - 34")
            this.add("US Polo - 36")
            this.add("US Polo - 38")

            this.add("Color Plus - 30")
            this.add("Color Plus - 32")
            this.add("Color Plus - 34")
            this.add("Color Plus - 36")
            this.add("Color Plus - 38")
        }

        return spnList
    }

    private fun initSortSpinner(prepareSortSpinnerList: List<String>) {
        val spnSort = findViewById<AppCompatSpinner>(R.id.spn_sort)
        spnSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedJeans = parent?.selectedItem as String
                val filteredJeansSize = filterSizeBasedOnJeansSelection(selectedJeans)
                initJeansSpinner(filteredJeansSize)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        val spnAdapter = ArrayAdapter<String>(this@SpinnerActivity, android.R.layout.simple_spinner_item, prepareSortSpinnerList)
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnSort.adapter = spnAdapter
    }


    private fun prepareSortSpinnerList() : List<String> {
        val spnList : List<String> = ArrayList<String>().apply {
            this.add("Select your brand")
            this.add("Wrangler")
            this.add("Pepe Jeans")
            this.add("Spykar")
            this.add("US Polo")
            this.add("Color Plus")
        }

        return spnList
    }

    private fun initJeansSpinner(itemList: List<String>) {
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

    private fun filterSizeBasedOnJeansSelection(selectedJeans : String) : List<String>{
        val filteredJeansSizeList = ArrayList<String>()
        for (jeansSize in jeansSizeList){
            if (jeansSize.contains(selectedJeans))
                filteredJeansSizeList.add(jeansSize)
        }

        return filteredJeansSizeList
    }

}