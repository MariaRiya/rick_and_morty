package com.example.projectrickandmorty.ui.characters

import android.util.Log
import android.view.View
import androidx.appcompat.view.menu.MenuView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectrickandmorty.ui.Characters
import com.example.projectrickandmorty.R.layout.item_layout_character
import com.example.projectrickandmorty.common.Common
import com.example.projectrickandmorty.interfacee.RetrofitServices
import com.example.projectrickandmorty.ui.ResponseCharacters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailViewModel : ViewModel() {

    var character = MutableLiveData<Characters>()
    var mService = Common.Common.retrofitService

    fun start(id : Int){
        mService.getCharacterById(id).enqueue(object : Callback<Characters> {
            override fun onFailure(call: Call<Characters>, t: Throwable) {
                Log.d("TAG", "ERROR = " + t.message.toString())
// need to add some message about exception
            }

            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                character.value = response.body()
                Log.d("TAG", "BODY = " + response.body())



            }
        })
    }
}