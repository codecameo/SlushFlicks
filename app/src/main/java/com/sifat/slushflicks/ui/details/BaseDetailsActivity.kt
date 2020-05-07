package com.sifat.slushflicks.ui.details

import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.sifat.slushflicks.R
import com.sifat.slushflicks.ui.base.FullScreenActivity
import com.sifat.slushflicks.ui.details.adapter.CastAdapter
import com.sifat.slushflicks.ui.details.adapter.RelatedMovieAdapter
import com.sifat.slushflicks.ui.details.adapter.ReviewAdapter
import com.sifat.slushflicks.utils.PLAIN_TEXT_TYPE
import com.sifat.slushflicks.utils.showToast

abstract class BaseDetailsActivity<DB : ViewDataBinding, VM : ViewModel> :
    FullScreenActivity<DB, VM>() {

    protected lateinit var castAdapter: CastAdapter
    protected lateinit var recommendedAdapter: RelatedMovieAdapter
    protected lateinit var similarAdapter: RelatedMovieAdapter
    protected lateinit var reviewAdapter: ReviewAdapter

    protected lateinit var shareMenuItem: MenuItem

    /**
     * Set toolbar into actionbar.
     */
    protected fun setupToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    protected open fun initVariable() {
        castAdapter = CastAdapter()
        recommendedAdapter = RelatedMovieAdapter()
        similarAdapter = RelatedMovieAdapter()
        reviewAdapter = ReviewAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            val inflater = menuInflater
            inflater.inflate(R.menu.detail_menu, it)
            shareMenuItem = it.findItem(R.id.menu_share)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.menu_share -> {
                shareShow()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    abstract fun shareShow()

    private fun openYoutube(videoId: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(String.format(YOUTUBE_VIDEO_LINK, videoId))
        )
        intent.`package` = YOUTUBE_PACKAGE_NAME
        val activeApp = packageManager.queryIntentActivities(intent, 0)
        if (activeApp.isNotEmpty()) {
            startActivity(intent)
        } else {
            showToast(getString(R.string.install_youtube))
        }
    }

    protected fun shareShow(dynamicLink: String) {
        val message = String.format(getString(R.string.text_friend_message), dynamicLink)
        try {
            val i = Intent(Intent.ACTION_SEND)
            i.type = PLAIN_TEXT_TYPE
            i.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(Intent.createChooser(i, getString(R.string.text_share_to)))
        } catch (e: Exception) {
            showToast(getString(R.string.error_no_app_found))
        }
    }

    protected fun showTrailer(videoUrl: String?) {
        if (!videoUrl.isNullOrEmpty()) {
            openYoutube(videoUrl)
        }
    }

    companion object {
        const val KEY_SHOW_ID = "key_show_id"
        const val YOUTUBE_PACKAGE_NAME = "com.google.android.youtube"
        const val YOUTUBE_VIDEO_LINK = "https://www.youtube.com/watch?v=%s"
    }
}