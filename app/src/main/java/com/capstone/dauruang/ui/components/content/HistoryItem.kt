package com.capstone.dauruang.ui.components.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R
import com.capstone.dauruang.data.dummy.HistoryDummyProvider
import com.capstone.dauruang.model.History

@Composable
fun HistoryItem(
    modifier: Modifier = Modifier,
    history: History,
    color: Color = colorResource(R.color.text_primary)
) {
    Row(
        modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ){
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "${history.title}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = color
            )
            Row(
                modifier = Modifier.padding(top = 2.dp)
            ) {
                Text(
                    text = "${history.date}",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(end = 4.dp),
                    color = color
                )
                Text(
                    text = "${history.time}",
                    fontSize = 12.sp,
                    color = color
                )
            }
        }
        Row(
            modifier = modifier
                .padding(start = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if(history.money.isNullOrEmpty()) "" else "Rp. ${history.money}",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(end = 8.dp),
                color = color
            )
            Text(
                text = if(history.point.toString().isNullOrEmpty()) "0" else " + ${history.point} Poin",
                fontSize = 14.sp,
                color = color
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun HistoryItemPreview(@PreviewParameter(HistoryDummyProvider::class) data: History) {
    HistoryItem(
        history = data
    )
}