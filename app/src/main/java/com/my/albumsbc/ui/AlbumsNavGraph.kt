package com.my.albumsbc.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.my.albumsbc.presentation.AlbumScreen
import com.my.albumsbc.presentation.AlbumsScreen
import com.my.albumsbc.presentation.AlbumsViewModel
import com.my.albumsbc.ui.MainDestinations.ALBUMS_ROUTE
import com.my.albumsbc.ui.MainDestinations.ALBUM_ROUTE

object MainDestinations {
    const val ALBUMS_ROUTE = "albums"
    const val ALBUM_ROUTE = "album"
}

@Composable
fun AlbumsNavGraph(
    albumsViewModel: AlbumsViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ALBUMS_ROUTE
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ALBUMS_ROUTE) {
            AlbumsScreen(albumsViewModel) {
                albumsViewModel.albumSelected = it
                actions.navigateToAlbum()
            }
        }
        composable(ALBUM_ROUTE) {
            AlbumScreen(albumsViewModel)
        }
    }
}

class MainActions(navController: NavHostController) {
    val navigateToAlbum = {
        navController.navigate(ALBUM_ROUTE)
    }
}


