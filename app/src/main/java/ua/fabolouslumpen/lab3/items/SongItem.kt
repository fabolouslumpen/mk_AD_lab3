package ua.fabolouslumpen.lab3.items

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val songName: String,
    val songArtist: String
):ListItem{
    override fun getType(): Int = ListItem.TYPE_TEXT
}
