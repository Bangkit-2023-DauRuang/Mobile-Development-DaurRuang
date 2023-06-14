package com.capstone.dauruang.ui.components.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R
import kotlinx.coroutines.delay

@Composable
fun ModalSuccess(
    modifier: Modifier = Modifier
        .background(Color.Transparent),
    color: Color = colorResource(R.color.green_primary),

    durationMillis: Long,
    onDismiss: () -> Unit
) {

    val showModal = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Delay for the specified duration
        delay(durationMillis)
        // Dismiss the modal after the delay
        showModal.value = false
        onDismiss()
    }

    if (showModal.value) {
        Box(
            modifier = modifier.padding(top = 40.dp, start = 20.dp, end = 20.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Box(modifier = modifier
                .background(
                    color = colorResource(R.color.color_modal),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(start = 32.dp, end = 32.dp, top = 80.dp, bottom = 44.dp),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Transaksi berhasil di proses, lihat detail nya pada halaman transaksi",
                    modifier = Modifier,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = color,
                    textAlign = TextAlign.Center

                )
            }
            Box(
                modifier = Modifier
                    .padding(bottom = 100.dp)
                    .height(200.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Row(modifier = Modifier.background(color = Color.White, shape = RoundedCornerShape(100.dp)).padding(1.dp)){
                    Row(modifier = modifier.padding(4.dp).background(color = color, shape = RoundedCornerShape(100.dp))){
                        Icon(
                            imageVector = Icons.Rounded.Done ,
                            contentDescription = "done_modal_icon",
                            modifier = Modifier
                                .padding(16.dp)
                                .background(color = color, shape = RoundedCornerShape(100.dp))
                                .size(52.dp),
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }



}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun ModalSuccessPreview(){
//    ModalSuccess()
}
