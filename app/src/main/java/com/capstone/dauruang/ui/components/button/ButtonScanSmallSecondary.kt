package com.capstone.dauruang.ui.components.button

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun ButtonScanSmallSecondary(
    modifier: Modifier = Modifier,
    eventClick: () -> Unit
) {
    val background = ButtonDefaults.buttonColors(
        containerColor = Color.Unspecified,
        contentColor = colorResource(R.color.green_primary)
    )

    Button(
        onClick = eventClick,
        colors = background,
        modifier = Modifier
            .height(56.dp)
            .border(2.dp, colorResource(R.color.green_primary), shape = RoundedCornerShape(100.dp)),
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_scan) ,
            contentDescription = "scan_button",
            modifier = Modifier.size(32.dp)
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun ButtonScanSmallSecondaryPreview(){
    ButtonScanSmallSecondary(
        eventClick = {}
    )
}