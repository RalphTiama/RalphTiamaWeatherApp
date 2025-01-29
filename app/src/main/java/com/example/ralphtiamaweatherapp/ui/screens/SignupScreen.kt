package com.example.ralphtiamaweatherapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ralphtiamaweatherapp.AuthState
import com.example.ralphtiamaweatherapp.AuthViewModel
import com.example.ralphtiamaweatherapp.AuthenticatedRoute
import com.example.ralphtiamaweatherapp.LoginScreenRoute
import com.example.ralphtiamaweatherapp.R
import com.example.ralphtiamaweatherapp.UnAuthenticatedRoute
import com.example.ralphtiamaweatherapp.ui.textfields.CustomTextField
import com.example.ralphtiamaweatherapp.ui.theme.Color2
import com.example.ralphtiamaweatherapp.ui.theme.Color3
import com.example.ralphtiamaweatherapp.ui.theme.Color7
import com.example.ralphtiamaweatherapp.ui.theme.Color9


@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate(AuthenticatedRoute){popUpTo<UnAuthenticatedRoute>{inclusive = true}}
            is AuthState.Error -> Toast.makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_LONG).show()
            else -> Unit
        }
    }

        Box(modifier = Modifier
            .imePadding()
            .fillMaxSize()
            .background(Color2)) {

            Image(
                painter = painterResource(id = R.drawable.icon_start), // Your SVG file
                contentDescription = "Weather Background",
                modifier = modifier
                    .height(300.dp)
                    .align(Alignment.TopCenter)
            )

            Image(
                painter = painterResource(id = R.drawable.bottom_details),
                contentDescription = "Weather Background",
                modifier = modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(color = Color3, text = "SignUp", fontSize = 44.sp)
                Text(color = Color9, text = "Weather Metro Manila.", fontSize = 24.sp)

                Spacer(modifier = Modifier.height(16.dp))


                CustomTextField(
                    modifier = modifier.fillMaxSize(),
                    hint = "Email",
                    onTextChange = {
                        email = it
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    modifier = modifier.fillMaxSize(),
                    hint = "Password",
                    onTextChange = {
                        password = it
                    }
                )


                Spacer(modifier = Modifier.height(12.dp))

                TextButton(onClick = {
                    navController.navigate(LoginScreenRoute)
                }) {
                    Text(text = "Already have an account, Login", color = Color7)
                }


                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color3
                    ), onClick = {
                        authViewModel.signup(email, password)
                    },
                    enabled = authState.value != AuthState.Loading
                ) {
                    Text(text = "Signup")
                }


            }


        }








}