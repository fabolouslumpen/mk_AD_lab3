package ua.fabolouslumpen.lab3.items

data class SongItem(
    val songName: String,
    val songArtist: String
):ListItem{
    override fun getType(): Int = ListItem.TYPE_TEXT
}
