package com.example.finalassessment.controllers

import com.example.finalassessment.models.Dish
import com.example.finalassessment.models.DishResponse
import com.example.finalassessment.models.SingleDishResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DishController {

    @GET("api/v1/dishes/")
    suspend fun getDishes(): Response<DishResponse>

    @POST("api/v1/dishes/")
    suspend fun createDish(@Body dish: Dish): Response<SingleDishResponse>

    @GET("api/v1/dishes/{id}")
    suspend fun getDish(@Path("id") id: Int): Response<SingleDishResponse>
}