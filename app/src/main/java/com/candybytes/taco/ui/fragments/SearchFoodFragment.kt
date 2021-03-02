package com.candybytes.taco.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.candybytes.taco.databinding.FragmentSearchFoodBinding
import com.candybytes.taco.ui.adapters.FoodListAdapter
import com.candybytes.taco.ui.vm.SearchFoodViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFoodFragment : Fragment() {

    private val viewModel: SearchFoodViewModel by viewModels()

    private val adapter = FoodListAdapter(
        showDetails = { food ->
            findNavController().navigate(
                SearchFoodFragmentDirections.actionSearchFoodFragmentToFoodFragment(food)
            )
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSearchFoodBinding.inflate(inflater, container, false).apply {
            foodList.adapter = adapter
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.items.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}
