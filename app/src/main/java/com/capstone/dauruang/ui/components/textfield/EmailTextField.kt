package com.capstone.dauruang.ui.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White),
    lineColor: Color = colorResource(R.color.green_primary),

    textColor: Color,
    email: String,
    onEmailChange: (String) -> Unit,
    onClear: () -> Unit,
    isError: Boolean = false
) {
    var text by remember { mutableStateOf(email) }

    val customTextFieldColors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = lineColor,
        unfocusedIndicatorColor = lineColor,
        containerColor = Color.White,
    )

    val trailingIconView = @Composable {
        IconButton(
            onClick = onClear,
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = "",
                tint = colorResource(R.color.green_primary)
            )
        }
    }

    val leadingIconView = @Composable {
        IconButton(
            onClick = {
                text = email
            },
        ) {
            Icon(
                Icons.Default.Email,
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
            value = email,
            onValueChange = onEmailChange,
            label = {
                Text(
                    text ="Email",
                    color = colorResource(R.color.green_primary),
                    fontSize = if(text.isNotBlank()) 12.sp else 16.sp,
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = textColor,
            ),
            colors = customTextFieldColors,
            trailingIcon = if (email.isNotBlank()) trailingIconView else null,
            leadingIcon = leadingIconView
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
fun EmailTextFieldPreview(){
    EmailTextField(
        email = "davidkrb52@gmail.com",
        onEmailChange = {},
        textColor = colorResource(R.color.green_primary),
        onClear = {}
    )
}
