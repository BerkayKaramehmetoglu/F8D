package com.example.f8d.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun getImage(context: Context, image: String, size: Int, design: ImageView) {
    val url = "http://kasimadalan.pe.hu/yemekler/resimler/${image}"
    Glide.with(context).load(url).override(size, size).into(design)
}