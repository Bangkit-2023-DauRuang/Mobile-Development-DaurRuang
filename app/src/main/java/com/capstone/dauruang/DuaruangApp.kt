package com.capstone.dauruang

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.capstone.dauruang.ui.screen.home.HomeScreen
import com.capstone.dauruang.ui.screen.register.RegisterContent

@Composable
fun DuaruangApp() {
    HomeScreen()



//     WelcomeScreen(navigateLogin = {}, navigateRegister = {})
//     MainScreen()
//     FirstScreen(navigateButton = {})
//     SecondScreen(navigateNext = {}, navigateBack = {})
//     ThirdScreen(navigateNext = {}, navigateBack = {})

//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    var username by remember { mutableStateOf("") }
//    var noHp by remember { mutableStateOf("") }

//                    LoginContent(
//                        email = email ,
//                        password = password,
//                        onEmailChange = { newValue -> email = newValue },
//                        onPassChange = { newValue -> password = newValue},
//                        onLoginClick = {},
//                        navigateToRegister = {},
//                        onClear = { email = "" }
//                    )

//    RegisterContent(
//        username = username,
//        email = email,
//        password = password,
//        noHp = noHp,
//        onUsernameChange = { newValue -> username = newValue },
//        onEmailChange = { newValue -> email = newValue },
//        onPassChange = { newValue -> password = newValue },
//        onNoHpChange = { newValue -> noHp = newValue },
//        onRegisterClick = {},
//        onClearEmail = { email = "" },
//        onClearUsername = { username = "" },
//        onClearHp = { noHp = "" },
//        navigateToLogin = {}
//    )
}