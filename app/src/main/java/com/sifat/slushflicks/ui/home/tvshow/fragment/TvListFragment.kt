package com.sifat.slushflicks.ui.home.tvshow.fragment

import com.sifat.slushflicks.ui.home.tvshow.viewmodel.AirTodayTvViewModel
import com.sifat.slushflicks.ui.home.tvshow.viewmodel.PopularTvViewModel
import com.sifat.slushflicks.ui.home.tvshow.viewmodel.TopRatedTvViewModel
import com.sifat.slushflicks.ui.home.tvshow.viewmodel.TrendingTvViewModel


class TrendingTvFragment : BaseTvListFragment<TrendingTvViewModel>() {
    override fun getViewModelClass() = TrendingTvViewModel::class.java
}

class PopularTvFragment : BaseTvListFragment<PopularTvViewModel>() {
    override fun getViewModelClass() = PopularTvViewModel::class.java
}

class AirTodayTvFragment : BaseTvListFragment<AirTodayTvViewModel>() {
    override fun getViewModelClass() = AirTodayTvViewModel::class.java
}

class TopRatedTvFragment : BaseTvListFragment<TopRatedTvViewModel>() {
    override fun getViewModelClass() = TopRatedTvViewModel::class.java
}