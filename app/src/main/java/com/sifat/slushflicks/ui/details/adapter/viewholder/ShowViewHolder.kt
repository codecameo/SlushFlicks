package com.sifat.slushflicks.ui.details.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sifat.slushflicks.databinding.ItemRelatedMovieBinding
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.home.adapter.model.ShowListModel

class ShowViewHolder(
    private val binding: ItemRelatedMovieBinding,
    private val onShowClickListener: OnShowClickListener
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    init {
        binding.root.setOnClickListener(this)
    }

    fun bindTo(model: ShowListModel) {
        binding.model = model.data
        binding.state = model.state
    }

    interface OnShowClickListener {
        fun onShowClicked(showModelMinimal: ShowModelMinimal)
    }

    override fun onClick(view: View?) {
        binding.model?.run {
            onShowClickListener.onShowClicked(this)
        }
    }
}