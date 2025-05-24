package com.example.f8d.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.f8d.R
import com.example.f8d.databinding.FragmentDetailBinding
import com.example.f8d.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val bundle: DetailFragmentArgs by navArgs()
        val comingFood = bundle.food

        binding.orderTitle.text = comingFood.yemek_adi
        getImage(requireContext(), bundle.food.yemek_resim_adi, binding.imageView)
        binding.orderPrice.text = "${bundle.food.yemek_fiyat} ₺"

        binding.orderPlus.setOnClickListener {
            viewModel.plusCounter()
        }

        binding.orderMinus.setOnClickListener {
            viewModel.minusCounter()
        }

        binding.orderSubmitBtn.setOnClickListener {
            viewModel.addCart(
                bundle.food.yemek_id,
                bundle.food.yemek_adi,
                bundle.food.yemek_resim_adi,
                bundle.food.yemek_fiyat,
                viewModel.counter.value!!,
                "berkay_kara"
            )
            it.findNavController().navigate(R.id.detail_to_cart)
        }

        viewModel.counter.observe(viewLifecycleOwner) {
            binding.orderCounter.text = viewModel.counter.value!!.toString()
            binding.orderTotalPrice.text =
                "${(viewModel.counter.value!!) * (bundle.food.yemek_fiyat)} ₺"
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    private fun getImage(context: Context, image: String, design: ImageView) {
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${image}"
        Glide.with(context).load(url).override(800, 800).into(design)
    }
}