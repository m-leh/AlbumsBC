package com.my.albumsbc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.my.albumsbc.presentation.AlbumsViewModel
import com.my.albumsbc.ui.AlbumsNavGraph
import com.my.albumsbc.ui.theme.AlbumsBCTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val albumsViewModel: AlbumsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlbumsBCTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AlbumsNavGraph(
                        albumsViewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}