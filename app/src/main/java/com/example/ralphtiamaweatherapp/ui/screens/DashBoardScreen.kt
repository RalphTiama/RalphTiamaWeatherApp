package com.example.ralphtiamaweatherapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.ralphtiamaweatherapp.AuthViewModel
import com.example.ralphtiamaweatherapp.presentation.WeatherViewModel

@Composable
fun DashBoardScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel,
    weatherViewModel: WeatherViewModel,
){

    val navItemList = listOf(
        NavItem("Today", Icons.Default.Home),
        NavItem("Weather", Icons.Default.DateRange),
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem -> 
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = { 
                            Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }) { innerPadding ->
        ContentScreen(modifier = modifier.padding(innerPadding), navController = navController, authViewModel = authViewModel, weatherViewModel,  selectedIndex)
    }

}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, navController: NavHostController, authViewModel: AuthViewModel, weatherViewModel: WeatherViewModel, selectedIndex : Int){
    when(selectedIndex){
        0 -> CurrentWeatherScreen(navController = navController, authViewModel = authViewModel, weatherViewModel = weatherViewModel)
        1 -> ListOfWeatherScreen(modifier,navController = navController, authViewModel = authViewModel, weatherViewModel = weatherViewModel)
    }
}

data class NavItem(
    val label : String,
    val icon : ImageVector
)