package com.example.finalassessment

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.finalassessment.controllers.DishController
import com.example.finalassessment.models.Dish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun DishDetailsScreen(dishId: Int,
                         modifier: Modifier = Modifier
) {

    val dishService = ApiUtility.apiUtilityFun().create(DishController::class.java)
    var dishes:Dish by remember {
        mutableStateOf(Dish(category = "category", id = 0, image = "", name = "Dish Name", price = 100, tag = "", description = ""))
    }

    LaunchedEffect(Unit) {
        val response = withContext(Dispatchers.IO){
            dishService.getDish(dishId)
        }
        if(response.isSuccessful){
            response
                .body()?.let { data ->
                    dishes = data.data
//                    Log.d("Api data", dishes.toString())
                }
        }

    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(painter = rememberAsyncImagePainter(dishes.image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(4.dp)))

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = dishes.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = dishes.description,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = dishes.price.toString(),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}