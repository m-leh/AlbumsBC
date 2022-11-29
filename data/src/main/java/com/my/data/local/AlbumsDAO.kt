package com.my.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.my.data.local.entity.AlbumEntity
import kotlinx.coroutines.flow.Flow

/* Albums Database Object */
@Dao
interface AlbumsDAO {

    @Insert(onConflict = REPLACE)
    suspend fun insert(albums:List<AlbumEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insert(event: AlbumEntity): Long

    @Query("SELECT * FROM albums")
    fun getAlbums(): Flow<List<AlbumEntity>>

    @Query("DELETE FROM albums")
    suspend fun deleteAll()

}