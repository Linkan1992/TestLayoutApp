package com.example.testlayoutapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatSpinner
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testlayoutapp.adapter.CitySpinnerAdapter
import com.example.testlayoutapp.adapter.CustomSpinnerAdapter
import com.example.testlayoutapp.adapter.ItemListAdapter
import com.example.testlayoutapp.model.CityDTO
import com.example.testlayoutapp.model.ItemModel
import com.example.testlayoutapp.model.JeansDTO
import com.example.testlayoutapp.model.StateDTO

class CustomSpinnerActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    companion object {
        fun navigateToPage(context: Context) {
            val intent = Intent(context, CustomSpinnerActivity::class.java)
            intent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_spinner)

        initStateSpinner(prepareStateSpinnerList())
        setupRecyclerViewList()
    }

    private fun setupRecyclerViewList() {
        val recyclerViewList = findViewById<RecyclerView>(R.id.rview_list)
        val layoutManagerObj = LinearLayoutManager(this@CustomSpinnerActivity)
        layoutManagerObj.orientation = RecyclerView.VERTICAL
        recyclerViewList.layoutManager = layoutManagerObj
        recyclerViewList.itemAnimator = DefaultItemAnimator()
       // val itemAdapter = ItemListAdapter(dataList = ArrayList())
        val itemAdapter = ItemListAdapter(dataList = createRecyclerList())
        recyclerViewList.adapter = itemAdapter

    }

    private fun createRecyclerList(): List<ItemModel> {
        val dataList = ArrayList<ItemModel>().apply {
            add(ItemModel(sourceImage = R.drawable.arrow, title = "Arrow"))
            add(ItemModel(sourceImage = R.drawable.ic_bell, title = "Bell"))
            add(ItemModel(sourceImage = R.drawable.ic_drop_down_arrow, title = "Drop-Arrow"))
            add(ItemModel(sourceImage = R.drawable.ic_rupee, title = "Rupee"))
            add(ItemModel(sourceImage = R.drawable.arrow, title = "Arrow"))
            add(ItemModel(sourceImage = R.drawable.ic_bell, title = "Bell"))
            add(ItemModel(sourceImage = R.drawable.ic_drop_down_arrow, title = "Drop-Arrow"))
            add(ItemModel(sourceImage = R.drawable.ic_rupee, title = "Rupee"))
            add(ItemModel(sourceImage = R.drawable.arrow, title = "Arrow"))
            add(ItemModel(sourceImage = R.drawable.ic_bell, title = "Bell"))
            add(ItemModel(sourceImage = R.drawable.ic_drop_down_arrow, title = "Drop-Arrow"))
            add(ItemModel(sourceImage = R.drawable.ic_rupee, title = "Rupee"))
        }
        return dataList
    }


    private fun prepareCityList(stateName: String): List<CityDTO> {
        val spnList: List<CityDTO> = ArrayList<CityDTO>().apply {

            for (i in 0..3) {
                var cityName = ""
                var cityPopulation = 0
                when (stateName) {
                    AppConstants.STATE_MAHARASHTRA -> {
                        if (i == 0){
                            cityName = "Mumbai"
                            cityPopulation = 4000
                        }else if(i == 1){
                            cityName = "Pune"
                            cityPopulation = 3000
                        } else if(i == 2){
                            cityName = "Nagpur"
                            cityPopulation = 2000
                        } else if(i == 3){
                            cityName = "Nashik"
                            cityPopulation = 1500
                        }
                    }
                    AppConstants.STATE_GUJRAT -> {
                        when (i) {
                            0 -> {
                                cityName = "Ahemdabad"
                                cityPopulation = 4000
                            }
                            1 -> {
                                cityName = "Vadodara"
                                cityPopulation = 3000
                            }
                            2 -> {
                                cityName = "Bhuj"
                                cityPopulation = 2000
                            }
                            3 -> {
                                cityName = "Gandhinagar"
                                cityPopulation = 1500
                            }
                        }
                    }
                    AppConstants.STATE_UTTAR_PRADESH -> {
                        when (i) {
                            0 -> {
                                cityName = "Lucknow"
                                cityPopulation = 4000
                            }
                            1 -> {
                                cityName = "Varansi"
                                cityPopulation = 3000
                            }
                            2 -> {
                                cityName = "Azamgarh"
                                cityPopulation = 2000
                            }
                            3 -> {
                                cityName = "Mathura"
                                cityPopulation = 1500
                            }
                        }
                    }
                    AppConstants.STATE_KARNATKA -> {
                        when (i) {
                            0 -> {
                                cityName = "Bengaluru"
                                cityPopulation = 4000
                            }
                            1 -> {
                                cityName = "Manglore"
                                cityPopulation = 3000
                            }
                            2 -> {
                                cityName = "Yadgiri"
                                cityPopulation = 2000
                            }
                            3 -> {
                                cityName = "Electronic City"
                                cityPopulation = 1500
                            }
                        }
                    }
                }

                add(CityDTO(cityName = cityName, cityId = i.toLong(), populationCount = cityPopulation))
            }
        }

        return spnList
    }

    private fun initStateSpinner(stateSpinnerList: List<StateDTO>) {
        val spnState = findViewById<AppCompatSpinner>(R.id.spn_sort)

        spnState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val itemSelected = parent?.selectedItem as StateDTO
                initCitySpinner(itemSelected.cityList)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        val spnStateAdapter = CustomSpinnerAdapter(stateSpinnerList)
        spnState.adapter = spnStateAdapter
    }


    private fun prepareStateSpinnerList(): List<StateDTO> {
        val spnList: List<StateDTO> = ArrayList<StateDTO>().apply {
            this.add(
                StateDTO(
                    stateName = "Select your state",
                    cityList = ArrayList(),
                    populationCount = 0,
                    stateId = 0
                )
            )
            this.add(
                StateDTO(
                    stateName = AppConstants.STATE_MAHARASHTRA,
                    cityList = prepareCityList(AppConstants.STATE_MAHARASHTRA),
                    populationCount = 16000,
                    stateId = 1
                )
            )
            this.add(
                StateDTO(
                    stateName = AppConstants.STATE_GUJRAT,
                    cityList = prepareCityList(AppConstants.STATE_GUJRAT),
                    populationCount = 13000,
                    stateId = 2
                )
            )
            this.add(
                StateDTO(
                    stateName = AppConstants.STATE_UTTAR_PRADESH,
                    cityList = prepareCityList(AppConstants.STATE_UTTAR_PRADESH),
                    populationCount = 22000,
                    stateId = 3
                )
            )
            this.add(
                StateDTO(
                    stateName = AppConstants.STATE_KARNATKA,
                    cityList = prepareCityList(AppConstants.STATE_KARNATKA),
                    populationCount = 11000,
                    stateId = 4
                )
            )
        }

        return spnList
    }

    private fun initCitySpinner(cityList: List<CityDTO>) {
        val spnCity = findViewById<AppCompatSpinner>(R.id.spn_filter)

        spnCity.onItemSelectedListener = this

        val spnCityAdapter = CitySpinnerAdapter(cityList)
        spnCity.adapter = spnCityAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem = parent?.selectedItem as CityDTO
        Toast.makeText(baseContext, "Selected Item = ${selectedItem.cityName}", Toast.LENGTH_SHORT).show()

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

}