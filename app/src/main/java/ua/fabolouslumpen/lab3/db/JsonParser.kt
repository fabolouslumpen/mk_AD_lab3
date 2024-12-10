package ua.fabolouslumpen.lab3.db

import android.annotation.SuppressLint
import android.content.Context
import org.json.JSONArray
import ua.fabolouslumpen.lab3.R
import ua.fabolouslumpen.lab3.items.AlbumItem
import ua.fabolouslumpen.lab3.items.SongItem

class JsonParser(private val context: Context) {

    @SuppressLint("DiscouragedApi")
    suspend fun parseAndSaveData() {
        val jsonString = context.resources.openRawResource(R.raw.items).bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonString)

        val songDao = DatabaseProvider.database.songDao()
        val albumDao = DatabaseProvider.database.albumDao()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val type = jsonObject.getString("type")
            val name = jsonObject.getString("name")
            when (type) {
                "song" -> {
                    val artist = jsonObject.optString("artist", "")
                    songDao.insertSong(SongItem(songName = name, songArtist = artist))
                }
                "album" -> {
                    val cover = jsonObject.getString("cover")
                    val coverResId = context.resources.getIdentifier(cover, "drawable", context.packageName)
                    albumDao.insertAlbum(AlbumItem(albumName = name, coverResId = coverResId))
                }
            }
        }
    }
}
