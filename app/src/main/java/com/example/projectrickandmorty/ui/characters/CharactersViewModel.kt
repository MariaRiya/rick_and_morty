package com.example.projectrickandmorty.ui.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectrickandmorty.common.Common
import com.example.projectrickandmorty.interfacee.RetrofitServices
import com.example.projectrickandmorty.ui.Characters
import com.example.projectrickandmorty.ui.Responsee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersViewModel : ViewModel() {

    internal var charactersList = MutableLiveData<MutableList<Characters>>()
    lateinit var mService: RetrofitServices
    internal var showDialog = MutableLiveData<Boolean>()


    init{
        mService = Common.Common.retrofitService
        showDialog.value = false
        getAllCharactersList()

    }


    private fun getAllCharactersList() {
        mService.getCharacterList().enqueue(object : Callback<Responsee> {
            override fun onFailure(call: Call<Responsee>, t: Throwable) {
                Log.d("TAG", "ERROR = " + t.message.toString())
                showDialog.value = true
// need to add some message about exception
            }

            override fun onResponse(call: Call<Responsee>, response: Response<Responsee>) {
                charactersList.value = response.body()?.results
               Log.d("TAG", "BODY = " + response.body())
                showDialog.value = true



            }
        })
    }

    }