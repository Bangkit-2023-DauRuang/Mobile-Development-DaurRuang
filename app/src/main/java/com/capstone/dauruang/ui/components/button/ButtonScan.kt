package com.capstone.dauruang.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Scanner
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.dauruang.R

@Composable
fun ButtonScan(
    modifier: Modifier = Modifier
        .padding(horizontal = 24.dp, vertical = 12.dp),
    eventClick: () -> Unit
) {

    val background = ButtonDefaults.buttonColors(
        containerColor = colorResource(R.color.green_primary),
        contentColor = Color.White
    )

    Button(
        onClick = eventClick,
        colors = background,
        modifier = Modifier
            .width(160.dp)
            .height(56.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_scan) ,
            contentDescription = "scan_button",
            modifier = Modifier.size(28.dp))
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun ButtonScanPreview(){
    ButtonScan(
        eventClick = {}
    )
}
