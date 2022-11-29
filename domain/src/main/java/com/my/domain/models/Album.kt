package com.my.domain.models

data class Album(
    val id: Long,
    var name: String,
    var thumbnail: String?,
    var picture: String?
)