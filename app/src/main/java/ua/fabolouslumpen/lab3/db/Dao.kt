package ua.fabolouslumpen.lab3.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ua.fabolouslumpen.lab3.items.AlbumItem
import ua.fabolouslumpen.lab3.items.SongItem

@Dao
interface SongDao {
    @Query("SELECT * FROM songs")
    suspend fun getAllSongs(): List<SongItem>

    @Insert
    suspend fun insertSong(song: SongItem)

    @Delete
    suspend fun deleteSong(song: SongItem)
}

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums")
    suspend fun getAllAlbums(): List<AlbumItem>

    @Insert
    suspend fun insertAlbum(album: AlbumItem)

    @Delete
    suspend fun deleteAlbum(album: AlbumItem)
}
