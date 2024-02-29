package com.techjet.codingtask.utils.bindingUtils

import android.graphics.Bitmap
import android.net.Uri
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.techjet.codingtask.AppExecutorService

object ImageBindingUtils {

    private const val SET_IMAGE_URL = "setImageUrl"
    private const val IMAGE_BITMAP = "setImageBitmap"
    private const val IMAGE_IMAGE_URI = "setImageUri"

    @JvmStatic
    @BindingAdapter(SET_IMAGE_URL)
    fun setImageUrl(imageView: ImageView, url: String?) {
        if (!TextUtils.isEmpty(url)) {

            val picasso = Picasso.Builder(imageView.context).executor(AppExecutorService.singleThreadExecutor)
                .indicatorsEnabled(false).build()

            picasso.load(url)
                .priority(Picasso.Priority.HIGH)
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter(IMAGE_BITMAP)
    fun setImageBitmap(imageView: ImageView, bitmap: Bitmap?) {
        bitmap?.let {
            imageView.setImageBitmap(it)
        }
    }

    @JvmStatic
    @BindingAdapter(IMAGE_IMAGE_URI)
    fun setImageUri(imageView: ImageView, uri: Uri?) {
        if (uri != null) {
            imageView.setImageURI(null)
            imageView.setImageURI(uri)
        }
    }
}