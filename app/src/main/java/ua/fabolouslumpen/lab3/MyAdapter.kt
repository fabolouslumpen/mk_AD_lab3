package ua.fabolouslumpen.lab3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.fabolouslumpen.lab3.items.AlbumItem
import ua.fabolouslumpen.lab3.items.ListItem
import ua.fabolouslumpen.lab3.items.SongItem

class MyAdapter(private val items: List<ListItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class SongViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val songTV: TextView = itemView.findViewById(R.id.songTV)
        val artistTV: TextView = itemView.findViewById(R.id.artistTV)
    }

    class AlvumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById(R.id.nameTV)
        val coverV: ImageView = itemView.findViewById(R.id.coverV)
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            ListItem.TYPE_TEXT -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
                SongViewHolder(view)
            }
            ListItem.TYPE_IMAGE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
                AlvumViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is SongViewHolder -> {
                val song = item as SongItem
                holder.songTV.text = song.songName
                holder.artistTV.text = song.songArtist
            }
            is AlvumViewHolder -> {
                val album = item as AlbumItem
                holder.nameTV.text = album.albumName
//                album.coverResId?.let { holder.coverV.setImageResource(it) }
                holder.coverV.setImageResource(album.coverResId)
            }
        }

    }

    override fun getItemCount(): Int {return items.size}

}