package com.my.albumsbc

import com.my.albumsbc.koin.BASE_URL
import com.my.data.remote.AlbumsService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * https://static.leboncoin.fr/img/shared/technical-test.json
 */
class MockRemoteServerTest {

    private var server = MockWebServer()
    private lateinit var service: AlbumsService
    private lateinit var responseJson: String

    @Before
    fun setup() {
        server.start()
        val retrofit = Retrofit.Builder()
            .baseUrl(server.url(BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(AlbumsService::class.java)
        responseJson = MockAlbumsService().getJson()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testAlbums() = runTest {
        val response = MockResponse()
            .setBody(responseJson)
        server.enqueue(response)
        val albums = service.getAlbums()
        Assert.assertNotNull(albums.size)
        Assert.assertTrue(albums.size == 5000)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}