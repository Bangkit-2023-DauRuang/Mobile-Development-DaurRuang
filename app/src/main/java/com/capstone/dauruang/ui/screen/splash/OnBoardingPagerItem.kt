package com.capstone.dauruang.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.dauruang.R
import com.capstone.dauruang.ui.components.button.ButtonLargePrimary
import com.capstone.dauruang.ui.components.content.ContentSplash

@Composable
fun OnboardingPagerItem(item: Onboard, modifier: Modifier = Modifier.fillMaxSize()) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ){
        Column() {
            Image(
                modifier = modifier
                    .fillMaxWidth(),
                alignment = Alignment.TopCenter,
                painter = painterResource(item.imageFile),
                contentDescription = stringResource(R.string.splash_image)
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 96.dp)
        ){
            Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 16.dp, end = 16.dp)
                ) {
                    ContentSplash(
                        title = item.title,
                        content = item.description
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun OnboardingPagerItemPreview(){
    OnboardingPagerItem(
        item = onboardingList[1]
    )
}


