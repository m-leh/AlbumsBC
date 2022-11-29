package com.my.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.my.domain.models.Album

@Entity(tableName = "albums")
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "thumbnail")
    var thumbnail: String?,

    @ColumnInfo(name = "picture")
    var picture: String?
)

fun AlbumEntity.toModel(): Album {
    return Album(id, name, thumbnail, picture)
}

fun List<AlbumEntity>.toModel(): List<Album> {
    return map { entity ->
        entity.toModel()
    }
}

