package com.sifat.slushflicks.ui.details.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.sifat.slushflicks.model.SeasonModel

class SeasonDiffUtils : DiffUtil.ItemCallback<SeasonModel>() {
    override fun areItemsTheSame(oldItem: SeasonModel, newItem: SeasonModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SeasonModel, newItem: SeasonModel): Boolean {
        return oldItem == newItem
    }
}