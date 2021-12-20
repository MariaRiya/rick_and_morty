package com.example.projectrickandmorty.interfacee

import com.example.projectrickandmorty.models.ResponseEpisodes
import com.example.projectrickandmorty.ui.ResponseCharacters
//import com.example.projectrickandmorty.ui.characters.Character
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {

    @GET("character")
    fun getCharacterList(): Call<ResponseCharacters>

    @GET("character?")
    fun getCharactersByPage(
        @Query("page") pageIndex: Int
    ): Call<ResponseCharacters>


    @GET("episode")
    fun getEpisodesList(): Call<ResponseEpisodes>

    @GET("episode?")
    fun getEpisodesByPage(
        @Query("page") pageIndex: Int
    ): Call<ResponseEpisodes>

}