package ua.fabolouslumpen.lab3.items

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class AlbumItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val albumName: String,
    val coverResId: Int
): ListItem {
    override fun getType(): Int = ListItem.TYPE_IMAGE
}
