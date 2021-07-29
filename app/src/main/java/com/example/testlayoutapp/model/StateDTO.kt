package com.example.testlayoutapp.model

data class StateDTO(
    val stateName: String,
    val cityList: List<CityDTO>,
    val populationCount: Int,
    val stateId : Long
)