package com.sifat.slushflicks.ui.details.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.sifat.slushflicks.ui.home.adapter.model.ShowListModel

class MovieDiffUtils : DiffUtil.ItemCallback<ShowListModel>() {
    override fun areItemsTheSame(oldItem: ShowListModel, newItem: ShowListModel): Boolean {
        return oldItem.state == newItem.state && oldItem.data?.id == newItem.data?.id
    }

    override fun areContentsTheSame(oldItem: ShowListModel, newItem: ShowListModel): Boolean {
        return oldItem.data == newItem.data
    }
}