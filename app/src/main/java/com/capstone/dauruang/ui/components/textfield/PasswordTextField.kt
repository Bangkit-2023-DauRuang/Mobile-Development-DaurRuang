package com.capstone.dauruang.ui.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White),
    lineColor: Color = colorResource(R.color.green_primary),

    textColor: Color,
    password: String,
    onPassChange: (String) -> Unit,
    isError: Boolean = false
) {
    var pass by remember { mutableStateOf(password) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val customTextFieldColors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = lineColor,
        unfocusedIndicatorColor = lineColor,
        containerColor = Color.White,
    )

    val leadingIconView = @Composable {
        IconButton(
            onClick = {},
        ) {
            Icon(
                Icons.Default.Lock,
                contentDescription = "",
                tint = colorResource(R.color.green_primary)
            )
        }
    }

    Column(
        modifier = Modifier
            .background(Color.White)
    ){
        TextField(
            value = password,
            onValueChange = onPassChange,
            singleLine = true,
            label = {
                Text(
                    text = "Password",
                    color = colorResource(R.color.green_primary),
                    fontSize = if (pass.isNotBlank()) 12.sp else 16.sp,
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = textColor,
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = image, description,
                        tint = colorResource(R.color.green_primary)
                    )
                }
            },
            leadingIcon = leadingIconView,
            colors = customTextFieldColors,
        )

        if(isError){
            Text(
                text = "Email tidak boleh kosong",
                color = Color.Red,
                fontSize = 10.sp,
                modifier = Modifier.padding(start = 52.dp, top = 2.dp)
            )
        }
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun PasswordTextFieldPreview() {
    PasswordTextField(
        textColor = colorResource(R.color.green_primary),
        password = "12345",
        onPassChange = {}
    )
}
