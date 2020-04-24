package com.example.slushflicks.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.slushflicks.R
import com.example.slushflicks.databinding.ItemMovieBinding
import com.example.slushflicks.databinding.ItemTypeTagBinding
import com.example.slushflicks.ui.home.adapter.viewholder.MovieViewHolder
import com.example.slushflicks.ui.home.adapter.viewholder.TypeTagViewModel

class TypeTagListAdapter : RecyclerView.Adapter<TypeTagViewModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeTagViewModel {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemTypeTagBinding>(inflater, R.layout.item_type_tag, parent, false)
        return TypeTagViewModel((binding))
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: TypeTagViewModel, position: Int) {

    }
}