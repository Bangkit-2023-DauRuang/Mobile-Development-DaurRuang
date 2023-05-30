package com.capstone.dauruang.ui.components.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R

@Composable
fun ButtonLargeIconSecondary(
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color.White,
        contentColor = colorResource(R.color.green_primary)
    ),

    onClickButton: () -> Unit,
    title: String,
    icons: Painter,
    ) {

    val textTitle = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
            append("Login with ")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(title)
        }
    }

    Button(
        onClick = onClickButton,
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .border(2.dp, colorResource(R.color.green_primary), shape = RoundedCornerShape(10.dp)),
        colors = buttonColors
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.google),
                contentDescription = "google_logo" )
            Text(
                modifier = modifier
                    .padding(start = 12.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                text = textTitle,
                fontSize = 16.sp,
                color = colorResource(R.color.green_primary),
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun ButtonLargeIconSecondaryPreview() {
    ButtonLargeIconSecondary(
        onClickButton = {},
        title = "Google",
        icons = painterResource(R.drawable.google)
    )
}
