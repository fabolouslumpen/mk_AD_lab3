package ua.fabolouslumpen.lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import ua.fabolouslumpen.lab3.items.AlbumItem
import ua.fabolouslumpen.lab3.items.ListItem
import ua.fabolouslumpen.lab3.items.SongItem


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = localData()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = MyAdapter(items)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun localData(): List<ListItem> {
        val jsonString = resources.openRawResource(R.raw.items).bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonString)
        val items = mutableListOf<ListItem>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val type = jsonObject.getString("type")
            val name = jsonObject.getString("name")
            when (type) {
                "song" -> {
                    val artist = jsonObject.optString("artist", "")
                    items.add(SongItem(name, artist))
                }
                "album" -> {
                    val cover = jsonObject.getString("cover")
                    val coverResId = resources.getIdentifier(cover, "drawable", packageName)
                    items.add(AlbumItem(name, coverResId))
                }
            }
        }
        return items
    }
//    val items: List<ListItem> = listOf(
//        SongItem("Jet Pilot", "System Of A Down"),
//        AlbumItem("123", R.drawable.ic_launcher_background),
//        SongItem("Office-37", "XARAKTER"),
//        SongItem("Jump In The Fire", "Metallica"),
//        AlbumItem("321", R.drawable.ic_launcher_foreground)
//    )
}