package com.capstone.dauruang.ui.screen.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.dauruang.data.dummy.HistoryDataProvider
import com.capstone.dauruang.model.History
import com.capstone.dauruang.ui.components.content.HistoryItem
import com.capstone.dauruang.ui.components.content.TitlePage
import com.capstone.dauruang.ui.screen.scan.ScanActivity
import com.capstone.dauruang.ui.screen.scan.ScanScreen
import com.capstone.dauruang.ui.theme.DauRuangTheme


class HistoryActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DauRuangTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HistoryContent(
                        navigateBack = { onBackPressed() },
                        history = HistoryDataProvider.history
                    )
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, HistoryActivity::class.java)
    }

}

@Composable
fun HistoryContent(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
        .background(Color.White),
    history: MutableList<History>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
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

