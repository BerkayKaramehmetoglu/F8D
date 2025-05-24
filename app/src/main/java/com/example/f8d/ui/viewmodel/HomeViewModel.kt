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

    init {
        getFood()
    }

    fun getFood() {
        try {
            CoroutineScope(Dispatchers.Main).launch {
                foodList.value = foodRepository.getFood()
            }
        }
        catch (e:Exception){
            println(e)
        }
    }
}