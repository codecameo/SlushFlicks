package com.sifat.slushflicks.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ItemSeasonBinding
import com.sifat.slushflicks.model.SeasonModel
import com.sifat.slushflicks.ui.details.adapter.diffutils.SeasonDiffUtils
import com.sifat.slushflicks.ui.details.adapter.viewholder.SeasonViewHolder

class SeasonAdapter : ListAdapter<SeasonModel, SeasonViewHolder>(SeasonDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemSeasonBinding>(
                inflater,
                R.layout.item_season,
                parent,
                false
            )
        return SeasonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}