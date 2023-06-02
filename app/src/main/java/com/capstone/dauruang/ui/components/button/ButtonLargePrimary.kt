package com.capstone.dauruang.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R

@Composable
fun ButtonLargePrimary (
    navigateButton: () -> Unit,
    title: String,
    fontWeight: FontWeight,
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = colorResource(R.color.green_primary),
        contentColor = Color.White
    )
) {

    Button(
        onClick = navigateButton,
        modifier = modifier
            .height(44.dp),
        shape = RoundedCornerShape(16),
        colors = buttonColors
    ) {
        Text(
            text = title,
            fontSize = 18.sp ,
            fontWeight = fontWeight,
            color = Color.White,
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ButtonLargePrimaryPreview(){
    ButtonLargePrimary(
        navigateButton = {},
        title = "Next",
        fontWeight = FontWeight.Medium
    )
}
