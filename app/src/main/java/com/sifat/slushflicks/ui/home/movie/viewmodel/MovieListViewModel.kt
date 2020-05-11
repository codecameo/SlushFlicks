package com.sifat.slushflicks.ui.home.movie.viewmodel

import com.sifat.slushflicks.di.constant.*
import com.sifat.slushflicks.di.home.movie.MovieScope
import com.sifat.slushflicks.repository.movie.MovieListRepository
import javax.inject.Inject
import javax.inject.Named

@MovieScope
class TrendingViewModel
@Inject constructor(@Named(NAME_TRENDING_MOVIE_REPO) movieListRepository: MovieListRepository) :
    BaseMovieListViewModel(movieListRepository)

@MovieScope
class PopularViewModel
@Inject constructor(@Named(NAME_POPULAR_MOVIE_REPO) movieListRepository: MovieListRepository) :
    BaseMovieListViewModel(movieListRepository)

@MovieScope
class TopRatedViewModel
@Inject constructor(@Named(NAME_TOP_RATED_MOVIE_REPO) movieListRepository: MovieListRepository) :
    BaseMovieListViewModel(movieListRepository)

@MovieScope
class NowPlayingViewModel
@Inject constructor(@Named(NAME_NOW_PLAYING_MOVIE_REPO) movieListRepository: MovieListRepository) :
    BaseMovieListViewModel(movieListRepository)

@MovieScope
class UpcomingViewModel
@Inject constructor(@Named(NAME_UPCOMING_MOVIE_REPO) movieListRepository: MovieListRepository) :
    BaseMovieListViewModel(movieListRepository)