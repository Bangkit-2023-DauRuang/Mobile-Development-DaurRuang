package com.capstone.dauruang.ui.components.button

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.capstone.dauruang.R

@Composable
fun ButtonMediumIconPrimary (
    modifier: Modifier = Modifier,
    eventClick: () -> Unit,
    height: Dp,
    width: Float = 1f,
    icon: ImageVector,
    rotate: Float = 0f
) {
    val background = ButtonDefaults.buttonColors(
        containerColor = colorResource(R.color.green_primary),
        contentColor = Color.White
    )

    Button(
        onClick = eventClick,
        colors = background,
        modifier = Modifier
            .fillMaxWidth(width)
            .height(height)
            .padding(horizontal = 6.dp)
    ) {
        Icon(
            imageVector = icon ,
            contentDescription = "scan_button",
            modifier = Modifier
                .size(28.dp)
                .rotate(rotate))
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun ButtonMediumIconPrimaryPreview(){
    ButtonMediumIconPrimary(
        eventClick = {},
        height = 56.dp,
        icon = Icons.Filled.ArrowBack,
        rotate = 180f
    )
}