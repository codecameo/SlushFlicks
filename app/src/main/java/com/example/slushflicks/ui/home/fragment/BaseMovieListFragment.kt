package com.example.slushflicks.ui.home.fragment

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerFragment
import com.example.slushflicks.R
import com.example.slushflicks.databinding.FragmentHomeListBinding
import com.example.slushflicks.ui.base.BaseFragment

abstract class BaseMovieListFragment<VM : ViewModel> : BaseFragment<FragmentHomeListBinding, VM>(R.layout.fragment_movie_list) {


}