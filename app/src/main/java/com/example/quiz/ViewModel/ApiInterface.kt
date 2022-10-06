package com.example.api_coroutine.ViewModel

import com.example.quiz.Modeldata
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @GET(" ")
    suspend fun getdata(
        @Query("quiz")s1:String
    ): Response<Modeldata>


}