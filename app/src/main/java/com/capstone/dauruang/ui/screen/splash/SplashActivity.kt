package com.capstone.dauruang.ui.screen.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.dauruang.R
import com.capstone.dauruang.ui.components.button.ButtonLargePrimary
import com.capstone.dauruang.ui.components.button.ButtonSmall
import com.capstone.dauruang.ui.components.content.ContentSplash
import com.capstone.dauruang.ui.theme.DauRuangTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context: Context = this

        setContent {
            DauRuangTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreen()
                }
            }
        }

        GlobalScope.launch(Dispatchers.Main) {
            delay(2000L)
            startNextActivity()
        }
    }

    private fun startNextActivity() {
        val intent = Intent(this, OnBoardingActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}

@Composable
fun SplashScreen () {
    SplashContent()
}

@Composable
fun SplashContent(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo_dauruang),
            contentDescription = stringResource(R.string.dauruang_logo),
            modifier = modifier
                .size(200.dp))
    }
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .size(200.dp)
                .padding(top = 10.dp),
            painter = painterResource(R.drawable.image_hand),
            contentDescription = stringResource(R.string.hand_image))
    }
}




// Kode ini tidak digunakan untuk belajar saja

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier
        .fillMaxSize(),
    navigateButton: () -> Unit
){
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ){
        Image(
            modifier = modifier
                .fillMaxSize(),
            alignment = Alignment.TopCenter,
            painter = painterResource(R.drawable.splash_image_one),
            contentDescription = stringResource(R.string.splash_image))
    }
    Box(
        modifier = modifier
            .padding(bottom = 20.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(top = 20.dp, start = 16.dp, end = 16.dp)
        ) {
            ContentSplash(
                title = "Semua Bisa\n" +
                        "Daur Ulang",
                content = "Dengan mendaur ulang kamu ikut serta dalam menyelamatkan dari rusaknya lingkungan")

            ButtonLargePrimary(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                navigateButton = navigateButton,
                title = "NEXT",
                fontWeight = FontWeight.Medium
            )
        }

    }
}

@Composable
fun SecondScreen(
    navigateNext: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxSize()
){
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ){
        Image(
            modifier = modifier
                .fillMaxWidth(),
            alignment = Alignment.TopCenter,
            painter = painterResource(R.drawable.splash_image_second),
            contentDescription = stringResource(R.string.splash_image))
    }
    Box(
        modifier = modifier
            .padding(bottom = 20.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(top = 20.dp, start = 16.dp, end = 16.dp)
        ) {
            ContentSplash(
                title = "Mudah Jual Limbah\n" +
                        "Lingkungan",
                content = "Menjual sampah dengan tepat dan dapatkan uangnya")

            Row(
                modifier = Modifier
                    .padding(top = 32.dp)
            ) {
                ButtonSmall(
                    navigateButton = navigateBack,
                    icons = Icons.Default.KeyboardArrowLeft
                )
                ButtonLargePrimary(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth(),
                    navigateButton = navigateNext,
                    title = "NEXT",
                    fontWeight = FontWeight.Medium
                )
            }
        }

    }
}

@Composable
fun ThirdScreen(
    navigateNext: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxSize()
){

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ){
        Image(
            modifier = modifier
                .fillMaxWidth(),
            alignment = Alignment.TopCenter,
            painter = painterResource(R.drawable.splash_image_third),
            contentDescription = stringResource(R.string.splash_image))
    }

    Box(
        modifier = modifier
            .padding(bottom = 20.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(top = 20.dp, start = 16.dp, end = 16.dp)
        ) {
            ContentSplash(
                title = "Daurkan dan\n" +
                        "Uangkan ",
                content = "Jangan diamkan limbah mu tidak berguna")

            Row(
                modifier = Modifier
                    .padding(top = 32.dp)
            ) {
                ButtonSmall(
                    navigateButton = navigateBack,
                    icons = Icons.Default.KeyboardArrowLeft
                )
                ButtonLargePrimary(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth(),
                    navigateButton = navigateNext,
                    title = "NEXT",
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun MainScreenPreview(){
    SplashContent()
}

@Preview(showBackground = true , device = Devices.PIXEL_3)
@Composable
fun FirstScreenPreview(){
    FirstScreen(navigateButton = {})
}


@Preview(showBackground = true , device = Devices.PIXEL_3)
@Composable
fun SecondScreenPreview(){
    SecondScreen(navigateNext = {}, navigateBack = {})
}


@Preview(showBackground = true , device = Devices.PIXEL_3)
@Composable
fun ThirdScreenPreview(){
    ThirdScreen(navigateNext = {}, navigateBack = {})
}

