package com.sifat.slushflicks.helper

import android.net.Uri
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.sifat.slushflicks.BuildConfig
import com.sifat.slushflicks.utils.*
import com.sifat.slushflicks.utils.DynamicLinkConst.Companion.SHOW_ID_PARAM
import com.sifat.slushflicks.utils.DynamicLinkConst.Companion.SHOW_TYPE_PARAM
import java.lang.ref.WeakReference

class DynamicLinkProvider(private val baseUrl: String, private val dynamicLink: String) {

    fun generateDynamicLink(
        param: DynamicLinkParam,
        // To avoid potential memory leak
        weakCallback: WeakReference<OnEventShareCallback>
    ) {
        val socialMetaTagParameters =
            DynamicLink.SocialMetaTagParameters.Builder().setTitle(param.showName)
                .setDescription(param.overview)
                .setImageUrl(Uri.parse(param.imageUrl))
                .build()

        FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setSocialMetaTagParameters(socialMetaTagParameters)
            .setLink(Uri.parse(generateDeepLink(param.showId, param.showType)))
            .setDomainUriPrefix(HTTP_PREFIX + dynamicLink)
            // Open links with this app on Android
            .setAndroidParameters(
                DynamicLink.AndroidParameters.Builder(BuildConfig.APPLICATION_ID)
                    .setFallbackUrl(Uri.parse(DynamicLinkConst.ANDROID_FALL_BACK_URL))
                    .build()
            )
            .setGoogleAnalyticsParameters(
                DynamicLink.GoogleAnalyticsParameters.Builder()
                    .setSource(DynamicLinkConst.SOURCE)
                    .setMedium(DynamicLinkConst.MEDIUM)
                    .setCampaign(DynamicLinkConst.CAMPAIGN)
                    .build()
            )
            .buildShortDynamicLink()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    weakCallback.get()?.run {
                        onSuccess(task.result?.shortLink?.toString())
                    }
                } else {
                    weakCallback.get()?.run {
                        onFailure()
                    }
                }
            }
    }

    private fun generateDeepLink(showId: Long, showType: String): String? {
        val dynamicLinkUrl = StringBuilder(baseUrl)
            .append(QUESTION_MARK)
            .append(SHOW_ID_PARAM)
            .append(EQUAL_SIGN)
            .append(showId)
            .append(AMPERSAND)
            .append(SHOW_TYPE_PARAM)
            .append(EQUAL_SIGN)
            .append(showType)
        return dynamicLinkUrl.toString()
    }

    data class DynamicLinkParam(
        val showId: Long,
        val showType: String,
        val showName: String,
        val overview: String,
        val imageUrl: String
    )

    interface OnEventShareCallback {
        fun onSuccess(shortUrl: String?)
        fun onFailure()
    }
}