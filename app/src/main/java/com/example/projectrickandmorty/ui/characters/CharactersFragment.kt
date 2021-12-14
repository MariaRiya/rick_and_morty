package com.example.projectrickandmorty.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.projectrickandmorty.R

class CharactersFragment : Fragment() {

    private lateinit var charactersViewModel: CharactersViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        charactersViewModel =
                ViewModelProviders.of(this).get(CharactersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_characters, container, false)
  //      val textView: TextView = root.findViewById(R.id.text_home)
        charactersViewModel.text.observe(viewLifecycleOwner, Observer {
    //        textView.text = it
        })
        return root
    }
}