package com.sifat.slushflicks.ui.details.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.sifat.slushflicks.model.ReviewModel

class ReviewDiffUtil : DiffUtil.ItemCallback<ReviewModel>() {
    override fun areItemsTheSame(oldItem: ReviewModel, newItem: ReviewModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ReviewModel, newItem: ReviewModel): Boolean {
        return oldItem.content == newItem.content && oldItem.author == newItem.author
    }
}