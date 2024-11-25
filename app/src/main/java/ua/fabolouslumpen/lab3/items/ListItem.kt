package ua.fabolouslumpen.lab3.items

interface ListItem {
    fun getType(): Int

    companion object{
        const val TYPE_TEXT = 0
        const val  TYPE_IMAGE = 1
    }
}