package com.sifat.slushflicks

import android.app.Activity
import android.app.Fragment
import android.app.Service
import android.content.BroadcastReceiver
import android.content.ContentProvider
import androidx.multidex.MultiDexApplication
import com.sifat.slushflicks.di.app.DaggerAppComponent
import dagger.android.*
import javax.inject.Inject

/**
 * Used [DaggerApplication] before then came across multidex issue after adding firebase
 * changed the implementation as mentioned here
 * {@link https://github.com/google/dagger/issues/1035}
 * */
class SlushFlicksApplication : MultiDexApplication(), HasActivityInjector,
    HasFragmentInjector,
    HasServiceInjector,
    HasBroadcastReceiverInjector,
    HasContentProviderInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var broadcastReceiverInjector: DispatchingAndroidInjector<BroadcastReceiver>
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>
    @Inject
    lateinit var contentProviderInjector: DispatchingAndroidInjector<ContentProvider>
    @Volatile
    private var needToInject = true

    override fun onCreate() {
        super.onCreate()
        injectIfNecessary()
    }


    private fun injectIfNecessary() {
        if (needToInject) {
            synchronized(this) {
                if (needToInject) {
                    val applicationInjector =
                        applicationInjector()
                    applicationInjector.inject(this)
                    check(!needToInject) {
                        ("The AndroidInjector returned from applicationInjector() did not inject the "
                                + "DaggerApplication")
                    }
                }
            }
        }
    }


    private fun applicationInjector(): AndroidInjector<SlushFlicksApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    @Inject
    fun setInjected() {
        needToInject = false
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun fragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun serviceInjector(): AndroidInjector<Service> = serviceInjector

    override fun broadcastReceiverInjector(): AndroidInjector<BroadcastReceiver> =
        broadcastReceiverInjector

    override fun contentProviderInjector(): AndroidInjector<ContentProvider> {
        injectIfNecessary()
        return contentProviderInjector
    }
}