package com.my.data.sources

import com.my.data.local.AppDatabase
import com.my.data.local.entity.AlbumEntity
import com.my.data.local.entity.toModel
import com.my.domain.models.Album
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AlbumsLocalDataSource(private val appDatabase: AppDatabase){

    suspend fun addAlbums(albums: List<AlbumEntity>){
        appDatabase.albumsDAO().insert(albums)
    }

    suspend fun addAlbum(album: AlbumEntity){
        appDatabase.albumsDAO().insert(album)
    }

     fun getAlbums(): Flow<List<Album>> {
        return appDatabase.albumsDAO().getAlbums().map { it.toModel() }
    }

    suspend fun removeAll() {
        appDatabase.albumsDAO().deleteAll()
    }

}