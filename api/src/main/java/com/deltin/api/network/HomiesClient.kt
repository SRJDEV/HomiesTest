package com.deltin.api.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomiesClient {


    companion object{
        val baseUrl = "https://pixabay.com/"
        private val retrofit: Retrofit by lazy {


            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api:HomiesAPI by lazy {
            retrofit.create(HomiesAPI ::class.java)
        }
    }
}