package com.capstone.dauruang.ui.components.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.dauruang.R

@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    color: Color = colorResource(R.color.green_primary),
    icons: ImageVector,
    title: String,
    navigateToMenu: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(top = 12.dp)
            .clickable{navigateToMenu},
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = icons ,
                    contentDescription = "icons_menu_setting",
                    tint = color
                )
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(start = 12.dp),
                    color = color
                )
            }
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight ,
                contentDescription = "arrow_right",
                tint = color
            )
        }
        Divider(
            thickness = 1.dp,
            color = colorResource(R.color.green_primary),
            modifier = Modifier
                .alpha(0.3f)
                .padding(top = 12.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun SettingItemPreview(){
    SettingItem(
        icons = Icons.Filled.Person,
        title = "Ubah Profile",
        navigateToMenu = {}
    )
}