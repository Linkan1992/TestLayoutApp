package com.example.testlayoutapp.model

data class JeansDTO(
        val brandName : String,
        val availableSize : List<String>
){
    override fun toString(): String {
        return brandName
    }
}