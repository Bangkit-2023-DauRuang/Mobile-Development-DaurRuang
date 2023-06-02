package com.capstone.dauruang.ui.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R
import com.capstone.dauruang.ui.components.button.ButtonMediumIconPrimary
import com.capstone.dauruang.ui.components.button.ButtonScanSmallSecondary

@Composable
fun InfoScanCard (
    modifier: Modifier = Modifier,
    titleTrash: String,
    description: String,
    color: Color,
    onScanClick: () -> Unit,
    navigateNext: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(12.dp)
            .background(
                shape = RoundedCornerShape(12.dp),
                color = color
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 18.dp)
        ) {
            Text(
                text = titleTrash,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.green_primary),
                fontSize = 18.sp
            )
            Text(
                text = description,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontSize = 16.sp
            )
            Text(
                text = "\n#jangan biarkan sampah dimana mana",
                fontWeight = FontWeight.Light,
                color = Color.Black,
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                ButtonScanSmallSecondary(
                    eventClick = onScanClick
                )
                ButtonMediumIconPrimary(
                    eventClick = navigateNext,
                    height = 56.dp,
                    rotate = 180f,
                    icon = Icons.Filled.ArrowBack
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun InfoScanCardPreview () {
    InfoScanCard(
        titleTrash = "Plastic",
        description = "Daurkan platic mu untuk keberlangsungan yang lebih baik",
        color = colorResource(R.color.plastic_color_bg),
        onScanClick = {},
        navigateNext = {}
    )
}