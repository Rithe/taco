package com.candybytes.taco.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.candybytes.taco.databinding.FragmentFoodBinding
import com.candybytes.taco.ui.adapters.NutrientAdapter

class FoodFragment : Fragment() {

    private lateinit var adapter: NutrientAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFoodBinding.inflate(inflater, container, false).apply {
            adapter = NutrientAdapter()
            foodNutritionList.adapter = adapter

        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args: FoodFragmentArgs by navArgs()
        val food = args.food
        adapter.submitList(food.nutrients.toList())
    }
}