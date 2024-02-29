package com.techjet.codingtask.utils.handler

import android.content.Context
import com.squareup.picasso.Cache
import com.squareup.picasso.Picasso
import com.techjet.codingtask.AppExecutorService

object ImageHandler {

    fun getPicasso(context: Context): Picasso {
        return Picasso.Builder(context).executor(AppExecutorService.singleThreadExecutor)
            .memoryCache(Cache.NONE).indicatorsEnabled(false).build()
    }

}