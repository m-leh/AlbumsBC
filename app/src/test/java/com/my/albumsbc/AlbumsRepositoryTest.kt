package com.my.albumsbc

import com.my.data.local.entity.toModel
import com.my.data.remote.WSAlbum
import com.my.data.remote.toEntity
import com.my.data.repositories.AlbumsRepository
import com.my.data.sources.AlbumsLocalDataSource
import com.my.data.sources.AlbumsRemoteDataSource
import com.my.data.sources.Resource
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumsRepositoryTest {

    @MockK
    lateinit var albumsRemoteDataSource: AlbumsRemoteDataSource

    @MockK
    lateinit var albumsLocalDataSource: AlbumsLocalDataSource

    private lateinit var albumsRepository: AlbumsRepository

    private lateinit var albums: List<WSAlbum>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        albumsRepository = AlbumsRepository(albumsRemoteDataSource, albumsLocalDataSource)
        albums = MockAlbumsService().getAlbums()
    }

    @Test
    fun testGetAlbums() = runTest {
        every { albumsLocalDataSource.getAlbums() }
            .returns(flow { emit(albums.toEntity().toModel()) })
        albumsRepository.getAlbums()
        verify { albumsLocalDataSource.getAlbums() }
        launch {
            assertTrue(albumsRepository.getAlbums().first().size == 5000)
        }
    }

    @Test
    fun testLoadAlbums() = runTest {
        coEvery { albumsRemoteDataSource.getAlbums() }.returns(Resource.Success(albums))
        coEvery { albumsLocalDataSource.removeAll() }.answers {}
        coEvery { albumsLocalDataSource.addAlbum(any()) }.answers {}
        launch {
            albumsRepository.loadAlbums()
            coVerify { albumsLocalDataSource.removeAll() }
	    albums.forEach { album ->
            	coVerify { albumsLocalDataSource.addAlbum(album.toEntity()) }
	    }
        }
    }

}