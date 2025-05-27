package com.example.f8d.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f8d.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var foodRepository: FoodRepository) : ViewModel() {

    var counter = MutableLiveData(1)

    fun plusCounter() {
        counter.value = (counter.value ?: 0) + 1
    }

    fun minusCounter() {
        if (counter.value!! <= 0) {
            return
        } else {
            counter.value = (counter.value ?: 0) - 1
        }
    }

    fun addCart(
        id: Int,
        title: String,
        imageTitle: String,
        price: Int,
        count: Int,
        username: String
    ) {
        try {
            CoroutineScope(Dispatchers.Main).launch {
                foodRepository.addCart(id, title, imageTitle, price, count, username)
            }
        } catch (e: Exception) {
            println(e)
        }
    }
}