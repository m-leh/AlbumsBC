package com.my.data.repositories

import com.my.data.remote.toEntity
import com.my.data.sources.AlbumsLocalDataSource
import com.my.data.sources.AlbumsRemoteDataSource
import com.my.data.sources.Resource
import com.my.domain.models.Album
import com.my.domain.repositories.IAlbumsRepository
import kotlinx.coroutines.flow.Flow

class AlbumsRepository(
    private val albumsRemoteDataSource: AlbumsRemoteDataSource,
    private val albumsLocalDataSource: AlbumsLocalDataSource
) : IAlbumsRepository {

    override suspend fun loadAlbums() {
        when (val remoteAlbums = albumsRemoteDataSource.getAlbums()) {
            is Resource.Success -> {
                remoteAlbums.data?.let { it ->
                    albumsLocalDataSource.removeAll()
                    albumsLocalDataSource.addAlbums(it.toEntity())
                }
            }
            is Resource.Error -> {
                println("AlbumsRepository error synchronize ${remoteAlbums.message}")
            }
        }
    }

    override fun getAlbums(): Flow<List<Album>> {
        return albumsLocalDataSource.getAlbums()
    }

}