package com.my.data.remote

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MockAlbumsService(private val application: Application) {

    fun getAlbums(): List<WSAlbum> {
        val responseJson = getJson(application)
        val gson = Gson()
        return gson.fromJson(responseJson, object : TypeToken<List<WSAlbum>>() {}.type)
    }

    private fun getJson(context: Context): String {
        val inputStream = context.assets.open("technical-test.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        return String(buffer)
    }

}

