package com.example.f8d.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.f8d.R
import com.example.f8d.databinding.FragmentHomeBinding
import com.example.f8d.ui.adapter.FoodAdapter
import com.example.f8d.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.rv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.foodList.observe(viewLifecycleOwner) {
            val foodAdapter = FoodAdapter(requireContext(), it)
            binding.rv.adapter = foodAdapter
        }

        binding.navigationBottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_home -> {
                    findNavController().navigate(R.id.homeFragment)
                    true
                }

                R.id.bottom_nav_cart -> {
                    findNavController().navigate(R.id.home_to_cart)
                    true
                }

                else -> false
            }
        }

        binding.searchbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchCart(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchCart(newText)
                return true
            }
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomeViewModel by viewModels()
        viewModel = tempViewModel
    }
}