package ua.fabolouslumpen.lab3.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.fabolouslumpen.lab3.items.SongItem
import ua.fabolouslumpen.lab3.items.AlbumItem

@Database(entities = [SongItem::class, AlbumItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun albumDao(): AlbumDao
}