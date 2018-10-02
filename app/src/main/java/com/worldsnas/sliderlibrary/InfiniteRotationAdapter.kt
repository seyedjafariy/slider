package com.worldsnas.sliderlibrary

import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide

class InfiniteRotationAdapter(itemList: List<ItemInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.let {
            Glide.with(it.image).load(list[position % list.size].url).into(it.image)
            it.pageName.text = list[position % list.size].page
//            it.itemView.setBackgroundColor(list[position % list.size].colorInt)
            it.pagePosition.text =
                    it.itemView.resources.getString(R.string.actual_position, position.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_simple, parent, false)
        return ItemViewHolder(view)
    }

    private val list: List<ItemInfo> = listOf(itemList.last()) + itemList + listOf(itemList.first())

    override fun getItemCount() = list.size

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var pageName: TextView
        var pagePosition: TextView
        var image: AppCompatImageView

        init {
            pageName = view.findViewById(R.id.page_name)
            image = view.findViewById(R.id.image)
            pagePosition = view.findViewById(R.id.page_position)
        }
    }
}