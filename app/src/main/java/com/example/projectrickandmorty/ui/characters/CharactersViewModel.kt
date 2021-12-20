package com.example.projectrickandmorty.ui.characters

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectrickandmorty.common.Common
import com.example.projectrickandmorty.interfacee.RetrofitServices
import com.example.projectrickandmorty.ui.Characters
import com.example.projectrickandmorty.ui.ResponseCharacters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersViewModel : ViewModel() {

    internal var charactersList = MutableLiveData<MutableList<Characters>>()
    lateinit var mService: RetrofitServices
    internal var showDialog = MutableLiveData<Boolean>()

    internal var pagesCount = MutableLiveData<Int>(0)


    init{
        mService = Common.Common.retrofitService
        showDialog.value = false
        getAllCharactersList()

    }


    private fun getAllCharactersList() {
        mService.getCharacterList().enqueue(object : Callback<ResponseCharacters> {
            override fun onFailure(call: Call<ResponseCharacters>, t: Throwable) {
                Log.d("TAG", "ERROR = " + t.message.toString())
                showDialog.value = true
// need to add some message about exception
            }

            override fun onResponse(call: Call<ResponseCharacters>, response: Response<ResponseCharacters>) {
                charactersList.value = response.body()?.results
                pagesCount.value = response.body()?.info?.pages
               Log.d("TAG", "BODY = " + response.body())
                showDialog.value = true



            }
        })
    }

    fun load(page : Int){
        mService.getCharactersByPage(page).enqueue(object : Callback<ResponseCharacters> {
            override fun onFailure(call: Call<ResponseCharacters>, t: Throwable) {
                Log.d("TAG", "ERROR = " + t.message.toString())
      //          showDialog.value = true
// need to add some message about exception
            }

            override fun onResponse(call: Call<ResponseCharacters>, response: Response<ResponseCharacters>) {
                response.body()?.results?.let { charactersList.value?.addAll(it) }
                charactersList.value = charactersList.value
                Log.d("TAG", "BODY LOADED= " + response.body())

                Log.d("TAG", "LIST SIZE= " + charactersList.value?.size)

                //        showDialog.value = true



            }
        })
    }

    }