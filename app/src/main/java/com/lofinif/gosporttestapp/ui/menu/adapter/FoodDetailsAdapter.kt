package com.lofinif.gosporttestapp.ui.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.lofinif.gosporttestapp.R
import com.lofinif.gosporttestapp.databinding.LayoutMenuFoodItemBinding
import com.lofinif.gosporttestapp.ui.menu.model.FoodDetailsModel
import kotlin.random.Random

class FoodDetailsAdapter(private val context: Context): ListAdapter<FoodDetailsModel, FoodDetailsAdapter.FoodDetailsViewHolder>(DiffUtilCallBackFoods()) {
    class FoodDetailsViewHolder(private val binding: LayoutMenuFoodItemBinding, private val context: Context): ViewHolder(binding.root) {
        fun bind(model: FoodDetailsModel){
            binding.foodDescription.text = model.description
            binding.foodTitle.text = model.name
            Glide.with(context)
                .load(model.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.foodPicItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodDetailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_menu_food_item, parent, false)
        val binding = LayoutMenuFoodItemBinding.bind(view)

        return FoodDetailsViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: FoodDetailsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

}

class DiffUtilCallBackFoods(): DiffUtil.ItemCallback<FoodDetailsModel>() {
    override fun areItemsTheSame(oldItem: FoodDetailsModel, newItem: FoodDetailsModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FoodDetailsModel, newItem: FoodDetailsModel): Boolean {
        return oldItem.description == newItem.description
    }

}
