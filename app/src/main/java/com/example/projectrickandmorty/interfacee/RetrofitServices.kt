package com.example.projectrickandmorty.interfacee

import com.example.projectrickandmorty.ui.Responsee
//import com.example.projectrickandmorty.ui.characters.Character
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitServices {

    @GET("character")
    fun getCharacterList(): Call<Responsee>

    @GET("character?")
    fun getCharactersByPage(
        @Query("page") pageIndex: Int
    ): Call<Responsee>

}