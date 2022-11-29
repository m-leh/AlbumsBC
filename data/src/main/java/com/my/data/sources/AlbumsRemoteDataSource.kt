package com.my.data.sources

import com.my.data.remote.AlbumsService
import com.my.data.remote.WSAlbum
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class AlbumsRemoteDataSource(
    private val albumsService: AlbumsService
) {

    suspend fun getAlbums(): Resource<List<WSAlbum>> = withContext(IO) {
        try {
            val albums = albumsService.getAlbums()
            Resource.Success(albums)
        } catch (e: HttpException) {
            Resource.Error(errorMessage = e.message ?: "Something went wrong")
        } catch (e: IOException) {
            Resource.Error("Please check your network connection")
        } catch (e: Exception) {
            Resource.Error(errorMessage = "Something went wrong")
        }
    }

}

sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : Resource<T>(data = data)

    class Error<T>(errorMessage: String) : Resource<T>(message = errorMessage)

}