package com.lofinif.gosporttestapp.ui.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lofinif.gosporttestapp.R
import com.lofinif.gosporttestapp.databinding.LayoutMenuCategoriesItemBinding
import com.lofinif.gosporttestapp.ui.menu.model.CategoryModel

class CategoryAdapter(
    private val onCategoryClickListener: OnCategoryClickListener,
) : ListAdapter<CategoryModel, CategoryAdapter.CategoryViewHolder>(DiffUtilCallBackCategory()) {
    class CategoryViewHolder(
        private val binding: LayoutMenuCategoriesItemBinding,
        private val onCategoryClickListener: OnCategoryClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CategoryModel) {
            val context = binding.root.context

            binding.category.text = model.category
            val backgroundColor = if (model.isSelected) {
                ContextCompat.getColor(context, R.color.button_category_pressed)
            } else {
                ContextCompat.getColor(context, R.color.button_category_default)
            }
            val textColor = if (model.isSelected) {
                ContextCompat.getColor(context, R.color.button_category_pressed_text)
            } else {
                ContextCompat.getColor(context, R.color.button_category_default_text)
            }
            binding.category.setBackgroundColor(backgroundColor)
            binding.category.setTextColor(textColor)
            binding.category.setOnClickListener {
                onCategoryClickListener.onCategoryClick(model)
            }
        }
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_menu_categories_item, parent, false)
        val binding = LayoutMenuCategoriesItemBinding.bind(view)
        return CategoryViewHolder(binding, onCategoryClickListener)
    }

}

class DiffUtilCallBackCategory : DiffUtil.ItemCallback<CategoryModel>() {
    override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
        return oldItem.category == newItem.category &&
                oldItem.isSelected == newItem.isSelected
    }

}

interface OnCategoryClickListener {
    fun onCategoryClick(category: CategoryModel)
}