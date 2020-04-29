package com.sifat.slushflicks.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ItemTypeTagBinding
import com.sifat.slushflicks.ui.helper.getCopiedCollectionListModel
import com.sifat.slushflicks.ui.home.adapter.diffutils.CollectionDiffUtils
import com.sifat.slushflicks.ui.home.adapter.model.CollectionListModel
import com.sifat.slushflicks.ui.home.adapter.viewholder.TypeTagViewModel

class TypeTagListAdapter :
    ListAdapter<CollectionListModel, TypeTagViewModel>(CollectionDiffUtils()) {

    lateinit var onCollectionClickListener: TypeTagViewModel.OnCollectionClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeTagViewModel {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemTypeTagBinding>(
            inflater,
            R.layout.item_type_tag,
            parent,
            false
        )
        return TypeTagViewModel(binding, onCollectionClickListener)
    }

    override fun onBindViewHolder(holder: TypeTagViewModel, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun submitList(list: List<CollectionListModel>?) {
        super.submitList(getCopiedCollectionListModel(list ?: emptyList()))
    }
}