package com.example.projectrickandmorty.ui.characters
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projectrickandmorty.ui.Characters
import com.example.projectrickandmorty.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout_character.view.*

class CharactersAdapter(private val context: Context, private val charactersList: MutableList<Characters>):RecyclerView.Adapter<CharactersAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.imageView_character
        val txt_name: TextView = itemView.textView_name_value
        val txt_species: TextView = itemView.textView_species_value
        val txt_status: TextView = itemView.textView_status_value
        val txt_gender: TextView = itemView.textView_gender_value


        fun bind(listItem: Characters) {
            itemView.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${itemView.textView_name_value.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_character, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = charactersList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = charactersList[position]
        holder.bind(listItem)

        Picasso.get().load(charactersList[position].image).into(holder.image)
        holder.txt_name.text = charactersList[position].name
        holder.txt_species.text = charactersList[position].species
        holder.txt_status.text = charactersList[position].status
        holder.txt_gender.text = charactersList[position].gender

    }

    fun addItems(list : MutableList<Characters>){
        if(charactersList.size == 0){
            charactersList.addAll(list)
        }else {
            for(i in charactersList.size..list.size-1){
                charactersList.add(list[i])
            }
        }

        Log.d("TAG" , "adapter size = " + charactersList.size)
    }

}
