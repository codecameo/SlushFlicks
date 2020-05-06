package com.sifat.slushflicks.ui.details

import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ActivityTvDetailsBinding
import com.sifat.slushflicks.ui.base.FullScreenActivity
import com.sifat.slushflicks.ui.details.viewmodel.MovieDetailsViewModel

class TvDetailsActivity : FullScreenActivity<ActivityTvDetailsBinding, MovieDetailsViewModel>() {
    override fun getLayoutRes() = R.layout.activity_tv_details

    override fun getViewModelClass(): Class<MovieDetailsViewModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}