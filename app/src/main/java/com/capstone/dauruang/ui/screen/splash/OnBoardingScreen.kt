package com.capstone.dauruang.ui.screen.splash

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capstone.dauruang.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.capstone.dauruang.ui.components.button.ButtonLargePrimary
import com.capstone.dauruang.ui.screen.welcome.WelcomeActivity

@Composable
fun OnBoardingScreen() {
    OnBoardingContent()
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingContent(){
    var currentPage by remember { mutableStateOf(0)}
    var pagerState = rememberPagerState { onboardingList.size }

    val context = LocalContext.current

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                OnboardingPagerItem(onboardingList[page])
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp)
            ){
                Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally){
                    Row(modifier = Modifier, horizontalArrangement = Arrangement.Center) {
                        onboardingList.forEachIndexed { index, _ ->
                            OnboardingPagerSlide(
                                selected = index == pagerState.currentPage,
                                colorResource(R.color.green_primary),
                                Icons.Filled.Album
                            )
                        }
                    }

                    if(pagerState.currentPage == 2){
                        ButtonLargePrimary(
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp, top = 12.dp)
                                .fillMaxWidth(),
                            navigateButton = { context.startActivity(WelcomeActivity.newIntent(context)) },
                            title = "NEXT",
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }


}

data class Onboard(val title: String, val description: String, val imageFile: Int)
val onboardingList = listOf(
    Onboard(
        "Semua Bisa\n"+"Daur Ulang",
        "Dengan mendaur ulang kamu ikut serta dalam menyelamatkan dari rusaknya lingkungan",
        R.drawable.splash_image_one
    ),
    Onboard(
        "Mudah Jual Limbah\n"+"Lingkungan",
        "Menjual sampah dengan tepat dan dapatkan uangnya",
        R.drawable.splash_image_second
    ),
    Onboard(
        "Daurkan dan\n"+"Uangkan ",
        "Jangan diamkan limbah mu tidak berguna",
        R.drawable.splash_image_third
    )
)