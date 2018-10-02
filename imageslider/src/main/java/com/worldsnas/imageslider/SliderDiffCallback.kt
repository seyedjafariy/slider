package com.worldsnas.imageslider

import android.support.v7.util.DiffUtil
import android.text.TextUtils

class SliderDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(p0: String, p1: String): Boolean =
            TextUtils.equals(p0, p1)

    override fun areContentsTheSame(p0: String, p1: String): Boolean = false
}