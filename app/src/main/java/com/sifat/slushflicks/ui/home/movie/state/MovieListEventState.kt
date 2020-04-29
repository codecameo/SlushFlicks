package com.sifat.slushflicks.ui.home.movie.state

sealed class MovieListEventState {
    class FetchMovieListEvent(val forceUpdate: Boolean = true) : MovieListEventState()
}