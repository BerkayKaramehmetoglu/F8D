package com.example.f8d.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f8d.data.entity.Food
import com.example.f8d.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var foodRepository: FoodRepository) : ViewModel() {

    var foodList = MutableLiveData<List<Food>>()
    private var originalFoodList: List<Food> = emptyList()

    init {
        getFood()
    }

    private fun getFood() {
        try {
            CoroutineScope(Dispatchers.Main).launch {
                originalFoodList = foodRepository.getFood()
                foodList.value = foodRepository.getFood()
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    fun searchCart(query: String?) {
        if (query.isNullOrBlank()) {
            foodList.value = originalFoodList
            return
        }
        val filteredList = originalFoodList.filter {
            it.yemek_adi.contains(query, ignoreCase = true)
        }
        foodList.value = filteredList
    }
}