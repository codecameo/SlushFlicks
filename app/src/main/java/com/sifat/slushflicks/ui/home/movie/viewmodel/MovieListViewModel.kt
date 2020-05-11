package com.sifat.slushflicks.ui.home.movie.viewmodel

import com.sifat.slushflicks.di.constant.*
import com.sifat.slushflicks.di.home.movie.MovieListScope
import com.sifat.slushflicks.repository.movie.MovieListRepository
import javax.inject.Inject
import javax.inject.Named

@MovieListScope
class TrendingViewModel
@Inject constructor(@Named(NAME_TRENDING_MOVIE_REPO) movieListRepository: MovieListRepository) :
    BaseMovieListViewModel(movieListRepository)

@MovieListScope
class PopularViewModel
@Inject constructor(@Named(NAME_POPULAR_MOVIE_REPO) movieListRepository: MovieListRepository) :
    BaseMovieListViewModel(movieListRepository)

@MovieListScope
class TopRatedViewModel
@Inject constructor(@Named(NAME_TOP_RATED_MOVIE_REPO) movieListRepository: MovieListRepository) :
    BaseMovieListViewModel(movieListRepository)

@MovieListScope
class NowPlayingViewModel
@Inject constructor(@Named(NAME_NOW_PLAYING_MOVIE_REPO) movieListRepository: MovieListRepository) :
    BaseMovieListViewModel(movieListRepository)

@MovieListScope
class UpcomingViewModel
@Inject constructor(@Named(NAME_UPCOMING_MOVIE_REPO) movieListRepository: MovieListRepository) :
    BaseMovieListViewModel(movieListRepository)