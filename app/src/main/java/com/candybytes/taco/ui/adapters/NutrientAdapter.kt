package com.candybytes.taco.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.candybytes.taco.databinding.ItemNutrientBinding
import com.candybytes.taco.vo.Nutrient

class NutrientAdapter :
    ListAdapter<Pair<String, Nutrient>, RecyclerView.ViewHolder>(NutrientDiffCallback()) {

    class NutrientViewHolder(private val binding: ItemNutrientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(name: String, nutrient: Nutrient) {
            binding.apply {
                nutrientName.text = name
                nutrientValue.text = defineNutrientDisplayText(nutrient)
                executePendingBindings()
            }
        }

        private fun defineNutrientDisplayText(nutrient: Nutrient): String {
            return if (nutrient.qty.isEmpty() || nutrient.qty == NOT_AVAILABLE) {
                NOT_AVAILABLE
            } else {
                nutrient.qty.plus(" ").plus(nutrient.unit)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NutrientViewHolder(
            ItemNutrientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val curNutrient = getItem(position)
        (holder as NutrientViewHolder).bind(curNutrient.first, curNutrient.second)
    }

    companion object {
        const val NOT_AVAILABLE = "NA"
    }
}

private class NutrientDiffCallback : DiffUtil.ItemCallback<Pair<String, Nutrient>>() {

    override fun areItemsTheSame(
        oldItem: Pair<String, Nutrient>,
        newItem: Pair<String, Nutrient>
    ): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(
        oldItem: Pair<String, Nutrient>,
        newItem: Pair<String, Nutrient>
    ): Boolean {
        return oldItem == newItem
    }
}