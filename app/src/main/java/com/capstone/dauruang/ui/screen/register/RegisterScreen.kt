package com.capstone.dauruang.ui.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R
import com.capstone.dauruang.ui.components.button.ButtonLargeIconSecondary
import com.capstone.dauruang.ui.components.button.ButtonLargePrimary
import com.capstone.dauruang.ui.components.textfield.CustomTextField
import com.capstone.dauruang.ui.components.textfield.EmailTextField
import com.capstone.dauruang.ui.components.textfield.PasswordTextField

@Composable
fun RegisterScreen(

) {

}

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Color.White),

    username: String? = null,
    email: String,
    password: String,
    noHp: String? = null,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPassChange: (String) -> Unit,
    onNoHpChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onClearUsername: () -> Unit,
    onClearHp: () -> Unit,
    onClearEmail: () -> Unit,

    // navigasi
    navigateToLogin: () -> Unit
){
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
            painter = painterResource(R.drawable.ilustration_register),
            contentDescription = "ilsutration_regitser",
        )

        Spacer(
            modifier = Modifier
                .padding(vertical = 4.dp)
        )

        // Content
        Column() {
            Text(
                text = "Let’s create new account",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.green_primary)
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "Belum punya aku DauRuang ? Mari buat akun mu sekarang \uD83D\uDE0A",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.green_primary)
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 12.dp)
            )
            CustomTextField(
                title = "Username",
                textColor = colorResource(R.color.green_primary),
                iconLeading = Icons.Filled.Person2,
                keyboardType = KeyboardType.Text,
                text = username!!,
                onTextChange = onUsernameChange,
                onClear = onClearUsername
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 4.dp)
            )
            EmailTextField(
                textColor = colorResource(R.color.green_primary),
                email = email,
                onEmailChange = onEmailChange,
                onClear = onClearEmail
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
                    .padding(vertical = 4.dp)
            )
            CustomTextField(
                title = "No Hp",
                textColor = colorResource(R.color.green_primary),
                iconLeading = Icons.Filled.Phone,
                keyboardType = KeyboardType.Phone,
                text = noHp!!,
                onTextChange = onNoHpChange,
                onClear = onClearHp
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 12.dp)
            )
            ButtonLargePrimary(
                navigateButton = onRegisterClick,
                title = "REGISTER",
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, end = 4.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Sudah punya akun ?",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = "Login",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = colorResource(R.color.green_primary),
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clickable { navigateToLogin },
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
                type = "Register",
                title = "Google",
                icons = painterResource(R.drawable.google)
            )
            Spacer(
                modifier = Modifier
                    .padding(bottom = 50.dp)
            )
        }
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun RegisterScreenPreview(){
    RegisterContent(
        username = "david",
        email = "davidkrb52@gmail.com",
        password = "12345",
        noHp = "081554465073",
        onUsernameChange = {},
        onEmailChange = {},
        onPassChange = {},
        onNoHpChange = {},
        onRegisterClick = {},
        onClearEmail = {},
        onClearUsername = {},
        onClearHp = {},
        navigateToLogin = {}
    )
}
