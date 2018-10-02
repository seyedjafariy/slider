package com.worldsnas.imageslider

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import java.util.*

class Slider constructor(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {
    var mSliderSliderClickListener: SliderClickListener? = null
    private val snapHelper = PagerSnapHelper()
    private val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    private val timer = Timer()
    private var timerTask = SliderTimer(this)
    private val sliderAdapter: SliderAdapter = SliderAdapter(OnClickListener {view->
        onClick(view)
    })

    init {
        adapter = sliderAdapter
        layoutManager = linearLayoutManager
        snapHelper.attachToRecyclerView(this)
    }

    var slideChangeInterval: Long = 1000

    fun setInterval(interval: Long) {
        if (interval > 0) {
            slideChangeInterval = interval
            stopTimerTask()
            startTimer()
        }
    }

    private fun startTimer() {
        if (slideChangeInterval > 0) {
            timerTask = SliderTimer(this)
            timer.schedule(timerTask, slideChangeInterval, slideChangeInterval)
        }
    }

    fun stopTimerTask() {
        timerTask.cancel()
    }

    private fun stopTimer() {
        timerTask.removeView()
        timer.cancel()
        timer.purge()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startTimer()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopTimer()
    }

    fun getSliderAdapter() = sliderAdapter

    fun submitList(urls: List<String>) {
        sliderAdapter.submitList(urls)
        stopTimerTask()
        startTimer()
    }

    private fun onClick(view : View) {
        val position = view.tag as Int
        mSliderSliderClickListener?.onClick(position, sliderAdapter.getItem(position))
    }
}