package com.sifat.slushflicks.ui.details

import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.sifat.slushflicks.R
import com.sifat.slushflicks.ui.base.FullScreenActivity
import com.sifat.slushflicks.ui.details.adapter.CastAdapter
import com.sifat.slushflicks.ui.details.adapter.RelatedMovieAdapter
import com.sifat.slushflicks.ui.details.adapter.ReviewAdapter
import com.sifat.slushflicks.utils.showToast

abstract class BaseDetailsActivity<DB : ViewDataBinding, VM : ViewModel> :
    FullScreenActivity<DB, VM>() {

    protected lateinit var castAdapter: CastAdapter
    protected lateinit var recommendedAdapter: RelatedMovieAdapter
    protected lateinit var similarAdapter: RelatedMovieAdapter
    protected lateinit var reviewAdapter: ReviewAdapter

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

    protected fun initVariable() {
        castAdapter = CastAdapter()
        recommendedAdapter = RelatedMovieAdapter()
        similarAdapter = RelatedMovieAdapter()
        reviewAdapter = ReviewAdapter()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

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