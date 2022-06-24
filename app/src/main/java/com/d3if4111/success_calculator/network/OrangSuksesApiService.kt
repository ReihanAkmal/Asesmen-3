package com.d3if4111.success_calculator.network

import com.d3if4111.success_calculator.model.OrangSukses
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface OrangSuksesApiService {

    @GET("orang-sukses.json")
    fun getOrangSukses() : Call<List<OrangSukses>>

    companion object {

        var BASE_URL = "https://raw.githubusercontent.com/ReihanAkmal/Assessmen1-WeightConverter/main/"

        fun create() : OrangSuksesApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(OrangSuksesApiService::class.java)

        }
    }
}