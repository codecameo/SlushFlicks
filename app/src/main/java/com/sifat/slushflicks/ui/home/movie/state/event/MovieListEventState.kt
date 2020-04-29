package com.sifat.slushflicks.ui.home.movie.state.event

/**
 * This events will be fired from the view to viewmodel;
 * based on the event, viewmodel will take necessary action
 * */
sealed class MovieListEventState {
    class FetchMovieListEvent(val forceUpdate: Boolean = true) : MovieListEventState()
}