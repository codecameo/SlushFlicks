package com.sifat.slushflicks.ui.home.about

import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.FragmentAboutBinding
import com.sifat.slushflicks.ui.base.BaseFragment

class AboutFragment : BaseFragment<FragmentAboutBinding, AboutViewModel>(R.layout.fragment_about) {
    override fun getViewModelClass(): Class<AboutViewModel> = AboutViewModel::class.java

}