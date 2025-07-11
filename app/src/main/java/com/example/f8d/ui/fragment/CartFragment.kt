package com.example.f8d.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.f8d.databinding.FragmentCartBinding
import com.example.f8d.ui.adapter.CartAdapter
import com.example.f8d.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        binding.rv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.cartList.observe(viewLifecycleOwner) {
            val cartAdapter = it?.let { it1 ->
                CartAdapter(
                    requireContext(),
                    it1, binding, viewModel
                )
            }
            binding.rv.adapter = cartAdapter
        }

        viewModel.cartError.observe(viewLifecycleOwner) {
            binding.textView4.text = viewModel.cartError.value
            binding.cartTotalPrice.visibility = View.INVISIBLE
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CartViewModel by viewModels()
        viewModel = tempViewModel
    }
}