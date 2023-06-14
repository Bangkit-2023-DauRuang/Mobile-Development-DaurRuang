package com.capstone.dauruang.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R


@Composable
fun ButtonSummarySecondary(
    modifier: Modifier = Modifier,
    eventClick: () -> Unit,
    height: Dp,
    width: Float = 1f,
) {
    Button(
        onClick = eventClick,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .padding(horizontal = 6.dp),
        shape = MaterialTheme.shapes.extraLarge,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = colorResource(R.color.green_primary),
            containerColor = Color.Transparent,
            disabledContentColor = Color.Gray
        ),
        border = BorderStroke(2.dp, colorResource(R.color.green_primary))
    ) {
        Text(
            text = "Batalkan Order",
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 2.dp)
        )
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun ButtonSummarySecondaryPreview(){
    ButtonSummarySecondary(
        eventClick = {},
        height = 56.dp
    )
}