package com.capstone.dauruang.ui.screen.transaction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R
import com.capstone.dauruang.ui.components.content.TitlePage
import com.capstone.dauruang.ui.nav.BottomNavTransaction

class TransactionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TransactionScreen(
                navigateBack = { onBackPressed() }
            )
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, TransactionActivity::class.java)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    val transScreenState = rememberSaveable { mutableStateOf(BottomNavTransaction.Diproses) }
    var selectedIndex = remember { mutableStateOf(0) }
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.green_primary), shape = RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    ))
                    .padding(top = 12.dp),
            ) {
                var animate by remember { mutableStateOf(false) }

                NavigationBar(
                    modifier = modifier.fillMaxWidth(),
                    containerColor = colorResource(R.color.green_primary),
                ) {
                    TabRow(
                        selectedTabIndex = selectedIndex.value,
                        divider = {},
                        indicator = noIndicator,
                        containerColor = colorResource(R.color.green_primary),
                        modifier = Modifier
                            .background(colorResource(R.color.green_primary))
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 12.dp),
                    ) {
                        Tab(
                            selected = 0 == selectedIndex.value,
                            onClick = {
                                selectedIndex.value = 0
                                // Toast.makeText(context, "Ini tab ${selectedIndex}", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CustomMenuButtonTransaction(
                                text = "Diproses",
                                selected = 0 == selectedIndex.value,
                            )
                        }

                        Tab(
                            selected = 1 == selectedIndex.value,
                            onClick = {
                                selectedIndex.value = 1
                                // Toast.makeText(context, "Ini tab ${selectedIndex}", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CustomMenuButtonTransaction(
                                text = "Selesai",
                                selected = 1 == selectedIndex.value,
                            )
                        }

                        Tab(
                            selected = 2 == selectedIndex.value,
                            onClick = {
                                selectedIndex.value = 2
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CustomMenuButtonTransaction(
                                text = "Dibatalkan",
                                selected = 2 == selectedIndex.value,
                            )
                        }
                    }
                }
            }
        },
        content = { paddingValue ->
            Column(
                modifier = modifier.padding(paddingValue).fillMaxHeight()
            ) {
                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp)) {
                    TitlePage(
                        navigateBack = navigateBack,
                        title = "Transaksi ${if (selectedIndex.value == 0) "Diproses" else if (selectedIndex.value == 1) "Selesai" else "Dibatalkan"}"
                    )

                    // Loop
                    Text(text = selectedIndex.value.toString())
                }
            }
        }
    )
}

@Composable
fun BottomBarTransaction(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .background(colorResource(R.color.green_primary))
        .padding(top = 12.dp),
    context: Context = LocalContext.current,

    selectedIndex: MutableState<Int>,
    onValueGetIndex: () -> Unit,
    transScreenState: MutableState<BottomNavTransaction>
) {
    var animate by remember { mutableStateOf(false) }

    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = colorResource(R.color.green_primary),
    ) {
        TabRow(
            selectedTabIndex = selectedIndex.value,
            divider = {},
            indicator = noIndicator,
            containerColor = colorResource(R.color.green_primary),
            modifier = Modifier
                .background(colorResource(R.color.green_primary))
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
        ) {
            Tab(
                selected = 0 == selectedIndex.value,
                onClick = {
                    selectedIndex.value = 0
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomMenuButtonTransaction(
                    text = "Diproses",
                    selected = 0 == selectedIndex.value,
                )
            }

            Tab(
                selected = 1 == selectedIndex.value,
                onClick = {
                    selectedIndex.value = 1
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomMenuButtonTransaction(
                    text = "Selesai",
                    selected = 1 == selectedIndex.value,
                )
            }

            Tab(
                selected = 2 == selectedIndex.value,
                onClick = {
                    selectedIndex.value = 2
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomMenuButtonTransaction(
                    text = "Dibatalkan",
                    selected = 2 == selectedIndex.value,
                )
            }


        }
    }

}

private val noIndicator: @Composable (List<TabPosition>) -> Unit = {}

@Composable
fun CustomMenuButtonTransaction(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    color: Color = colorResource(R.color.green_primary)
) {
    Surface(
        color = when {
            selected -> Color.White
            else -> color
        },
        contentColor = when {
            selected -> color
            else -> Color.White
        },
        shape = RoundedCornerShape(100.dp),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun TransactionPreview() {
    TransactionScreen(navigateBack = {})
    CustomMenuButtonTransaction(text = "Diproses", selected = true)
}
