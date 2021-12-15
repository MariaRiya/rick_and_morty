package com.example.projectrickandmorty.ui.characters

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectrickandmorty.R
import com.example.projectrickandmorty.common.Common
import com.example.projectrickandmorty.interfacee.RetrofitServices
import com.example.projectrickandmorty.ui.Characters
import com.example.projectrickandmorty.ui.Responsee
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_characters.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class CharactersFragment : Fragment() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: CharactersAdapter
    lateinit var dialog: AlertDialog
    private lateinit var charactersViewModel: CharactersViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        dialog = SpotsDialog.Builder().setCancelable(true).setContext(context).build()
        dialog.show()


        charactersViewModel =
                ViewModelProviders.of(this).get(CharactersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_characters, container, false)
        charactersViewModel.charactersList.observe(viewLifecycleOwner, Observer {

            adapter = CharactersAdapter(requireActivity().baseContext, it)
            adapter.notifyDataSetChanged()
            recyclerCharactersList.adapter = adapter

     //           Log.d("TAG", "BODY = " + response.body())



                dialog.dismiss()
        })


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerCharactersList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        recyclerCharactersList.layoutManager = layoutManager


    }


//    private fun getAllCharactersList() {
//        dialog.show()
//        mService.getCharacterList().enqueue(object : Callback<Responsee> {
//            override fun onFailure(call: Call<Responsee>, t: Throwable) {
//                Log.d("TAG", "ERROR = " + t.message.toString())
//
//                dialog.dismiss()
//
//            }
//
//            override fun onResponse(call: Call<Responsee>, response: Response<Responsee>) {
//                adapter = CharactersAdapter(activity!!.baseContext, response.body()?.results as MutableList<Characters>)
//                adapter.notifyDataSetChanged()
//                recyclerCharactersList.adapter = adapter
//
//                Log.d("TAG", "BODY = " + response.body())
////                var obj = response.body()
//
//
//
//                dialog.dismiss()
//            }
//        })
//    }


}