package com.example.f8d.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f8d.data.entity.CRDResponse
import com.example.f8d.data.entity.Cart
import com.example.f8d.data.entity.Food
import com.example.f8d.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var foodRepository: FoodRepository) : ViewModel() {

    var cartList = MutableLiveData<List<Cart>>()

    init {
        getCart("berkay_kara")
    }

    fun getCart(username: String) {
        try {
            CoroutineScope(Dispatchers.Main).launch {
                cartList.value = foodRepository.getCart(username)
            }
        } catch (e: Exception) {
            println(e)
        }
    }
}