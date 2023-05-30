package com.capstone.dauruang.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R
import com.capstone.dauruang.ui.components.button.ButtonLargeIconSecondary
import com.capstone.dauruang.ui.components.button.ButtonLargePrimary
import com.capstone.dauruang.ui.components.textfield.EmailTextField
import com.capstone.dauruang.ui.components.textfield.PasswordTextField

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Color.White),

    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPassChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onClear: () -> Unit,

    // navigasi
    navigateToRegister: () -> Unit
) {

    var offset by remember { mutableStateOf(0f) }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(
            modifier = Modifier
                .padding(vertical = 12.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(R.drawable.ilustration_login),
            contentDescription = "ilsutration_login",
        )

        Spacer(
            modifier = Modifier
                .padding(vertical = 4.dp)
        )

        // Content
        Column() {
            Text(
                text = "Hello, let’s Login",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.green_primary)
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "Adalah tempat terbaik untuk mu memilah sampah dan menjadikan uang",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.green_primary)
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 12.dp)
            )
            EmailTextField(
                textColor = colorResource(R.color.green_primary),
                email = email,
                onEmailChange = onEmailChange,
                onClear = onClear
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 4.dp)
            )
            PasswordTextField(
                textColor = colorResource(R.color.green_primary),
                password = password,
                onPassChange = onPassChange
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 12.dp)
            )
            ButtonLargePrimary(
                navigateButton = onLoginClick,
                title = "LOGIN",
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, end = 4.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Belum punya akun ?",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = "Register",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = colorResource(R.color.green_primary),
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clickable { navigateToRegister },
                )
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 4.dp)
            )
            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Divider(
                    thickness = 2.dp,
                    color = colorResource(R.color.green_primary),
                    modifier = Modifier
                        .width(150.dp)
                )
                Text(
                    text = "or",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )
                Divider(
                    thickness = 2.dp,
                    color = colorResource(R.color.green_primary),
                    modifier = Modifier
                        .width(150.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 4.dp)
            )
            ButtonLargeIconSecondary(
                onClickButton = {},
                title = "Google",
                icons = painterResource(R.drawable.google)
            )
            Spacer(
                modifier = Modifier
                    .padding(bottom = 100.dp)
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        email = "davidkrb52@gmail.com",
        password = "12345",
        onEmailChange = {},
        onPassChange = {},
        onLoginClick = {},
        onClear = {},
        navigateToRegister = {}
    )
}