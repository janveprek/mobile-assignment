package com.veprek.honza.rocketlaunch.repository.api

import android.content.Context
import android.content.ContextWrapper
import okhttp3.OkHttpClient
import okhttp3.Request
import quanti.com.kotlinlog.Log
import java.io.File
import java.io.FileOutputStream

class DownloadManager(private val context: Context, private val client: OkHttpClient) {

    suspend fun downloadFile(
        url: String
    ): String {
        val request = Request.Builder().url(url).build()

        val call = client.newCall(request).execute()
        val body = call.body

        val wrapper = ContextWrapper(context)
        val name = url.substringAfterLast("/")
        val file = File(wrapper.filesDir, name)
        Log.d("File path: ${file.path}")
        if (!file.exists()) {
            file.createNewFile()
        }
        val outputStream = FileOutputStream(file)

        body?.let {
            val bytes = body.bytes()
            with(outputStream) {
                write(bytes)
                close()
            }
        }
        return name
    }
}
