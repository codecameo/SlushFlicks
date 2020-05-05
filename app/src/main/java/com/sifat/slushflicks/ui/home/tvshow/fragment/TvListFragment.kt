package com.sifat.slushflicks.ui.home.tvshow.fragment

import com.sifat.slushflicks.ui.home.tvshow.viewmodel.TrendingTvViewModel


class TrendingTvFragment : BaseTvListFragment<TrendingTvViewModel>() {
    override fun getViewModelClass() = TrendingTvViewModel::class.java
}