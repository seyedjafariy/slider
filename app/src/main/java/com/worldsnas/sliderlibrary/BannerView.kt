package com.worldsnas.sliderlibrary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.airbnb.epoxy.*
import com.worldsnas.sliderlibrary.databinding.BannerViewBinding

@ModelView(
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    saveViewState = true
)
class BannerView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {
    private val binding: BannerViewBinding =
            BannerViewBinding.inflate(LayoutInflater.from(context), this, true)

    lateinit var imageUrl : String

    @ModelProp
    fun bindImage(image : String) {
        imageUrl = image
        binding.image.setImageURI(image)
    }

    @CallbackProp
    fun listener(listener: ((imgUrl : String) -> Unit)?) {
        if (listener == null) {
            binding.root.setOnClickListener(null)
        } else {
            binding.root.setOnClickListener {
                listener(imageUrl)
            }
        }
    }
}
