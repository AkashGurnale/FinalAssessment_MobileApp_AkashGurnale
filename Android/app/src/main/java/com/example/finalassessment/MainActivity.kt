package com.example.finalassessment

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalassessment.ui.theme.FinalAssessmentTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App(){

    var navController = rememberNavController()
    NavHost(navController = navController, startDestination = "dishesList") {

        composable(route = "dishesList"){
            DishesList(navController = navController)
        }

//        composable(route = "createForm/{name}",
//            arguments = listOf(
//                navArgument("name"){type= NavType.StringType})
//        ){
//            var name = it.arguments?.getString("name")
//
//            name?.let {
//                CreateFormScreen(name = name)
//            }
//        }


        composable(route = "dishDetailScreen/{id}",
            arguments = listOf(
                navArgument("id"){ type = NavType.IntType}
            )
        ){
            var id = it.arguments?.getInt("id")
            id?.let {
                DishDetailsScreen(dishId = id)
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FinalAssessmentTheme {
        Greeting("Android")
    }
}