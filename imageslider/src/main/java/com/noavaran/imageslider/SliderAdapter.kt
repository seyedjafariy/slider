package com.noavaran.imageslider

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SliderAdapter (private val listener : View.OnClickListener): ListAdapter<String, SliderViewHolder>(SliderDiffCallback()){


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SliderViewHolder =
            SliderViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.row_slide, p0, false))

    override fun onBindViewHolder(p0: SliderViewHolder, p1: Int) {
        p0.bind(getItem(p1))
        p0.setOnClickListener(listener)
    }

    public override fun getItem(position: Int): String {
        return super.getItem(position)
    }
}