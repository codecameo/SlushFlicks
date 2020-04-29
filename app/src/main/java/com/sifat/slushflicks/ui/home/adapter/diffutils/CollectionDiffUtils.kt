package com.sifat.slushflicks.ui.home.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.sifat.slushflicks.ui.home.adapter.model.CollectionListModel

class CollectionDiffUtils : DiffUtil.ItemCallback<CollectionListModel>() {

    override fun areItemsTheSame(
        oldItem: CollectionListModel,
        newItem: CollectionListModel
    ): Boolean {
        return oldItem.data?.label == newItem.data?.label
    }

    override fun areContentsTheSame(
        oldItem: CollectionListModel,
        newItem: CollectionListModel
    ): Boolean {
        return oldItem.data == newItem.data
    }
}