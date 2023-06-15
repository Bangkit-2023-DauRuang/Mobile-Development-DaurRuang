package com.capstone.dauruang.ui.screen.transaction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.dauruang.R
import com.capstone.dauruang.data.network.response.Orders
import com.capstone.dauruang.model.User
import com.capstone.dauruang.ui.ViewModelFactory
import com.capstone.dauruang.ui.components.content.TitlePage
import com.capstone.dauruang.ui.components.content.TransactionItem
import com.capstone.dauruang.ui.nav.BottomNavTransaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TransactionActivity : ComponentActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser

    private val viewModel: TransactionViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        val name = firebaseUser.displayName
        val email = firebaseUser.email

        setContent {
//             val orderListState: List<Orders> by viewModel.ordersResult.observeAsState(initial = emptyList())
            val orderListState: List<Orders> by viewModel.ordersUserResult.observeAsState(initial = emptyList())
            val orderList: List<Orders> = orderListState

            TransactionScreen(
                navigateBack = { onBackPressed() },
                orderList = orderList ?: emptyList(),
                name = name.toString(),
                email = email.toString()
            )
        }

        if (email != null) {
            setupViewModelObservers(email)
        }

//        setupViewModelObservers()
    }

//    private fun setupViewModelObservers() {
//         viewModel.getAllOrdersData()
//
//        viewModel.errorMessage.observe(this) { errorMessage ->
//            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
//            Log.e("Error nya disini ", errorMessage)
//        }
//    }

//    override fun onResume() {
//        super.onResume()
//        setupViewModelObservers()
//    }

    private fun setupViewModelObservers(email: String) {

        viewModel.getUserOrdersData(email)

        viewModel.errorMessage.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            Log.e("Error nya disini ", errorMessage)
        }
    }


    override fun onResume() {
        super.onResume()

        val emailUser = FirebaseAuth.getInstance().currentUser!!.email
        emailUser?.let { setupViewModelObservers(it) }
    }



    companion object {
        fun newIntent(context: Context) = Intent(context, TransactionActivity::class.java)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    orderList: List<Orders>,
    name: String,
    email: String
) {

    val transScreenState = rememberSaveable { mutableStateOf(BottomNavTransaction.Diproses) }
    var selectedIndex = remember { mutableIntStateOf(0) }
    val context = LocalContext.current


    val filteredOrderList = when (selectedIndex.value) {
        0 -> orderList.filter { it.status_pemesanan == "Diproses" || it.status_pemesanan == "Pengecekan"  }
        1 -> orderList.filter { it.status_pemesanan == "Selesai" }
        2 -> orderList.filter { it.status_pemesanan == "Dibatalkan" }
        else -> orderList // Ketika selectedIndex tidak sesuai dengan kondisi yang ada, mengembalikan orderList utuh
    }


    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        colorResource(R.color.green_primary), shape = RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
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
                modifier = modifier
                    .padding(paddingValue)
                    .fillMaxHeight()
            ) {
                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp)) {
                    TitlePage(
                        navigateBack = navigateBack,
                        title = "Transaksi ${if (selectedIndex.value == 0) "Diproses" else if (selectedIndex.value == 1) "Selesai" else "Dibatalkan"}"
                    )

                    LazyColumn {
                        items(filteredOrderList) { order ->
                            TransactionItem(order = order)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun OrderItem(order: Orders) {
    // Tampilkan informasi order
    Text("Order ID: ${order.id}")
    Text("Username: ${order.username}")
    Text("Jenis Sampah: ${order.jenis_sampah}")
    // Tambahkan informasi lainnya sesuai kebutuhan
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
//    TransactionScreen(navigateBack = {})
    CustomMenuButtonTransaction(text = "Diproses", selected = true)
}
