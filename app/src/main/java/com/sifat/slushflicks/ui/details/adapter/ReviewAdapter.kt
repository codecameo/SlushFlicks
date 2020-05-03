package com.sifat.slushflicks.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ItemReviewBinding
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.ui.details.adapter.diffutils.ReviewDiffUtil
import com.sifat.slushflicks.ui.details.adapter.viewholder.ReviewViewHolder

class ReviewAdapter : PagedListAdapter<ReviewModel, ReviewViewHolder>(ReviewDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemReviewBinding>(
                inflater,
                R.layout.item_review,
                parent,
                false
            )
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}