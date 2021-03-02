package com.candybytes.taco.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.candybytes.taco.databinding.ItemFoodBinding
import com.candybytes.taco.vo.Food
import com.candybytes.taco.vo.FoodDTO

class FoodListAdapter(private var showDetails: (FoodDTO) -> Unit) :
    PagingDataAdapter<Food, RecyclerView.ViewHolder>(FoodDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FoodViewHolder(
            ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            showDetails
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FoodViewHolder).bind(getItem(position))
    }

    class FoodViewHolder(
        private val binding: ItemFoodBinding,
        private var showDetails: (FoodDTO) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Food?) {
            binding.apply {
                food = item
                executePendingBindings()
            }

            binding.setClickListener {
                val nutrients = item?.nutrients ?: hashMapOf()
                val foodDto =
                    FoodDTO(item?.description.orEmpty(), item?.categoryId!!.or(-1), nutrients)
                showDetails(foodDto)
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