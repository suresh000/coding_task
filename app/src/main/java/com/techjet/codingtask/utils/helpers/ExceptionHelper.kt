package com.techjet.codingtask.utils.helpers

import com.techjet.codingtask.utils.helpers.LogHelper.e

object ExceptionHelper {

    private var EXCEPTION_ENABLED = true

    @JvmStatic
    fun setException(isLogEnabled: Boolean) {
        EXCEPTION_ENABLED = isLogEnabled
    }

    @JvmStatic
    fun printStackTrace(e: Exception) {
        if (EXCEPTION_ENABLED) {
            e.printStackTrace()
            e("exception", e.message)
        }
    }

    @JvmStatic
    fun printStackTrace(e: Throwable) {
        if (EXCEPTION_ENABLED) {
            e.printStackTrace()
            e("exception", e.message)
        }
    }
}
