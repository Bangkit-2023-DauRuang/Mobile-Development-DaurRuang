package com.capstone.dauruang.ui.screen.transaction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.capstone.dauruang.ui.components.content.TitlePage
import com.capstone.dauruang.ui.theme.DauRuangTheme

class TransactionSummaryActivity: ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContent{
            DauRuangTheme {
                Scaffold(
                    bottomBar = {},
                    content = {paddingValues ->
                        Column(modifier = Modifier.padding(paddingValues)) {

                        }
                    }
                )
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, TransactionSummaryActivity::class.java)
    }
}


@Composable
fun SummaryScreen(
    navigateBack: () -> Unit,
){
    TitlePage(
        navigateBack = navigateBack,
        title = "Summary Limbah Sampah"
    )



}

@Composable
fun BottomBarSummary(){

}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun SummaryPreview(){
    SummaryScreen(
        navigateBack = {}
    )
}