package com.techjet.codingtask.utils.helpers

import android.text.TextUtils
import android.util.Log

object LogHelper {

    private var LOG_ENABLED = true

    @JvmStatic
    fun v(tag: String?, message: String?) {
        if (LOG_ENABLED && !TextUtils.isEmpty(message)) {
            Log.v(tag, message!!)
        }
    }

    @JvmStatic
    fun e(tag: String?, message: String?) {
        if (LOG_ENABLED && !TextUtils.isEmpty(message)) {
            Log.e(tag, message!!)
        }
    }

    @JvmStatic
    fun e(tag: String?, message: String?, ex: Exception?) {
        if (LOG_ENABLED && !TextUtils.isEmpty(message)) {
            Log.e(tag, message, ex)
        }
    }

    @JvmStatic
    fun e(tag: String?, message: String?, e: Error?) {
        if (LOG_ENABLED && !TextUtils.isEmpty(message)) {
            Log.e(tag, message, e)
        }
    }

    @JvmStatic
    fun d(tag: String?, message: String?) {
        if (LOG_ENABLED && !TextUtils.isEmpty(message)) {
            Log.d(tag, message!!)
        }
    }

    @JvmStatic
    fun i(tag: String?, message: String?) {
        if (LOG_ENABLED && !TextUtils.isEmpty(message)) {
            Log.i(tag, message!!)
        }
    }

    @JvmStatic
    fun w(tag: String?, message: String?) {
        if (LOG_ENABLED && !TextUtils.isEmpty(message)) {
            Log.w(tag, message!!)
        }
    }

    @JvmStatic
    fun w(tag: String?, message: String?, ex: Exception?) {
        if (LOG_ENABLED && !TextUtils.isEmpty(message)) {
            Log.w(tag, message, ex)
        }
    }

    @JvmStatic
    fun setLog(isLogEnabled: Boolean) {
        LOG_ENABLED = isLogEnabled
    }

}
