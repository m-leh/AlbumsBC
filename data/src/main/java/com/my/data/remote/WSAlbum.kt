package com.my.data.remote

import com.my.data.local.entity.AlbumEntity


data class WSAlbum(
    val albumId: Long,
    val id: Long,
    val title: String,
    val url: String?,
    val thumbnailUrl: String?
)

fun WSAlbum.toEntity(): AlbumEntity {
    return AlbumEntity(id, title, thumbnailUrl, url)
}

fun List<WSAlbum>.toEntity(): List<AlbumEntity> {
    return map { it.toEntity() }
}
