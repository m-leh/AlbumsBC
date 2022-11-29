package com.my.albumsbc

import com.my.albumsbc.presentation.AlbumsViewModel
import com.my.data.local.entity.toModel
import com.my.data.remote.WSAlbum
import com.my.data.remote.toEntity
import com.my.domain.usecases.AlbumsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AlbumsViewModelTest {

    @MockK
    lateinit var albumsUseCase: AlbumsUseCase

    private lateinit var viewModel: AlbumsViewModel

    private lateinit var albums: List<WSAlbum>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        albums = MockAlbumsService().getAlbums()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testViewModel() = runTest {
        every { albumsUseCase.getAlbums() }.returns(flow { emit(albums.toEntity().toModel()) })
        coEvery { albumsUseCase.loadAlbums() }.answers {}
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            viewModel = AlbumsViewModel(albumsUseCase)
            assertTrue(viewModel.albums.value.size == 5000)
        } finally {
            Dispatchers.resetMain()
        }
    }

}