package com.my.albumsbc

import android.app.Application
import com.my.albumsbc.koin.appModule
import com.my.albumsbc.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AlbumsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AlbumsApp)
            modules(
                appModule,
                viewModelModule,
            )
        }
    }
}