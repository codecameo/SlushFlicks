package com.sifat.slushflicks.ui.details.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.sifat.slushflicks.model.CastModel

class CastDiffUtils : DiffUtil.ItemCallback<CastModel>() {

    override fun areItemsTheSame(oldItem: CastModel, newItem: CastModel): Boolean {
        return oldItem.castId == newItem.castId
    }

    override fun areContentsTheSame(oldItem: CastModel, newItem: CastModel): Boolean {
        return oldItem == newItem
    }
}