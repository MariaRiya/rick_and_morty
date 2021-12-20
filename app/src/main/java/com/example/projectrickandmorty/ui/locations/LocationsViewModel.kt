package com.example.projectrickandmorty.ui.locations

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectrickandmorty.common.Common
import com.example.projectrickandmorty.interfacee.RetrofitServices
import com.example.projectrickandmorty.models.Episode
import com.example.projectrickandmorty.models.Location
import com.example.projectrickandmorty.models.ResponseEpisodes
import com.example.projectrickandmorty.models.ResponseLocations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationsViewModel : ViewModel() {

    internal var locationsList = MutableLiveData<MutableList<Location>>()
    lateinit var mService: RetrofitServices
    internal var showDialog = MutableLiveData<Boolean>()

    internal var pagesCount = MutableLiveData<Int>(0)

    init{
        mService = Common.Common.retrofitService
        showDialog.value = false
        getAllLocationsList()

    }


    private fun getAllLocationsList() {
        mService.getLocationList().enqueue(object : Callback<ResponseLocations> {
            override fun onFailure(call: Call<ResponseLocations>, t: Throwable) {
                Log.d("TAG", "ERROR = " + t.message.toString())
                showDialog.value = true
// need to add some message about exception
            }

            override fun onResponse(call: Call<ResponseLocations>, response: Response<ResponseLocations>) {
                locationsList.value = response.body()?.results
                pagesCount.value = response.body()?.info?.pages
                Log.d("TAG", "BODY LOCATION = " + response.body())
                showDialog.value = true



            }
        })
    }

    fun load(page : Int){
        mService.getLocationsByPage(page).enqueue(object : Callback<ResponseLocations> {
            override fun onFailure(call: Call<ResponseLocations>, t: Throwable) {
                Log.d("TAG", "ERROR = " + t.message.toString())
                //          showDialog.value = true
// need to add some message about exception
            }

            override fun onResponse(call: Call<ResponseLocations>, response: Response<ResponseLocations>) {
                response.body()?.results?.let { locationsList.value?.addAll(it) }
                locationsList.value = locationsList.value
                Log.d("TAG", "BODY LOADED LOCATION = " + response.body())

                Log.d("TAG", "LIST SIZE= " + locationsList.value?.size)

                //        showDialog.value = true



            }
        })
    }


}