package com.sifat.slushflicks.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ItemTypeTagBinding
import com.sifat.slushflicks.ui.home.adapter.viewholder.TypeTagViewModel

class TypeTagListAdapter : RecyclerView.Adapter<TypeTagViewModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeTagViewModel {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemTypeTagBinding>(
            inflater,
            R.layout.item_type_tag,
            parent,
            false
        )
        return TypeTagViewModel((binding))
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: TypeTagViewModel, position: Int) {

    }
}