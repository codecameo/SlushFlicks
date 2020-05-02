package com.sifat.slushflicks.ui.details.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sifat.slushflicks.databinding.ItemCastBinding
import com.sifat.slushflicks.model.CastModel

class CastViewHolder(private val binding: ItemCastBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(castModel: CastModel) {
        binding.model = castModel
    }
}