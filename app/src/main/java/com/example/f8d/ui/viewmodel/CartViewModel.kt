package com.example.f8d.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f8d.data.entity.Cart
import com.example.f8d.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var foodRepository: FoodRepository) : ViewModel() {

    var cartList = MutableLiveData<List<Cart>?>()
    var cartError = MutableLiveData<String>()

    init {
        getCart("berkay_kara")
    }

    private fun getCart(username: String) {
        try {
            CoroutineScope(Dispatchers.Main).launch {
                val cartUser = foodRepository.getCart(username)
                if (cartUser?.sepet_yemekler == null || cartUser.success == 0) {
                    cartError.value = "Sepetinizde Ürün Yok"
                    cartList.value = cartUser?.sepet_yemekler
                } else if (cartUser.sepet_yemekler != null) {
                    cartList.value = cartUser.sepet_yemekler!!
                }
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    fun deleteCart(id: Int, username: String) {
        try {
            CoroutineScope(Dispatchers.Main).launch {
                foodRepository.deleteCart(id, username)
                getCart(username)
            }
        } catch (e: Exception) {
            println(e)
        }
    }
}