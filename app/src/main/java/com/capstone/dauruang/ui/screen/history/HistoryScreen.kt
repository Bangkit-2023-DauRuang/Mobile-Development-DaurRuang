package com.capstone.dauruang.ui.screen.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.data.dummy.HistoryDataProvider
import com.capstone.dauruang.data.dummy.HistoryDummyProvider
import com.capstone.dauruang.model.History
import com.capstone.dauruang.ui.components.content.HistoryItem
import com.capstone.dauruang.ui.components.content.TitlePage

@Composable
fun HistoryScreen() {

}

@Composable
fun HistoryContent(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    history: MutableList<History>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        TitlePage(
            navigateBack = navigateBack,
            title = "History"
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        LazyColumn {
            itemsIndexed(
                items = history,
                itemContent = { index, history ->
                    HistoryItem(history = history)
                }
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun HistoryScreenPreview() {
    HistoryContent(
        navigateBack = {},
        history = HistoryDataProvider.history
    )
}

