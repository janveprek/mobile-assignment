package com.veprek.honza.rocketlaunch.repository.api

import android.content.Context
import android.content.ContextWrapper
import okhttp3.OkHttpClient
import okhttp3.Request
import quanti.com.kotlinlog.Log
import java.io.File
import java.io.FileOutputStream

class DownloadManager(private val context: Context) {

    suspend fun downloadFile(
        url: String
    ): String {
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder().url(url).build()

        val executed = client.newCall(request).execute()
        val body = executed.body

        val wrapper = ContextWrapper(context)
        val file = File(wrapper.filesDir, "test.jpg")
        val path = file.path
        Log.d("File name: $path")
        if (!file.exists()) {
            file.createNewFile()
        }

        val outputStream = FileOutputStream(file)

        body?.let {
            val bytes = body.bytes()
            Log.d("Delka: ${bytes.size} bytu")
            with(outputStream) {
                write(bytes)
                close()
            }
        }
        return path
    }
}
