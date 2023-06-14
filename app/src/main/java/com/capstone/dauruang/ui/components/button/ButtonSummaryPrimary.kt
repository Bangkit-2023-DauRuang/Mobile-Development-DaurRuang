package com.capstone.dauruang.ui.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R

@Composable
fun ButtonSummaryPrimary (
    modifier: Modifier = Modifier,
    eventClick: () -> Unit,
    height: Dp,
) {
    val background = ButtonDefaults.buttonColors(
        containerColor = colorResource(R.color.green_primary),
        contentColor = Color.White
    )

    Button(
        onClick = eventClick,
        colors = background,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .padding(horizontal = 6.dp)
    ) {
        Text(
            text = "Jemput Limbah",
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun ButtonSummaryPrimaryPreview(){
    ButtonSummaryPrimary(
        eventClick = {},
        height = 56.dp
    )
}