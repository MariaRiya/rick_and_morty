package com.example.projectrickandmorty.ui.episodes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectrickandmorty.common.Common
import com.example.projectrickandmorty.interfacee.RetrofitServices
import com.example.projectrickandmorty.models.Episode
import com.example.projectrickandmorty.models.ResponseEpisodes
import com.example.projectrickandmorty.ui.Characters
import com.example.projectrickandmorty.ui.ResponseCharacters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodesViewModel : ViewModel() {

    internal var episodesList = MutableLiveData<MutableList<Episode>>()
    lateinit var mService: RetrofitServices
    internal var showDialog = MutableLiveData<Boolean>()

    internal var pagesCount = MutableLiveData<Int>(0)

    init{
        mService = Common.Common.retrofitService
        showDialog.value = false
        getAllEpisodesList()

    }


    private fun getAllEpisodesList() {
        mService.getEpisodesList().enqueue(object : Callback<ResponseEpisodes> {
            override fun onFailure(call: Call<ResponseEpisodes>, t: Throwable) {
                Log.d("TAG", "ERROR = " + t.message.toString())
                showDialog.value = true
// need to add some message about exception
            }

            override fun onResponse(call: Call<ResponseEpisodes>, response: Response<ResponseEpisodes>) {
                episodesList.value = response.body()?.results
                pagesCount.value = response.body()?.info?.pages
                Log.d("TAG", "BODY EPISODE = " + response.body())
                showDialog.value = true



            }
        })
    }

    fun load(page : Int){
        mService.getEpisodesByPage(page).enqueue(object : Callback<ResponseEpisodes> {
            override fun onFailure(call: Call<ResponseEpisodes>, t: Throwable) {
                Log.d("TAG", "ERROR = " + t.message.toString())
                //          showDialog.value = true
// need to add some message about exception
            }

            override fun onResponse(call: Call<ResponseEpisodes>, response: Response<ResponseEpisodes>) {
                response.body()?.results?.let { episodesList.value?.addAll(it) }
                episodesList.value = episodesList.value
                Log.d("TAG", "BODY LOADED EPISODE = " + response.body())

                Log.d("TAG", "LIST SIZE= " + episodesList.value?.size)

                //        showDialog.value = true



            }
        })
    }


}