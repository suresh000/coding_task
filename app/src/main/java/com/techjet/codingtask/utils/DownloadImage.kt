package com.techjet.codingtask.utils

import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.URL


class DownloadImage {

    companion object {

        @JvmStatic
        fun download(link: String, path: String) {
            URL(link).openStream().use { input ->
                FileOutputStream(File(path)).use { output ->
                    input.copyTo(output)
                }
            }
        }

        @JvmStatic
        fun download(downloadUrl: String, file: File, callback: (Boolean) -> Unit) {
            var count = 0
            try {
                val url = URL(downloadUrl)
                val connection = url.openConnection()
                connection.connect()

                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                val lengthOfFile = connection.contentLength

                // download the file
                val input: InputStream = BufferedInputStream(url.openStream(), lengthOfFile)

                // Output stream
                val output: OutputStream = FileOutputStream(file)

                val data = ByteArray(1024)

                while (input.read(data).also { count = it } != -1) {
                    output.write(data, 0, count)
                }

                output.flush()

                output.close()
                input.close()

                callback.invoke(true)

            } catch (e: Exception) {
                callback.invoke(false)
            }

        }

    }

}