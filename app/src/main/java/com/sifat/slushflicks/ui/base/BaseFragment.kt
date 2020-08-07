package com.sifat.slushflicks.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseFragment<DB : ViewDataBinding, VM : ViewModel>(@LayoutRes resId: Int) :
    Fragment(resId), HasSupportFragmentInjector {

    protected lateinit var binding: DB
    protected lateinit var viewModel: VM

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment?>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        viewModel = ViewModelProvider(this, provideViewModelFactory()).get(getViewModelClass())
    }

    abstract fun getViewModelClass(): Class<VM>
    abstract fun provideViewModelFactory(): ViewModelProvider.Factory

    override fun supportFragmentInjector(): AndroidInjector<Fragment?>? {
        return childFragmentInjector
    }
}