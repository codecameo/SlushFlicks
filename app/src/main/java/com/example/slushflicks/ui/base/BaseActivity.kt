package com.example.slushflicks.ui.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseActivity<DB : ViewDataBinding, VM : ViewModel>  : DaggerAppCompatActivity() {

    protected lateinit var binding: DB
    protected lateinit var viewModel: VM
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    protected abstract fun getViewModelClass() : Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
    }

    init {
        val TAG : String = this.javaClass.name
    }

}