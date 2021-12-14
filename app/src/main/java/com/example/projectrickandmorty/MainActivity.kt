package com.example.projectrickandmorty

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectrickandmorty.common.Common
import com.example.projectrickandmorty.interfacee.RetrofitServices
import com.example.projectrickandmorty.ui.Characters
import com.example.projectrickandmorty.ui.Responsee
import com.example.projectrickandmorty.ui.characters.CharactersAdapter
//import com.example.projectrickandmorty.ui.characters.Character

import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_characters.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: CharactersAdapter
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        mService = Common.Common.retrofitService
        recyclerCharactersList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerCharactersList.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        getAllCharactersList()

    }


    private fun getAllCharactersList() {
        dialog.show()
        mService.getCharacterList().enqueue(object : Callback<Responsee> {
            override fun onFailure(call: Call<Responsee>, t: Throwable) {
                Log.d("TAG", "ERROR = " + t.message.toString())

                dialog.dismiss()

            }

            override fun onResponse(call: Call<Responsee>, response: Response<Responsee>) {
                adapter = CharactersAdapter(baseContext, response.body()?.results as MutableList<Characters>)
                adapter.notifyDataSetChanged()
                recyclerCharactersList.adapter = adapter

                Log.d("TAG", "BODY = " + response.body())
//                var obj = response.body()



                dialog.dismiss()
            }
        })
    }

}