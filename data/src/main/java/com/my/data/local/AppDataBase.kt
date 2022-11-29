package com.my.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.my.data.local.entity.AlbumEntity

@Database(
    entities = [AlbumEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumsDAO(): AlbumsDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "albumsbc.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}