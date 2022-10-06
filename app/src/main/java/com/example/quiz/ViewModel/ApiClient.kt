package com.example.api_coroutine.ViewModel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        var baseurl = "https://b4e7d359-c58f-4aa3-a314-726b3baa3852.mock.pstmn.io/"


        fun getretrofit(): Retrofit {

            return Retrofit.Builder().baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

    }
}