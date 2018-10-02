package com.worldsnas.imageslider

import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide

class SliderViewHolder (view: View): RecyclerView.ViewHolder(view){

    private val image = itemView.findViewById<AppCompatImageView>(R.id.slideImage)

    fun bind(url : String){
        itemView.tag = adapterPosition
        Glide.with(image).load(url).into(image)
    }

    fun setOnClickListener(listener: View.OnClickListener?){
        itemView.setOnClickListener(listener)
    }
}