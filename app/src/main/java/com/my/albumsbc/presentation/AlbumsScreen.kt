package com.my.albumsbc.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.my.domain.models.Album

@Composable
fun AlbumsScreen(
    viewModel: AlbumsViewModel,
    onClickAlbum: (album: Album) -> Unit
) {
    val albumsState by viewModel.albums.collectAsState()
    LazyColumn(Modifier.fillMaxWidth()) {
        items(albumsState) { album ->
            AlbumItem(album = album, onClickAlbum = onClickAlbum)
        }
    }
}


@Composable
fun AlbumItem(
    album: Album,
    onClickAlbum: (album: Album) -> Unit
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clickable { onClickAlbum(album) }
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = album.thumbnail,
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    contentDescription = "album image"
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 5.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = album.name,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}
