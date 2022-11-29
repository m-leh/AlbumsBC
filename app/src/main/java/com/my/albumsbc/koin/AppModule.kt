package  com.my.albumsbc.koin

import android.app.Application
import com.my.albumsbc.presentation.AlbumsViewModel
import com.my.data.local.AppDatabase
import com.my.data.remote.AlbumsService
import com.my.data.remote.MockAlbumsService
import com.my.data.repositories.AlbumsRepository
import com.my.data.sources.AlbumsLocalDataSource
import com.my.data.sources.AlbumsRemoteDataSource
import com.my.domain.repositories.IAlbumsRepository
import com.my.domain.usecases.AlbumsUseCase
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL: String = "https://static.leboncoin.fr/img/shared/"

private fun provideDataBase(application: Application): AppDatabase {
    return AppDatabase.getInstance(application)
}

fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().build()
}

fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideAlbumsService(retrofit: Retrofit): AlbumsService {
    return retrofit.create(AlbumsService::class.java)
}

val appModule = module {
    single { provideHttpClient() }
    single { provideRetrofit(get()) }
    single { provideDataBase(get()) }
    single { provideAlbumsService(get()) }
    single { AlbumsLocalDataSource(get()) }
    single { AlbumsRemoteDataSource(get()) }
    single { MockAlbumsService(get()) }
    single { AlbumsRepository(get(), get()) as IAlbumsRepository}
    single { AlbumsUseCase(get())}
}

val viewModelModule = module {
    viewModel {
        AlbumsViewModel(get())
    }
}