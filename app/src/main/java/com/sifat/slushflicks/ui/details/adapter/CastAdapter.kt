package com.sifat.slushflicks.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ItemCastBinding
import com.sifat.slushflicks.model.CastModel
import com.sifat.slushflicks.ui.details.adapter.diffutils.CastDiffUtils
import com.sifat.slushflicks.ui.details.adapter.viewholder.CastViewHolder
import java.util.*

class CastAdapter : ListAdapter<CastModel, CastViewHolder>(CastDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemCastBinding>(inflater, R.layout.item_cast, parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun submitList(list: MutableList<CastModel>?) {
        list?.let {
            it.sortWith(Comparator { castModel: CastModel, castModel1: CastModel ->
                castModel.order - castModel1.order
            })
        }
        super.submitList(list)
    }
}