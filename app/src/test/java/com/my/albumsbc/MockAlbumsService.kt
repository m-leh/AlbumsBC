package com.my.albumsbc

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.my.data.remote.WSAlbum

class MockAlbumsService {

    fun getJson(): String {
        val inputStream = this.javaClass.classLoader?.getResourceAsStream("technical-test.json")!!
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        return String(buffer)
    }

    fun getAlbums(): List<WSAlbum> {
        val responseJson = getJson()
        val gson = Gson()
        return gson.fromJson(responseJson, object : TypeToken<List<WSAlbum>>() {}.type)
    }

}

