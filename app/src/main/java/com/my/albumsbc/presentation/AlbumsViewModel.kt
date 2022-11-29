package com.my.albumsbc.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.domain.models.Album
import com.my.domain.usecases.AlbumsUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumsViewModel(
    private val albumsUseCase: AlbumsUseCase
) : ViewModel() {

    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> = _albums

    var albumSelected: Album? = null

    init {
        viewModelScope.launch(IO) {
            albumsUseCase.loadAlbums()
        }
        viewModelScope.launch {
            albumsUseCase.getAlbums().collect {
                _albums.emit(it)
            }
        }
    }
}
