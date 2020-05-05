package com.sifat.slushflicks.ui.home.movie.viewmodel

import com.sifat.slushflicks.di.home.HomeScope
import com.sifat.slushflicks.repository.movie.*
import javax.inject.Inject

@HomeScope
class TrendingViewModel
@Inject constructor(trendingMovieRepository: TrendingMovieRepository) :
    BaseMovieListViewModel(trendingMovieRepository)

@HomeScope
class PopularViewModel
@Inject constructor(popularMovieRepository: PopularMovieRepository) :
    BaseMovieListViewModel(popularMovieRepository)

@HomeScope
class TopRatedViewModel
@Inject constructor(topRatedMovieRepository: TopRatedMovieRepository) :
    BaseMovieListViewModel(topRatedMovieRepository)

@HomeScope
class NowPlayingViewModel
@Inject constructor(nowPlayingRepository: NowPlayingRepository) :
    BaseMovieListViewModel(nowPlayingRepository)

@HomeScope
class UpcomingViewModel
@Inject constructor(upcomingMovieRepository: UpcomingMovieRepository) :
    BaseMovieListViewModel(upcomingMovieRepository)