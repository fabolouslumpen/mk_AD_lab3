package ua.fabolouslumpen.lab3.items

data class AlbumItem(
    val albumName: String,
    val coverResId: Int
): ListItem {
    override fun getType(): Int = ListItem.TYPE_IMAGE
}
