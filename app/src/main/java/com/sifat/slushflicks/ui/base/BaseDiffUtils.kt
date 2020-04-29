package com.sifat.slushflicks.ui.base

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffUtils<Model>(
    protected var mOldList: List<Model>,
    protected var mNewList: List<Model>
) : DiffUtil.Callback() {

    fun setNewList(newList: List<Model>) {
        this.mNewList = newList
    }

    fun setOldList(oldList: List<Model>) {
        this.mOldList = oldList
    }

    override fun getOldListSize(): Int {
        return mOldList.size
    }

    override fun getNewListSize(): Int {
        return mNewList.size
    }
}