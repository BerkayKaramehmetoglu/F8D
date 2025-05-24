package com.example.f8d.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.f8d.data.entity.Food
import com.example.f8d.databinding.HomeCardBinding
import com.example.f8d.ui.fragment.HomeFragmentDirections

class FoodAdapter(var context: Context, var foodList: List<Food>) :
    RecyclerView.Adapter<FoodAdapter.CardViewHolder>() {

    inner class CardViewHolder(var design: HomeCardBinding) : RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = HomeCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val foodList = foodList[position]
        val design = holder.design

        design.textHomeTitle.text = foodList.yemek_adi
        design.textHomePrice.text = "${foodList.yemek_fiyat} ₺"
        getImage(foodList.yemek_resim_adi, design.imageHome)

        design.card.setOnClickListener {
            val navigate = HomeFragmentDirections.homeToDetail(foodList)
            it.findNavController().navigate(navigate)
        }
    }

    private fun getImage(image: String, design: ImageView) {
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${image}"
        Glide.with(context).load(url).override(350, 350).into(design)
    }
}