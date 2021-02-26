package com.candybytes.taco.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.candybytes.taco.databinding.FragmentCategoryListBinding
import com.candybytes.taco.ui.adapters.CategoryAdapter
import com.candybytes.taco.ui.vm.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment : Fragment() {

    private val viewModel: CategoriesViewModel by viewModels()
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCategoryListBinding.inflate(layoutInflater, container, false).apply {
            adapter = CategoryAdapter()
            categoryList.adapter = adapter
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.totalTacoCategories.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}
