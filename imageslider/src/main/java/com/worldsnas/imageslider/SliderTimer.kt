package com.worldsnas.imageslider

import android.os.Handler
import android.os.Looper
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.lang.ref.WeakReference
import java.util.*

internal class SliderTimer(recyclerView: RecyclerView) : TimerTask() {
    private val view: WeakReference<RecyclerView> = WeakReference(recyclerView)
    private val handler = Handler(Looper.getMainLooper())

    fun removeView() {
        view.clear()
    }

    override fun run() {
        handler.post {
            view.get()?.also {
                val layoutManager = it.layoutManager as LinearLayoutManager
                val totalCount = it.adapter?.itemCount ?: 0
                val next = layoutManager.findFirstCompletelyVisibleItemPosition() + 1

                if (totalCount == 0) {
                    return@post
                }
                if (next < totalCount) {
                    view.get()?.smoothScrollToPosition(next)
                } else if (totalCount > 0) {
                    view.get()?.scrollToPosition(0)
                }
            }
        }
    }
}