package com.capstone.dauruang.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.dauruang.R

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier
        .fillMaxSize()
){
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
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun WelcomeScreenPreview(){
    WelcomeScreen()
}