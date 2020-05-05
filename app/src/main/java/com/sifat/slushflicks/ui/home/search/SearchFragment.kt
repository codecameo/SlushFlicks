package com.sifat.slushflicks.ui.home.search

import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.FragmentSearchBinding
import com.sifat.slushflicks.ui.base.BaseFragment

class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {
    override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java
}