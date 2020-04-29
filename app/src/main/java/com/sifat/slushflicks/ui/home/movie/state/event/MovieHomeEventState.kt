package com.sifat.slushflicks.ui.home.movie.state.event

import com.sifat.slushflicks.model.CollectionModel

/**
 * This events will be fired from the view to viewmodel;
 * based on the event, viewmodel will take necessary action
 * */
sealed class MovieHomeEventState {
    class MovieCollectionEvent(val forceUpdate: Boolean = true) : MovieHomeEventState()
    class MovieCollectionClickEvent(val index: Int, val collectionModel: CollectionModel) :
        MovieHomeEventState()
}