package com.example.finalassessment.models


data class DishResponse(
    val message: String,
    val data: List<Dish>,
    val status: String
)

data class SingleDishResponse(
    val message: String,
    val data: Dish,
    val status: String)

data class GeneralResponse(
    val message: String,
    val status: String
)