package com.sifat.slushflicks.ui.details.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.sifat.slushflicks.databinding.ItemReviewBinding
import com.sifat.slushflicks.model.ReviewModel

class ReviewViewHolder(
    private val binding: ItemReviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(model: ReviewModel?) {
        model?.let { binding.model = it }
    }
}