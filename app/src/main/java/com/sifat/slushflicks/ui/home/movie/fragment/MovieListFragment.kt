package com.sifat.slushflicks.ui.home.movie.fragment

import com.sifat.slushflicks.ui.home.movie.viewmodel.*

class TrendingFragment : BaseMovieListFragment<TrendingViewModel>() {
    override fun getViewModelClass() = TrendingViewModel::class.java
}

class PopularFragment : BaseMovieListFragment<PopularViewModel>() {
    override fun getViewModelClass() = PopularViewModel::class.java
}

class TopRatedFragment : BaseMovieListFragment<TopRatedViewModel>() {
    override fun getViewModelClass() = TopRatedViewModel::class.java
}

class UpcomingFragment : BaseMovieListFragment<UpcomingViewModel>() {
    override fun getViewModelClass() = UpcomingViewModel::class.java
}

class NowPlayingFragment : BaseMovieListFragment<NowPlayingViewModel>() {
    override fun getViewModelClass() = NowPlayingViewModel::class.java
}