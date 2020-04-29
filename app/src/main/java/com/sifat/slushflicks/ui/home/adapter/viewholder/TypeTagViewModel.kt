package com.sifat.slushflicks.ui.home.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sifat.slushflicks.databinding.ItemTypeTagBinding
import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.base.ListViewState
import com.sifat.slushflicks.ui.home.adapter.model.CollectionListModel

class TypeTagViewModel(
    private val binding: ItemTypeTagBinding,
    private val onCollectionClickListener: OnCollectionClickListener
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private var collectionListModel: CollectionListModel? = null

    init {
        binding.root.setOnClickListener(this)
    }

    fun bindTo(collection: CollectionListModel?) {
        collectionListModel = collection
        collection?.let {
            binding.state = collection.state
            binding.model = collection.data
            return
        }
        binding.state = ListViewState.LOADING
    }

    override fun onClick(view: View?) {
        if (collectionListModel != null && collectionListModel!!.data != null) {
            if (collectionListModel!!.state == ListViewState.LOADING) return
            onCollectionClickListener.onCollectionClicked(
                adapterPosition,
                collectionListModel!!.data!!
            )
        }
    }

    interface OnCollectionClickListener {
        fun onCollectionClicked(index: Int, collectionModel: CollectionModel)
    }
}