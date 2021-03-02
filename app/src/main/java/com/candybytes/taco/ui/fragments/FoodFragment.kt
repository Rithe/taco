package com.candybytes.taco.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.candybytes.taco.databinding.FragmentFoodBinding
import timber.log.Timber

class FoodFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFoodBinding.inflate(inflater, container, false).apply {

        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args: FoodFragmentArgs by navArgs()
        val food = args.food
        Timber.d(food.dishName)
    }
}