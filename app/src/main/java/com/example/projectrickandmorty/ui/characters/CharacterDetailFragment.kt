package com.example.projectrickandmorty.ui.characters

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.projectrickandmorty.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.character_detail_fragment.*

class CharacterDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterDetailFragment()
    }

    private lateinit var viewModel: CharacterDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.character_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CharacterDetailViewModel::class.java)

        arguments?.getInt("id")?.let { viewModel.start(it)
            Log.d("TAG", "ID = " + it )
        }

        viewModel.character.observe(viewLifecycleOwner, Observer {

            Picasso.get().load(viewModel.character.value?.image).into(imageView_character)

            textView_id_value.text = viewModel.character.value?.id
            textView_name_value.text = viewModel.character.value?.name
            textView_species_value.text = viewModel.character.value?.species
            textView_status_value.text = viewModel.character.value?.status
            textView_gender_value.text = viewModel.character.value?.gender
            textView_type_value.text = viewModel.character.value?.type
            textView_origin_value.text = viewModel.character.value?.origin?.name
            textView_location_value.text = viewModel.character.value?.location?.name
            textView_url_value.text = viewModel.character.value?.url
            textView_created_value.text = viewModel.character.value?.created


            var list = mutableListOf<String>()
            list.addAll(viewModel.character.value?.episode as MutableList<String>)
            for(elem in list){
                textView_episode_value.text = textView_episode_value.text.toString() + elem + "\n"
            }


        })


    }





}