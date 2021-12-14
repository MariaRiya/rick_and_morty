package com.example.projectrickandmorty.common

import com.example.projectrickandmorty.interfacee.RetrofitServices
import com.example.projectrickandmorty.retrofit.RetrofitClient

object Common {



    object Common {
        private val BASE_URL = "https://rickandmortyapi.com/api/"
        val retrofitService: RetrofitServices
            get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
    }
}