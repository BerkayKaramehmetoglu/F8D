package com.example.f8d.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.f8d.util.getImage
import androidx.recyclerview.widget.RecyclerView
import com.example.f8d.data.entity.Cart
import com.example.f8d.databinding.CartCardBinding
import com.example.f8d.databinding.FragmentCartBinding
import com.example.f8d.ui.viewmodel.CartViewModel

class CartAdapter(
    var context: Context,
    var cartList: List<Cart>,
    var binding: FragmentCartBinding,
    var viewModel: CartViewModel
) :
    RecyclerView.Adapter<CartAdapter.CardViewHolder>() {
    private var totalPrice = 0

    inner class CardViewHolder(var design: CartCardBinding) : RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = CartCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val cartList = cartList[position]
        val design = holder.design

        design.cartTitle.text = cartList.yemek_adi
        design.cartPrice.text = "${cartList.yemek_fiyat} ₺"
        design.cartCount.text = "Adet ${cartList.yemek_siparis_adet}"
        design.cartTotal.text = "${((cartList.yemek_fiyat) * (cartList.yemek_siparis_adet))} ₺"

        design.delete.setOnClickListener {
            val idList = cartList.sepet_yemek_id
            val username = cartList.kullanici_adi

            val builder = AlertDialog.Builder(context)
            builder.setTitle(cartList.yemek_adi)
                .setMessage("Ürünü silmek istediğinizden emin misiniz?")
                .setPositiveButton("Evet") { dialog, _ ->
                    viewModel.deleteCart(idList, username)
                    dialog.dismiss()
                }
                .setNegativeButton("Hayır") { dialog, _ ->
                    dialog.dismiss()
                }

            val dialog = builder.create()
            dialog.show()
        }

        getImage(context, cartList.yemek_resim_adi, 400, design.image)

        totalPrice += (cartList.yemek_fiyat) * (cartList.yemek_siparis_adet)
        binding.cartTotalPrice.text = "${totalPrice} ₺"
    }
}