package com.example.testlayoutapp.model

import androidx.annotation.DrawableRes

data class ItemModel(
        @DrawableRes val sourceImage : Int,
        val title : String
)