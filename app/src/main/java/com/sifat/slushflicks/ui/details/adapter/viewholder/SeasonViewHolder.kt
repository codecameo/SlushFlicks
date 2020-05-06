package com.sifat.slushflicks.ui.details.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.sifat.slushflicks.databinding.ItemSeasonBinding
import com.sifat.slushflicks.model.SeasonModel

class SeasonViewHolder(private val binding: ItemSeasonBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindTo(season: SeasonModel) {
        binding.season = season
    }
}