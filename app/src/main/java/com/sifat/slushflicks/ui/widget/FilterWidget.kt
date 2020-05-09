package com.sifat.slushflicks.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.SearchFilterWidgetBinding
import com.sifat.slushflicks.utils.ShowType

class FilterWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle), View.OnClickListener {

    private val binding: SearchFilterWidgetBinding
    private var showType = ShowType.MOVIE
    var completeListener: OnCompleteListener? = null

    init {
        val inflater = LayoutInflater.from(context)
        binding = SearchFilterWidgetBinding.inflate(inflater, this, true)
        initListener()
    }

    private fun initListener() {
        binding.tvMovie.setOnClickListener(this)
        binding.tvTvShow.setOnClickListener(this)
        binding.tvDone.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.tvDone.id -> {
                complete()
            }
            binding.tvMovie.id -> {
                selectMovieType()
            }
            binding.tvTvShow.id -> {
                selectTvSeriesType()
            }
        }
    }

    private fun complete() {
        completeListener?.onComplete(showType)
    }

    fun setInitShowType(showType: ShowType) {
        when (showType) {
            ShowType.TV_SERIES -> {
                selectTvSeriesType()
            }
            ShowType.MOVIE -> {
                selectMovieType()
            }
        }
    }

    private fun selectTvSeriesType() {
        showType = ShowType.TV_SERIES
        setSelectState(binding.tvTvShow)
        setUnselectState(binding.tvMovie)
    }

    private fun selectMovieType() {
        showType = ShowType.MOVIE
        setSelectState(binding.tvMovie)
        setUnselectState(binding.tvTvShow)
    }

    private fun setSelectState(view: TextView) {
        view.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check, 0)
    }

    private fun setUnselectState(view: TextView) {
        view.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
    }

    interface OnCompleteListener {
        fun onComplete(showType: ShowType)
    }
}