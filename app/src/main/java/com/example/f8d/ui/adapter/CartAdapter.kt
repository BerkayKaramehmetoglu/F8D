package com.example.f8d.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.f8d.data.entity.Cart
import com.example.f8d.databinding.CartCardBinding

class CartAdapter(var context: Context, var cartList: List<Cart>) :
    RecyclerView.Adapter<CartAdapter.CardViewHolder>() {

    inner class CardViewHolder(var design: CartCardBinding) : RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = CartCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val carList = cartList[position]
        val design = holder.design

        design.cartTitle.text = carList.yemek_adi
        design.cartPrice.text = "${carList.yemek_fiyat} ₺"
        design.cartCount.text = "Adet ${carList.yemek_siparis_adet}"
        design.cartTotal.text = "${((carList.yemek_fiyat) * (carList.yemek_siparis_adet))} ₺"

        design.delete.setOnClickListener {
            //burada silme işlemi yapılacak
        }

        getImage(carList.yemek_resim_adi, design.image)
    }

    private fun getImage(image: String, design: ImageView) {
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${image}"
        Glide.with(context).load(url).override(400, 400).into(design)
    }
}