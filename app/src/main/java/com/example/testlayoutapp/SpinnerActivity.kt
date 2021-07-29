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
import com.example.testlayoutapp.model.JeansDTO

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
        CustomSpinnerActivity.navigateToPage(this@SpinnerActivity)
        finish()
        initSortSpinner(prepareSortSpinnerList())
    }


    private fun prepareJeansSizeList(jeansBrandName : String) : List<String>{
        val spnList : List<String> = ArrayList<String>().apply {

            this.add("$jeansBrandName - 30")
            this.add("$jeansBrandName - 32")
            this.add("$jeansBrandName - 34")
            this.add("$jeansBrandName - 36")
            this.add("$jeansBrandName - 38")
        }

        return spnList
    }

    private fun initSortSpinner(prepareSortSpinnerList: List<JeansDTO>) {
        val spnSort = findViewById<AppCompatSpinner>(R.id.spn_sort)
        spnSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedJeans = parent?.selectedItem as JeansDTO
                initJeansSpinner(selectedJeans.availableSize)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        val spnAdapter = ArrayAdapter<JeansDTO>(this@SpinnerActivity, android.R.layout.simple_spinner_item, prepareSortSpinnerList)
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnSort.adapter = spnAdapter
    }


    private fun prepareSortSpinnerList() : List<JeansDTO> {
        val spnList : List<JeansDTO> = ArrayList<JeansDTO>().apply {
            this.add(JeansDTO(brandName = "Select your brand", availableSize = ArrayList()))
            this.add(JeansDTO(brandName = "Wrangler", availableSize = prepareJeansSizeList(jeansBrandName = "Wrangler")))
            this.add(JeansDTO(brandName = "Pepe Jeans", availableSize = prepareJeansSizeList(jeansBrandName = "Pepe Jeans")))
            this.add(JeansDTO(brandName = "Spykar", availableSize = prepareJeansSizeList(jeansBrandName = "Spykar")))
            this.add(JeansDTO(brandName = "US Polo", availableSize = prepareJeansSizeList(jeansBrandName = "US Polo")))
            this.add(JeansDTO(brandName = "Color Plus", availableSize = prepareJeansSizeList(jeansBrandName = "Color Plus")))
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


}