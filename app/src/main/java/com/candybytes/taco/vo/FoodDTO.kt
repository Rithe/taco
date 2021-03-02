package com.candybytes.taco.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodDTO(
    var dishName: String,
    var categoryId: Int,
    var nutrients: HashMap<String, Nutrient>
) : Parcelable
