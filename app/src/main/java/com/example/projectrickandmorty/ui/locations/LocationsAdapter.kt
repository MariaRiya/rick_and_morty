package com.example.projectrickandmorty.ui.locations

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projectrickandmorty.R
import com.example.projectrickandmorty.models.Location
import kotlinx.android.synthetic.main.item_layout_location.view.*

class LocationsAdapter (private val context: Context, private val locationsList: MutableList<Location>): RecyclerView.Adapter<LocationsAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txt_name: TextView = itemView.textView_name_value
        val txt_type: TextView = itemView.textView_type_value
        val txt_dimension: TextView = itemView.textView_dimension_value


        fun bind(listItem: Location) {
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
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_location, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = locationsList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = locationsList[position]
        holder.bind(listItem)

        holder.txt_name.text = locationsList[position].name
        holder.txt_type.text = locationsList[position].type
        holder.txt_dimension.text = locationsList[position].dimension

    }

    fun addItems(list: MutableList<Location>) {
        if (locationsList.size == 0) {
            locationsList.addAll(list)
        } else {
            for (i in locationsList.size..list.size - 1) {
                locationsList.add(list[i])
            }
        }

        Log.d("TAG", "adapter size = " + locationsList.size)
    }
}