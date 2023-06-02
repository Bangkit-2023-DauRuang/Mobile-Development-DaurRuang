package com.capstone.dauruang.ui.screen.profile

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CommentBank
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R
import com.capstone.dauruang.ui.components.content.SettingItem
import com.capstone.dauruang.ui.components.content.TitlePage

@Composable
fun ProfileScreen() {

}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier
        .fillMaxSize(),
    imageProfile: Painter = painterResource(R.drawable.image_default),
    nameProfile: String,
    point: Int = 0,
    navigateBack: () -> Unit
){
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitlePage(
            navigateBack = navigateBack,
            title = "My Profile"
        )
        Column(
            modifier = Modifier
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = imageProfile,
                contentDescription = "image_profile",
                modifier = Modifier
                    .size(80.dp)
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = nameProfile,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "$point Point",
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "My Profile Setting",
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 4.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            SettingItem(
                icons = Icons.Filled.Person,
                title = "Ubah profile",
                navigateToMenu = {}
            )
            SettingItem(
                icons = Icons.Filled.LocationOn,
                title = "Ubah default location",
                navigateToMenu = {}
            )
            SettingItem(
                icons = Icons.Filled.CreditCard,
                title = "Ubah data bank",
                navigateToMenu = {}
            )

            Text(
                text = "Setting App",
                modifier = Modifier
                    .padding(top = 28.dp, bottom = 4.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            SettingItem(
                icons = Icons.Filled.DarkMode,
                title = "Ubah theme",
                navigateToMenu = {}
            )
            SettingItem(
                icons = Icons.Filled.Info,
                title = "Info app",
                navigateToMenu = {}
            )
        }
    }


}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun ProfileScreenPreview(){
    ProfileContent(
        navigateBack = {},
        imageProfile = painterResource(R.drawable.david),
        nameProfile = "David Nasrulloh"
    )
}