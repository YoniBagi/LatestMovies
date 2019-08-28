package com.yehonatan.latestmovies.Util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class BindingAdapters private constructor(){
    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.context).load(Consts.BASE_URL_IMAGE + imageUrl)
                .override(600, 600)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                .into(view)
        }

        @JvmStatic
        @BindingAdapter("loadImageCircle")
        fun loadImageCircle(view: ImageView, imageUrl: String){
            Glide.with(view.context).load(Consts.BASE_URL_IMAGE + imageUrl)
                .override(300, 300)
                .apply(RequestOptions.circleCropTransform())
                .into(view)
        }
    }
}