package com.example.ralphtiamaweatherapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.ralphtiamaweatherapp.presentation.WeatherViewModel
import com.example.ralphtiamaweatherapp.ui.screens.DashBoardScreen
import com.example.sneakerups.screens.LoginScreen
import com.example.ralphtiamaweatherapp.ui.screens.SignupScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.serialization.Serializable

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel, weatherViewModel: WeatherViewModel){

    val navController = rememberNavController()
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    val isAuth = auth.currentUser == null

    NavHost(navController = navController, startDestination = if(isAuth) UnAuthenticatedRoute else AuthenticatedRoute, builder = {

        navigation<UnAuthenticatedRoute>(startDestination = LoginScreenRoute){
            composable<LoginScreenRoute>{
                LoginScreen(modifier, navController, authViewModel)
            }
            composable<SignupScreenRoute> {
                SignupScreen(modifier, navController, authViewModel)
            }
        }

        navigation<AuthenticatedRoute>(startDestination = DashBoardScreenRoute){
            composable<DashBoardScreenRoute>{
                DashBoardScreen(modifier, navController, authViewModel, weatherViewModel)
            }

        }

    } )


    
}


@Serializable
object AuthenticatedRoute

@Serializable
object CurrentWeatherScreenRoute

@Serializable
object DashBoardScreenRoute

@Serializable
object ListOfWeatherScreenRoute


@Serializable
object UnAuthenticatedRoute

@Serializable
object LoginScreenRoute

@Serializable
object SignupScreenRoute








