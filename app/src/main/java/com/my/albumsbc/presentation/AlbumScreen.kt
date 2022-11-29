package com.my.albumsbc.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.my.domain.models.Album

@Composable
fun AlbumScreen(viewModel: AlbumsViewModel) {
    AlbumContent(viewModel.albumSelected)
}

@Composable
fun AlbumContent(
    album: Album?,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        LazyColumn(
            modifier = modifier.padding(horizontal = 10.dp)
        ) {
            item {
                Spacer(Modifier.height(10.dp))
                AsyncImage(
                    model = album?.picture,
                    modifier = Modifier
                        .heightIn(min = 180.dp)
                        .fillMaxWidth(),
                    contentDescription = "album image"
                )
            }
            item {
                Text(album?.name ?: "no name", style = MaterialTheme.typography.h4)
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

