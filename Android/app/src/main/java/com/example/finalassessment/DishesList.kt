package com.example.finalassessment

import DishCard
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.finalassessment.controllers.DishController
import com.example.finalassessment.models.Dish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DishesList(navController: NavController){

    val dishService = ApiUtility.apiUtilityFun().create(DishController::class.java)

    var isVeg = false

    var originalDishesList: List<Dish> by remember {
        mutableStateOf(listOf())
    }

    var dishes:List<Dish> by remember {
        mutableStateOf(listOf())
    }

    LaunchedEffect(Unit) {
        val response = withContext(Dispatchers.IO){
            dishService.getDishes()
        }
        if(response.isSuccessful){
            response
                .body()?.let { data ->
                    dishes = data.data
                    originalDishesList = data.data
                    Log.d("Api data", dishes.toString())
                }
        }

    }

    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)

    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Dishes", style = MaterialTheme.typography.headlineMedium)
                Button(onClick = {

                    if(isVeg == false){
                        dishes = dishes.filter { dish ->
                            dish.tag == "veg"
                        }
                        isVeg = true
                    } else {
                        isVeg = false
                        dishes = originalDishesList
                    }

 }) {
                    if(isVeg) {
                        Text(text = "Veg", style = MaterialTheme.typography.headlineMedium)
                    } else {
                        Text(text = "All", style = MaterialTheme.typography.headlineMedium)
                    }
                }
            }
        }
        items(dishes) {dish ->
            DishCard(name = dish.name, category = dish.category, imageurl = dish.image, callback = {
                navController.navigate("dishDetailScreen/${dish.id}")
            })

        }
    }
}