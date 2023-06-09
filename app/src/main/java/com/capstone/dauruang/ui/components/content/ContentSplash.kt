package com.capstone.dauruang.ui.components.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R

@Composable
fun ContentSplash(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    color: Color = colorResource(R.color.green_primary)
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp, start = 12.dp, end = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = colorResource(R.color.green_primary),
            modifier = Modifier
                .padding(bottom = 12.dp, start = 12.dp, end = 12.dp),
            textAlign = TextAlign.Center,
            style = TextStyle(
                lineHeight = 40.sp,
            )
        )
        Text(
            text = content,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = colorResource(R.color.green_primary),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 4.dp, start = 12.dp, end = 12.dp),
        )
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun ContentSplashPreview(){
    ContentSplash(
        title = "Semua Bisa\n" +
                "Daur Ulang",
        content = "Dengan mendaur ulang kamu ikut serta dalam menyelamatkan dari rusaknya lingkungan"
    )
}