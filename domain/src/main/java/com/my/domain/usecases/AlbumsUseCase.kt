package com.my.domain.usecases

import com.my.domain.models.Album
import com.my.domain.repositories.IAlbumsRepository
import kotlinx.coroutines.flow.Flow

class AlbumsUseCase(private val repository: IAlbumsRepository) {

    suspend fun loadAlbums(){
        repository.loadAlbums()
    }

    fun getAlbums(): Flow<List<Album>> {
        return repository.getAlbums()
    }
}