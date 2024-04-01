package com.example.finalassessment

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiUtility {

    val baseUrl = "http://192.168.154.144:8081/"

    fun apiUtilityFun(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}