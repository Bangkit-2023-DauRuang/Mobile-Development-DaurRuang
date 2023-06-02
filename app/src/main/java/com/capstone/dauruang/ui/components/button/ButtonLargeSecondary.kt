package com.capstone.dauruang.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R

@Composable
fun ButtonLargeSecondary(
    navigateButton: () -> Unit,
    title: String,
    fontWeight: FontWeight,
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color.White,
        contentColor = colorResource(R.color.green_primary)
    )
) {
    Button(
        onClick = navigateButton,
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .border(2.dp, colorResource(R.color.green_primary), shape = RoundedCornerShape(10.dp)),
        colors = buttonColors
    ) {
        Text(
            modifier = modifier
                .clip(shape = RoundedCornerShape(8.dp)),
            text = title,
            fontSize = 18.sp,
            fontWeight = fontWeight,
            color = colorResource(R.color.green_primary),
        )
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ButtonLargeSecondaryPreview() {
    ButtonLargeSecondary(
        navigateButton = {},
        title = "NEXT",
        fontWeight = FontWeight.Medium
    )
}