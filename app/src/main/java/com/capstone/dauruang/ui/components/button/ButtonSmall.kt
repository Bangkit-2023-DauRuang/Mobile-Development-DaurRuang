package com.capstone.dauruang.ui.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R

@Composable
fun ButtonSmall(
    navigateButton: () -> Unit,
    icons: ImageVector,
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = colorResource(R.color.green_primary),
        contentColor = Color.White
    )
){

    Button(
        onClick = navigateButton,
        modifier = modifier
            .height(48.dp),
        shape = RoundedCornerShape(16),
        colors = buttonColors
    ) {
        Icon(
            imageVector = icons ,
            contentDescription = "arrow_back",
            tint = Color.White
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ButtonSmallPreview(){
    ButtonSmall(navigateButton={}, icons = Icons.Default.KeyboardArrowLeft )
}