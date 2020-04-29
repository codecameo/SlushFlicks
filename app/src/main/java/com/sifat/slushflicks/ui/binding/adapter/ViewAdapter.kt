package com.sifat.slushflicks.ui.binding.adapter

import androidx.databinding.BindingAdapter
import com.facebook.shimmer.ShimmerFrameLayout
import com.sifat.slushflicks.ui.base.ListViewState

@BindingAdapter("shimmer")
fun ShimmerFrameLayout.setShimmer(listViewState: ListViewState) {
    if (listViewState == ListViewState.LOADING) {
        startShimmer()
    } else {
        hideShimmer()
    }
}