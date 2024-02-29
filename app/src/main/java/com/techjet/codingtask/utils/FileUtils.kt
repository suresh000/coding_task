package com.techjet.codingtask.utils

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import java.io.File

class FileUtils private constructor() {

    init {
        throw IllegalStateException(javaClass.name)
    }

    companion object {
        const val FILE_PROVIDER = "com.techjet.codingtask.fileprovider"

        @JvmStatic
        fun getAppCacheDir(context: Context): String {
            val dir = File(
                context.cacheDir.toString()
                        + File.separator
                        + "Coding"
                        + File.separator
            )
            if (!dir.exists()) {
                dir.mkdirs()
            }
            return dir.path + File.separator
        }

        @JvmStatic
        fun isExit(imagePath: String?): Boolean {
            return if (!TextUtils.isEmpty(imagePath)) {
                val uri = Uri.parse(imagePath)
                val file = File(uri.path.toString())
                file.exists()
            } else {
                false
            }
        }

        @JvmStatic
        fun deleteFile(file: File?): Boolean {
            return if (file != null && file.exists()) {
                file.delete()
            } else false
        }

    }
}