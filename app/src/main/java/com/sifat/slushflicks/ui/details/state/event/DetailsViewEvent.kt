package com.sifat.slushflicks.ui.details.state.event

sealed class DetailsViewEvent {
    data class FetchDetailsViewEvent(val forceUpdate: Boolean = true) : DetailsViewEvent()
}