package com.my.data.remote

import retrofit2.http.GET

interface AlbumsService {

    @GET("technical-test.json")
    suspend fun getAlbums(): List<WSAlbum>

}