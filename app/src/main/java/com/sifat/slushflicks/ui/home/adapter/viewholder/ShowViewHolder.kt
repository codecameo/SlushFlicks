package com.sifat.slushflicks.ui.home.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sifat.slushflicks.databinding.ItemShowBinding
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.base.ListViewState

class ShowViewHolder(
    private val binding: ItemShowBinding,
    private val onShowClickListener: OnShowClickListener
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    init {
        binding.root.setOnClickListener(this)
    }

    fun bindTo(model: ShowModelMinimal, state: ListViewState) {
        binding.model = model
        binding.state = state
    }

    override fun onClick(view: View?) {
        binding.model?.let { model ->
            onShowClickListener.onShowClicked(model)
        }
    }

    interface OnShowClickListener {
        fun onShowClicked(model: ShowModelMinimal)
    }
}