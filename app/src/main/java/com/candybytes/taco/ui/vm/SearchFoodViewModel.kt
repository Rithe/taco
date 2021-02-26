package com.candybytes.taco.ui.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.candybytes.taco.db.FoodDao
import timber.log.Timber

class SearchFoodViewModel @ViewModelInject constructor(
    private val foodDao: FoodDao,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val info = liveData {
        try {
            val foods = foodDao.getAllAsync()
            emit(foods)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    val items = Pager(
        PagingConfig(
            pageSize = 50,
            enablePlaceholders = true,
            maxSize = 200
        )
    ) {
        foodDao.getAllPaged()
    }.flow


}
