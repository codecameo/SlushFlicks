package com.sifat.slushflicks.ui.home.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.sifat.slushflicks.model.ShowModelMinimal

class MovieDiffUtils : DiffUtil.ItemCallback<ShowModelMinimal>() {

    override fun areItemsTheSame(oldItem: ShowModelMinimal, newItem: ShowModelMinimal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ShowModelMinimal,
        newItem: ShowModelMinimal
    ): Boolean {
        return oldItem == newItem
    }
}