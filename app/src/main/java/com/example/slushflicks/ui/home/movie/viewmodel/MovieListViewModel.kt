package com.example.slushflicks.ui.home.movie.viewmodel

import com.example.slushflicks.di.home.HomeScope
import com.example.slushflicks.repository.*
import javax.inject.Inject

@HomeScope
class TrendingViewModel
@Inject constructor(trendingRepository: TrendingRepository) :
    BaseMovieListViewModel(trendingRepository)

@HomeScope
class PopularViewModel
@Inject constructor(popularMovieRepository: PopularMovieRepository) :
    BaseMovieListViewModel(popularMovieRepository)

@HomeScope
class TopRatedViewModel
@Inject constructor(topRatedRepository: TopRatedRepository) :
    BaseMovieListViewModel(topRatedRepository)

@HomeScope
class NowPlayingViewModel
@Inject constructor(nowPlayingRepository: NowPlayingRepository) :
    BaseMovieListViewModel(nowPlayingRepository)

@HomeScope
class UpcomingViewModel
@Inject constructor(upcomingRepository: UpcomingRepository) :
    BaseMovieListViewModel(upcomingRepository)