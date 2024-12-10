package ua.fabolouslumpen.lab3

import MyAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ua.fabolouslumpen.lab3.db.DatabaseProvider
import ua.fabolouslumpen.lab3.db.JsonParser
import ua.fabolouslumpen.lab3.items.AlbumItem
import ua.fabolouslumpen.lab3.items.ListItem
import ua.fabolouslumpen.lab3.items.SongItem
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private val items = mutableListOf<ListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DatabaseProvider.initialize(applicationContext)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = MyAdapter(items) { item ->
            lifecycleScope.launch {
                when (item) {
                    is SongItem -> DatabaseProvider.database.songDao().deleteSong(item)
                    is AlbumItem -> DatabaseProvider.database.albumDao().deleteAlbum(item)
                }
                loadData()
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val loadButton: Button = findViewById(R.id.dataBT)
        loadButton.setOnClickListener {
            lifecycleScope.launch {
                JsonParser(this@MainActivity).parseAndSaveData()
                loadData()
            }
        }

        val loadRandomSongButton: Button = findViewById(R.id.songBT)
        loadRandomSongButton.setOnClickListener {
            lifecycleScope.launch {
                loadRandomSong()
            }
        }

        val loadRandomAlbumButton: Button = findViewById(R.id.albumBT)
        loadRandomAlbumButton.setOnClickListener {
            lifecycleScope.launch {
                loadRandomAlbum()
            }
        }

        lifecycleScope.launch {
            loadData()
        }
    }

    private suspend fun loadData() {
        items.clear()

        val songs = DatabaseProvider.database.songDao().getAllSongs()
        items.addAll(songs)

        val albums = DatabaseProvider.database.albumDao().getAllAlbums()
        items.addAll(albums)

        adapter.notifyDataSetChanged()
    }

    private suspend fun loadRandomSong() {
        val songs = DatabaseProvider.database.songDao().getAllSongs()
        if (songs.isNotEmpty()) {
            val randomSong = songs[Random.nextInt(songs.size)]
            items.clear()
            items.add(randomSong)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Random Song: ${randomSong.songName}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No songs available", Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun loadRandomAlbum() {
        val albums = DatabaseProvider.database.albumDao().getAllAlbums()
        if (albums.isNotEmpty()) {
            val randomAlbum = albums[Random.nextInt(albums.size)]
            items.clear()
            items.add(randomAlbum)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Random Album: ${randomAlbum.albumName}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No albums available", Toast.LENGTH_SHORT).show()
        }
    }
}
