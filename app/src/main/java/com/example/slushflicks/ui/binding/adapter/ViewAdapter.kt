package com.example.slushflicks.ui.binding.adapter

import androidx.databinding.BindingAdapter
import com.example.slushflicks.ui.base.ListViewState
import com.facebook.shimmer.ShimmerFrameLayout

@BindingAdapter("shimmer")
fun ShimmerFrameLayout.setShimmer(listViewState: ListViewState) {
    if (listViewState == ListViewState.LOADING) {
        startShimmer()
    } else {
        hideShimmer()
    }
}