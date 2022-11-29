package com.my.domain.repositories

import com.my.domain.models.Album
import kotlinx.coroutines.flow.Flow

interface IAlbumsRepository {

    fun getAlbums(): Flow<List<Album>>

    suspend fun loadAlbums()
}