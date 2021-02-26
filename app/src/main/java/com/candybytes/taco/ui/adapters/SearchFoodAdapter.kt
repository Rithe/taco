package com.candybytes.taco.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.candybytes.taco.R
import com.candybytes.taco.databinding.ItemFoodBinding
import com.candybytes.taco.vo.Food

class FoodListAdapter() : PagingDataAdapter<Food, RecyclerView.ViewHolder>(FoodDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FoodViewHolder(
            ItemFoodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val food: Food? = getItem(position)
        (holder as FoodViewHolder).bind(food)
        holder.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_searchFoodFragment_to_foodFragment)
        )
    }

    class FoodViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Food?) {
            binding.apply {
                food = item
                executePendingBindings()
            }
        }
    }
}

private class FoodDiffCallback : DiffUtil.ItemCallback<Food>() {

    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }
}