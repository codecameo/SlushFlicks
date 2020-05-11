package com.sifat.slushflicks.ui.home.about

import androidx.lifecycle.ViewModelProvider
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.FragmentAboutBinding
import com.sifat.slushflicks.di.constant.NAME_CONTENT_FACTORY
import com.sifat.slushflicks.ui.base.BaseFragment
import javax.inject.Inject
import javax.inject.Named

class AboutFragment : BaseFragment<FragmentAboutBinding, AboutViewModel>(R.layout.fragment_about) {

    @Inject
    @field:Named(NAME_CONTENT_FACTORY)
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getViewModelClass(): Class<AboutViewModel> = AboutViewModel::class.java

    override fun provideViewModelFactory(): ViewModelProvider.Factory = viewModelFactory

}