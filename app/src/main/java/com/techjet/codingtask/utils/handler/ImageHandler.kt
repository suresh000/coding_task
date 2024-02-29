package com.techjet.codingtask.utils.handler

import android.content.Context
import com.squareup.picasso.Picasso
import com.techjet.codingtask.AppExecutorService

object ImageHandler {

    fun getPicasso(context: Context): Picasso {
        return Picasso.Builder(context).executor(AppExecutorService.singleThreadExecutor)
            .indicatorsEnabled(false).build()
    }

}