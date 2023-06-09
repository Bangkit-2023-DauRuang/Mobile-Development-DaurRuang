package com.capstone.dauruang.ui.screen.welcome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.capstone.dauruang.R
import com.capstone.dauruang.ui.components.button.ButtonLargePrimary
import com.capstone.dauruang.ui.components.button.ButtonLargeSecondary
import com.capstone.dauruang.ui.components.content.ContentSplash
import com.capstone.dauruang.ui.screen.login.LoginScreen
import com.capstone.dauruang.ui.screen.register.RegisterScreen
import com.capstone.dauruang.ui.theme.DauRuangTheme

class WelcomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val backCallback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val currentDestination = navController.currentBackStackEntry?.destination?.route

                    if (currentDestination == "welcome") {
                        onBackPressed()
                    } else {
                        navController.popBackStack("welcome", inclusive = false)
                    }
                }
            }

            onBackPressedDispatcher.addCallback(this, backCallback)

            DauRuangTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var email by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }
                    var username by remember { mutableStateOf("") }
                    var noHp by remember { mutableStateOf("") }

                    NavHost(navController = navController, startDestination = "welcome") {
                        composable("welcome"){
                            WelcomeScreen(
                                navigateLogin = { navController.navigate("login") },
                                navigateRegister = { navController.navigate("register") },
                            )
                        }
                        composable("login") {
                            LoginScreen(
                                email = email ,
                                password = password,
                                onEmailChange = { newValue -> email = newValue },
                                onPassChange = { newValue -> password = newValue},
                                onLoginClick = {},
                                navigateToRegister = {
                                    navController.navigate("register") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onClear = { email = "" }
                            )
                        }
                        composable("register") {
                            RegisterScreen(
                                username = username,
                                email = email,
                                password = password,
                                noHp = noHp,
                                onUsernameChange = { newValue -> username = newValue },
                                onEmailChange = { newValue -> email = newValue },
                                onPassChange = { newValue -> password = newValue },
                                onNoHpChange = { newValue -> noHp = newValue },
                                onRegisterClick = {},
                                onClearEmail = { email = "" },
                                onClearUsername = { username = "" },
                                onClearHp = { noHp = "" },
                                navigateToLogin = {
                                    navController.navigate("login") {
                                        popUpTo("register") { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, WelcomeActivity::class.java)
    }
}

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier
        .fillMaxSize(),
    navigateLogin: () -> Unit,
    navigateRegister: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Image(
            painter = painterResource(R.drawable.background_welcome),
            contentDescription = "background",
            modifier = modifier
                .fillMaxSize(),
            alignment = Alignment.TopCenter
        )
        Image(
            painter = painterResource(R.drawable.whitelogo_dauruang),
            contentDescription = "logo_white_dauruang",
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(top = 40.dp)
                .align(alignment = Alignment.TopCenter)
        )
        Image(
            painter = painterResource(R.drawable.ilustration_welcome),
            contentDescription = "welcome_ilustration",
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp, start = 32.dp, end = 32.dp),
        )
        Box(
            modifier = modifier
                .padding(bottom = 20.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp)
            ) {
                ContentSplash(
                    title = "Selamat Datang",
                    content = "Peduli daur ulang sampah\n" +
                            "Peduli masa depan",
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                )

                ButtonLargePrimary(
                    navigateButton = navigateLogin,
                    title = "LOGIN",
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                )
                ButtonLargeSecondary(
                    navigateButton = navigateRegister,
                    title = "REGISTER",
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun WelcomeScreenPreview() {
//    WelcomeScreen(navigateLogin = {}, navigateRegister = {})
}