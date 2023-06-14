package com.capstone.dauruang.ui.screen.transaction

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import coil.compose.rememberAsyncImagePainter
import com.capstone.dauruang.R
import com.capstone.dauruang.data.network.request.OrderTransactionRequest
import com.capstone.dauruang.ui.ViewModelFactory
import com.capstone.dauruang.ui.components.button.ButtonSummaryPrimary
import com.capstone.dauruang.ui.components.button.ButtonSummarySecondary
import com.capstone.dauruang.ui.components.content.TitlePage
import com.capstone.dauruang.ui.components.modal.ModalFailed
import com.capstone.dauruang.ui.components.modal.ModalSuccess
import com.capstone.dauruang.ui.screen.scan.ScanActivity
import com.capstone.dauruang.ui.theme.DauRuangTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

class TransactionSummaryActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser

    private val viewModel: TransactionViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    private var imageUri: Uri? = null
    private var label: String? = null


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        val name = firebaseUser.displayName

        intent?.let {
            imageUri = it.getParcelableExtra("imageUri")
            label = it.getStringExtra("label")
        }

        setContent {

            var nama_sampah by remember { mutableStateOf("") }
            var alamat_penjemputan by remember { mutableStateOf("") }
            var catatan by remember { mutableStateOf("") }

            // Kategori
            var expanded by remember { mutableStateOf(false) }
            val suggestions = listOf("Logam", "Kertas", "Minyak", "Organik")
            var selectedText by remember { mutableStateOf(label.toString()) }
            var textfieldSize by remember { mutableStateOf(suggestions[0]) }

            // Berat
            var expandedBerat by remember { mutableStateOf(false) }
            val suggestionsBerat = listOf(1, 2, 3, 4, 5)
            var selectedBerat by remember { mutableIntStateOf(0) }
            var textfieldSizeBerat by remember { mutableStateOf(suggestions[0]) }
            var totalHarga by remember { mutableIntStateOf(selectedBerat*7500) }

            // Lokasi Daur Ulang
            var expandedLokasi by remember { mutableStateOf(false) }
            val suggestionsLokasi = listOf("Kebon Jeruk", "Tebet", "Setiabudi", "Cempaka Putih", "Kelapa Gading")
            var selectedTextLokasi by remember { mutableStateOf(suggestionsLokasi[0]) }
            var textfieldSizeLokasi by remember { mutableStateOf(suggestions[0]) }

            // Modal
            val showDialogState = remember { mutableStateOf(false) }

            val icon = if (expanded)
                Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
            else
                Icons.Filled.ArrowDropDown

            DauRuangTheme {
                Text(text = "Ini percobaan")
                Scaffold(
                    bottomBar = {
                        Column(modifier = Modifier
                            .padding(end = 12.dp, start = 12.dp, bottom = 12.dp)
                            .background(
                                Color.White
                            )) {
                            BottomBarSummary(
                                modifier = Modifier.padding(horizontal = 20.dp),
                                onClickBatalkan = {
                                    val intent = Intent(this@TransactionSummaryActivity, ScanActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                },
                                onClickJemputLimbah = {

                                    val isValid = name.toString().isNotEmpty() && selectedText.isNotEmpty() && selectedBerat.toString().isNotEmpty() && selectedTextLokasi.toString().isNotEmpty() && alamat_penjemputan.toString().isNotEmpty() && catatan.toString().isNotEmpty()

                                    if(isValid){
                                        val request = OrderTransactionRequest(
                                            username = name.toString(),
                                            jenis_sampah = selectedText,
                                            berat_sampah = selectedBerat,
                                            lokasi_pengepul = selectedTextLokasi,
                                            lokasi_user = alamat_penjemputan,
                                            catatan = catatan
                                        )
                                        viewModel.createOrder(request)
                                        showDialogState.value = true
                                        if (showDialogState.value) {
                                            setContent {
                                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                                    ModalSuccess(
                                                        durationMillis = 5000L, // Durasi modal 3 detik
                                                        onDismiss = { showDialogState.value = false }
                                                    )
                                                }
                                            }
                                        }
                                        CoroutineScope(Dispatchers.Main).launch {
                                            delay(3000) // Menunda selama 5 detik
                                            val intent = Intent(this@TransactionSummaryActivity, TransactionActivity::class.java)
                                            startActivity(intent)
                                            finish()
                                        }
                                    } else if(!isValid) {
                                        showDialogState.value = true
                                        if (showDialogState.value) {
                                            setContent {
                                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                                    ModalFailed(
                                                        durationMillis = 3000L, // Durasi modal 3 detik
                                                        onDismiss = { showDialogState.value = false }
                                                    )
                                                }
                                            }
                                        }

                                        CoroutineScope(Dispatchers.Main).launch {
                                            delay(3000) // Menunda selama 5 detik
                                            finish()
                                        }
                                    }
                                    observeOrderResult()
                                    observeErrorMessage()
                                }
                            )
                        }
                    },
                    topBar = {
                        TitlePage(
                            navigateBack = {
                                onBackPressed()
                            },
                            title = "Summary Limbah Sampah",
                            modifier = Modifier.padding(
                                end = 16.dp,
                                start = 16.dp,
                                bottom = 12.dp,
                                top = 8.dp
                            )
                        )
                    },
                    content = { paddingValues ->
                        Column(
                            modifier = Modifier
                                .padding(paddingValues)
                                .background(Color.White)
                        )
                        {
                            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                                Column(
                                    modifier = Modifier
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    if (imageUri != null) {
//                                    Toast.makeText(this@TransactionSummaryActivity, imageUri.toString(), Toast.LENGTH_LONG).show()
                                        Image(
                                            painter = rememberAsyncImagePainter(imageUri),
                                            contentDescription = "ini gambar",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(139.dp)
                                                .clip(RectangleShape)
                                        )
                                    }
                                    SpacerCustom()
                                    CustomTFSummary(
                                        modifier = Modifier.fillMaxWidth(),
                                        label = "Nama Sampah",
                                        value = nama_sampah,
                                        onValueChange = { newValue ->
                                            nama_sampah = newValue
                                        }
                                    )
                                    SpacerCustom()
                                    Row(
                                        modifier = Modifier,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(end = 2.dp)
                                                .weight(1f)
                                        ) {
//                                            selectedOptionText.let { Text(text = it, modifier = Modifier.padding(16.dp)) }
                                            Box() {
                                                OutlinedTextField(
                                                    readOnly = true,
                                                    value = selectedText,
                                                    onValueChange = { selectedText = it },
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .onGloballyPositioned { coordinates ->
                                                            textfieldSize =
                                                                coordinates.size
                                                                    .toSize()
                                                                    .toString()
                                                        },
                                                    label = { Text("Kategori") },
                                                    trailingIcon = {
                                                        Icon(icon, "contentDescription",
                                                            Modifier.clickable {
                                                                expanded = !expanded
                                                            })
                                                    },
                                                    shape = RoundedCornerShape(12.dp)
                                                )
                                                DropdownMenu(
                                                    expanded = expanded,
                                                    onDismissRequest = { expanded = false },
                                                    modifier = Modifier.fillMaxWidth()
                                                ) {
                                                    suggestions.forEach { label ->
                                                        DropdownMenuItem(
                                                            text = { Text(text = label) },
                                                            onClick = {
                                                                selectedText = label
                                                                expanded = false
                                                            }
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 2.dp)
                                                .weight(1f)
                                        ) {
                                            Box() {
                                                OutlinedTextField(
                                                    readOnly = true,
                                                    value = selectedBerat.toString(),
                                                    onValueChange = { selectedBerat = it.toInt() },
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .onGloballyPositioned { coordinates ->
                                                            textfieldSizeBerat =
                                                                coordinates.size
                                                                    .toSize()
                                                                    .toString()
                                                        },
                                                    label = { Text("Estimasi Berat") },
                                                    trailingIcon = {
                                                        Icon(icon, "contentDescription",
                                                            Modifier.clickable {
                                                                expandedBerat = !expandedBerat
                                                            })
                                                    },
                                                    shape = RoundedCornerShape(12.dp)
                                                )
                                                DropdownMenu(
                                                    expanded = expandedBerat,
                                                    onDismissRequest = { expandedBerat = false },
                                                    modifier = Modifier.fillMaxWidth()
                                                ) {
                                                    suggestionsBerat.forEach { option ->
                                                        DropdownMenuItem(
                                                            text = { Text(text = "${option.toString()} Kg") },
                                                            onClick = {
                                                                selectedBerat = option
                                                                expandedBerat = false
                                                            }
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    SpacerCustom()
                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        Column(modifier = Modifier) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Text(
                                                    text = nama_sampah,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Light,
                                                    modifier = Modifier.width(160.dp),
                                                    color = colorResource(R.color.text_secondary)
                                                )
                                                Text(
                                                    text = selectedBerat.toString() + " Kg",
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Light,
                                                    color = colorResource(R.color.text_secondary)
                                                )
                                                Text(
                                                    text = "@ Rp. 7.500",
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Light,
                                                    color = colorResource(R.color.text_secondary)
                                                )
                                                Text(
                                                    text = "Rp. " + "${selectedBerat*7500}",
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Light,
                                                    color = colorResource(R.color.text_secondary)
                                                )
                                            }

                                            Divider(
                                                thickness = 1.dp,
                                                color = colorResource(R.color.text_primary),
                                                modifier = Modifier
                                                    .alpha(0.8f)
                                                    .padding(vertical = 4.dp)
                                                    .fillMaxWidth()
                                            )

                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Text(
                                                    text = "Total",
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = colorResource(R.color.text_primary),
                                                )
                                                Text(
                                                    text = "Rp. " + "${selectedBerat*7500}",
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = colorResource(R.color.text_primary),
                                                )
                                            }
                                        }
                                    }
                                    SpacerCustom()
                                    Column(modifier = Modifier.fillMaxWidth()) {
                                        Box() {
                                            OutlinedTextField(
                                                readOnly = true,
                                                value = selectedTextLokasi,
                                                onValueChange = { selectedTextLokasi = it },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .onGloballyPositioned { coordinates ->
                                                        textfieldSize =
                                                            coordinates.size
                                                                .toSize()
                                                                .toString()
                                                    },
                                                label = { Text("Tempat Daur Ulang") },
                                                trailingIcon = {
                                                    Icon(icon, "contentDescription",
                                                        Modifier.clickable {
                                                            expandedLokasi = !expandedLokasi
                                                        }
                                                    )
                                                },
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            DropdownMenu(
                                                expanded = expandedLokasi,
                                                onDismissRequest = { expandedLokasi = false },
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                suggestionsLokasi.forEach { label ->
                                                    DropdownMenuItem(
                                                        text = { Text(text = label) },
                                                        onClick = {
                                                            selectedTextLokasi = label
                                                            expandedLokasi = false
                                                        }
                                                    )
                                                }
                                            }
                                        }
                                    }
                                    SpacerCustom()
                                    CustomTFSummary(
                                        modifier = Modifier.fillMaxWidth(),
                                        label = "Alamat Penjemputan",
                                        value = alamat_penjemputan,
                                        onValueChange = { newValue ->
                                            alamat_penjemputan = newValue
                                        }
                                    )
                                    SpacerCustom()
                                    CustomTFSummary(
                                        modifier = Modifier.fillMaxWidth(),
                                        label = "Catatan",
                                        value = catatan,
                                        onValueChange = { newValue ->
                                            catatan = newValue
                                        }
                                    )
                                    SpacerCustom()
                                }
                            }
                        }
                    }
                )
            }
        }

    }

    companion object {

        private const val EXTRA_PHOTO_URI = "photo_uri"

        fun newIntent(context: Context, photoUri: Uri): Intent {
            return Intent(context, TransactionSummaryActivity::class.java).apply {
                putExtra(EXTRA_PHOTO_URI, photoUri.toString())
            }
        }
    }

    private fun uriToBitmap(uri: Uri): Bitmap? {
        return try {
            val inputStream = contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun observeOrderResult() {
        viewModel.orderResultTransaction.observe(this) { orderData ->
            // Handle order data result
            setContent {
            }
        }
    }

    private fun observeErrorMessage() {
        viewModel.errorMessage.observe(this) { errorMessage ->
            setContent {

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTFSummary(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    label: String = "",
) {
    val borderColor =
        if (isSystemInDarkTheme()) Color.White else colorResource(R.color.text_primary)
    val textColor = if (isSystemInDarkTheme()) Color.White else colorResource(R.color.text_primary)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(text = label) },
        shape = RoundedCornerShape(12.dp)
    )
}


@Composable
fun SpacerCustom() {
    Spacer(
        modifier = Modifier
            .padding(vertical = 4.dp)
    )
}

@Composable
fun BottomBarSummary(
    onClickBatalkan: () -> Unit,
    onClickJemputLimbah: () -> Unit,
    modifier: Modifier = Modifier
        .padding(horizontal = 16.dp)
        .height(100.dp)
        .fillMaxWidth()
        .shadow(4.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))

) {
    Row(modifier = Modifier) {
        ButtonSummarySecondary(
            eventClick = onClickBatalkan,
            height = 48.dp,
            modifier = Modifier.weight(1f)
        )
        ButtonSummaryPrimary(
            eventClick = onClickJemputLimbah,
            height = 48.dp,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun SummaryPreview() {
    Column() {
        BottomBarSummary(
            onClickBatalkan = {},
            onClickJemputLimbah = {}
        )
    }
}