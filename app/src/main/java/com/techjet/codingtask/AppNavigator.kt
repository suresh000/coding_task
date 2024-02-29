package com.techjet.codingtask

import android.content.Context
import android.content.Intent
import android.net.Uri


object AppNavigator {

    @JvmStatic
    fun openBrowser(context: Context, link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context.startActivity(intent)
    }

}