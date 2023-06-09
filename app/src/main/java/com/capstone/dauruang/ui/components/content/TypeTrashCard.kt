package com.capstone.dauruang.ui.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.dauruang.R

@Composable
fun TypeTrashCard(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .height(92.dp),
    imageUrl: Int,
    title: String,
) {
    Column(
        modifier = modifier
            .border(2.dp, colorResource(R.color.green_primary), shape = RoundedCornerShape(10.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(imageUrl) ,
            contentDescription = title )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun TypeTrashCardPreview(){
    TypeTrashCard(
        imageUrl = R.drawable.botol_1,
        title = "Image botol 1"
    )
}

