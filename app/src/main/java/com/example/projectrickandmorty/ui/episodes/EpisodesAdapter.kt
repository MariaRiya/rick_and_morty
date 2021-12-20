package com.example.projectrickandmorty.ui.episodes

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projectrickandmorty.R
import com.example.projectrickandmorty.models.Episode
import kotlinx.android.synthetic.main.item_layout_character.view.textView_name_value
import kotlinx.android.synthetic.main.item_layout_episode.view.*

class EpisodesAdapter(private val context: Context, private val episodesList: MutableList<Episode>): RecyclerView.Adapter<EpisodesAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txt_name: TextView = itemView.textView_name_value
        val txt_episode: TextView = itemView.textView_episode_value
        val txt_air_date: TextView = itemView.textView_air_date_value


        fun bind(listItem: Episode) {
            itemView.setOnClickListener {
                Toast.makeText(
                    it.context,
                    "нажал на ${itemView.textView_name_value.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_episode, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = episodesList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = episodesList[position]
        holder.bind(listItem)

        holder.txt_name.text = episodesList[position].name
        holder.txt_episode.text = episodesList[position].episode
        holder.txt_air_date.text = episodesList[position].air_date

    }

    fun addItems(list: MutableList<Episode>) {
        if (episodesList.size == 0) {
            episodesList.addAll(list)
        } else {
            for (i in episodesList.size..list.size - 1) {
                episodesList.add(list[i])
            }
        }

        Log.d("TAG", "adapter size = " + episodesList.size)
    }
}