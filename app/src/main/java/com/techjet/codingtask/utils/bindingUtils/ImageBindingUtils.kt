package com.techjet.codingtask.utils.bindingUtils

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.techjet.codingtask.utils.handler.ImageHandler

object ImageBindingUtils {

    private const val SET_IMAGE_URL = "setImageUrl"

    @JvmStatic
    @BindingAdapter(SET_IMAGE_URL)
    fun setImageUrl(imageView: ImageView, url: String?) {
        if (!TextUtils.isEmpty(url)) {
            ImageHandler.getPicasso(imageView.context)
                .load(url)
                //.placeholder(R.drawable.ic_placeholder)
                .priority(Picasso.Priority.HIGH)
                .into(imageView)
        }
    }
}