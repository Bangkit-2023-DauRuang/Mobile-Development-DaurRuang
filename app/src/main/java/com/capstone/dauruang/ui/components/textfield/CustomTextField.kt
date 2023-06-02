package com.capstone.dauruang.ui.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person2
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White),
    lineColor: Color = colorResource(R.color.green_primary),

    title: String,
    textColor: Color,
    iconLeading: ImageVector,
    keyboardType: KeyboardType,
    text: String,
    onTextChange: (String) -> Unit,
    onClear: () -> Unit
) {

    var textValue by remember { mutableStateOf(text) }

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
                imageVector = iconLeading,
                contentDescription = "",
                tint = colorResource(R.color.green_primary)
            )
        }
    }

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


    TextField(
        value = text ,
        onValueChange = onTextChange,
        label = {
            Text(
                text = title,
                color = colorResource(R.color.green_primary),
                fontSize = if(textValue.isNotBlank()) 12.sp else 16.sp,
            )
        },
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(),
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = textColor,
        ),
        colors = customTextFieldColors,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        trailingIcon = if (text.isNotBlank()) trailingIconView else null,
        leadingIcon = leadingIconView
    )

}


@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun CustomTextFieldPreview() {
    CustomTextField(
        title = "Username",
        textColor = colorResource(R.color.green_primary),
        iconLeading = Icons.Filled.Person2,
        keyboardType = KeyboardType.Phone,
        text = "davidnasrulloh",
        onTextChange = {},
        onClear = {}
    )
}