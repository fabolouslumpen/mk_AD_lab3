import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.fabolouslumpen.lab3.items.AlbumItem
import ua.fabolouslumpen.lab3.items.ListItem
import ua.fabolouslumpen.lab3.items.SongItem
import ua.fabolouslumpen.lab3.R

class MyAdapter(
    private val items: List<ListItem>,
    private val onItemClick: (ListItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_SONG = 0
        private const val TYPE_ALBUM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_SONG -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
                SongViewHolder(view)
            }
            TYPE_ALBUM -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
                AlbumViewHolder(view)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }

        when (item) {
            is SongItem -> {
                val songHolder = holder as SongViewHolder
                songHolder.songNameTextView.text = item.songName
                songHolder.artistTextView.text = item.songArtist
            }
            is AlbumItem -> {
                val albumHolder = holder as AlbumViewHolder
                albumHolder.albumNameTextView.text = item.albumName
                albumHolder.albumImageView.setImageResource(item.coverResId)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is SongItem -> TYPE_SONG
            is AlbumItem -> TYPE_ALBUM
            else -> throw IllegalArgumentException("Unknown item type")
        }
    }

    override fun getItemCount(): Int = items.size

    inner class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val songNameTextView: TextView = view.findViewById(R.id.songTV)
        val artistTextView: TextView = view.findViewById(R.id.artistTV)
    }

    inner class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val albumNameTextView: TextView = view.findViewById(R.id.nameTV)
        val albumImageView: ImageView = view.findViewById(R.id.coverV)
    }
}

