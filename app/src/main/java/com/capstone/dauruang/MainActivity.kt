package com.capstone.dauruang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.capstone.dauruang.ui.screen.login.LoginScreen
import com.capstone.dauruang.ui.screen.splash.FirstScreen
import com.capstone.dauruang.ui.screen.splash.MainScreen
import com.capstone.dauruang.ui.screen.splash.SecondScreen
import com.capstone.dauruang.ui.screen.splash.ThirdScreen
import com.capstone.dauruang.ui.screen.welcome.WelcomeScreen
import com.capstone.dauruang.ui.theme.DauRuangTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DauRuangTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   // WelcomeScreen(navigateLogin = {}, navigateRegister = {})
                   // MainScreen()
                   // FirstScreen(navigateButton = {})
                   // SecondScreen(navigateNext = {}, navigateBack = {})
                   // ThirdScreen(navigateNext = {}, navigateBack = {})

                    var email by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }

                    LoginScreen(
                        email = email ,
                        password = password,
                        onEmailChange = { newValue -> email = newValue },
                        onPassChange = { newValue -> password = newValue},
                        onLoginClick = {},
                        navigateToRegister = {},
                        onClear = { email = "" }
                    )
                }
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
    DauRuangTheme {
        WelcomeScreen(navigateLogin = {}, navigateRegister = {})
    }
}