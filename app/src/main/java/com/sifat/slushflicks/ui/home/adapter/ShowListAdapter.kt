package com.sifat.slushflicks.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ItemShowBinding
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.base.ListViewState
import com.sifat.slushflicks.ui.home.adapter.diffutils.MovieDiffUtils
import com.sifat.slushflicks.ui.home.adapter.viewholder.ShowViewHolder

class ShowListAdapter : PagedListAdapter<ShowModelMinimal, ShowViewHolder>(MovieDiffUtils()) {

    var loadingState = ListViewState.LOADING
    lateinit var onShowClickListener: ShowViewHolder.OnShowClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemShowBinding>(inflater, R.layout.item_show, parent, false)
        return ShowViewHolder(binding, onShowClickListener)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bindTo(
                model = movie,
                state = loadingState
            )
        }
    }
}