package com.lofinif.gosporttestapp.ui.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lofinif.gosporttestapp.R
import com.lofinif.gosporttestapp.data.menu.dto.AdsPoster
import com.lofinif.gosporttestapp.databinding.LayoutMenuAdsItemBinding

class AdsPostersAdapter(private val context:Context): ListAdapter<AdsPoster, AdsPostersAdapter.AdsViewHolder>(DiffUtilCallBackAds()) {

    class AdsViewHolder(private val binding: LayoutMenuAdsItemBinding,
                        private val context: Context): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: AdsPoster){
            Glide.with(context)
                .load(model.source)
                .into(binding.poster)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdsPostersAdapter.AdsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_menu_ads_item, parent, false)
        val binding = LayoutMenuAdsItemBinding.bind(view)
        return AdsViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: AdsPostersAdapter.AdsViewHolder, position: Int) {
        val item = getItem(position)
        item.let {
            holder.bind(item)
        }
    }
}

class DiffUtilCallBackAds(): DiffUtil.ItemCallback<AdsPoster>() {
    override fun areItemsTheSame(
        oldItem: AdsPoster,
        newItem: AdsPoster
    ): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AdsPoster,
        newItem: AdsPoster
    ): Boolean {
        return oldItem.source == newItem.source
    }

}
