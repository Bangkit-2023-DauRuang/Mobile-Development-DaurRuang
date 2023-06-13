package com.capstone.dauruang.ui.components.content

import android.text.TextUtils.substring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PunchClock
import androidx.compose.material.icons.filled.SyncLock
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R
import com.capstone.dauruang.data.dummy.TransactionDummyProvider
import com.capstone.dauruang.data.network.response.Orders
import com.capstone.dauruang.model.Transaction
import com.capstone.dauruang.ui.components.button.ButtonLargePrimary
import com.capstone.dauruang.ui.components.button.ButtonSmall

@Composable
fun TransactionItem(
    order: Orders,
    modifier: Modifier = Modifier
        .padding(top = 8.dp)
        .background(Color.White)
        .fillMaxWidth()
) {
    val originalString = order.createdAt.toString()
    val dateString = originalString.substring(8, 10) + "-" + originalString.substring(5, 7) + "-" + originalString.substring(0, 4)

    Column(modifier = modifier.padding(vertical = 6.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column() {
                Text(
                    text = "Limbah ${order.jenis_sampah}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Row(
                    modifier = Modifier
                        .padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Text(text = "Total estimasi berat")
                        Text(text = "Poin")
                        Text(text = "Harga", fontWeight = FontWeight.SemiBold)
                    }
                    Column() {
                        Text(text = ": ${order.berat_sampah} Kg")
                        Text(text = ": ${order.points} Point")
                        Text(text = ": Rp. ${order.hargaPerKg}", fontWeight = FontWeight.SemiBold)
                    }
                }
            }
            Column(modifier = Modifier.width(100.dp), horizontalAlignment = Alignment.End) {
                Text(
                    text = dateString,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 4.dp)
                )
                if (order.status_pemesanan.toString() == "Selesai") {
                    Row(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(4.dp))
                            .background(colorResource(R.color.green_primary))
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "done_icons",
                            tint = colorResource(R.color.green_primary),
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(100.dp))
                                .background(
                                    Color.White
                                )
                                .size(12.dp)
                                .padding(1.dp)
                        )
                        Text(
                            text = "selesai", fontSize = 10.sp,
                            modifier = Modifier
                                .padding(start = 4.dp),
                            color = Color.White
                        )
                    }
                }

                if (order.status_pemesanan.toString() == "Diproses") {
                    Row(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(4.dp))
                            .background(colorResource(R.color.orange_primary))
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AccessTime,
                            contentDescription = "done_icons",
                            tint = Color.White,
                            modifier = Modifier
                                .size(14.dp)
                        )
                        Text(
                            text = "diproses", fontSize = 10.sp,
                            modifier = Modifier
                                .padding(start = 2.dp),
                            color = Color.White
                        )
                    }
                }

                if (order.status_pemesanan.toString() == "Pengecekan") {
                    Row(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(4.dp))
                            .background(colorResource(R.color.orange_primary))
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AccessTime,
                            contentDescription = "done_icons",
                            tint = Color.White,
                            modifier = Modifier
                                .size(14.dp)
                        )
                        Text(
                            text = "pengecekan", fontSize = 10.sp,
                            modifier = Modifier
                                .padding(start = 2.dp),
                            color = Color.White
                        )
                    }
                }

                if (order.status_pemesanan.toString() == "Dibatalkan") {
                    Row(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(4.dp))
                            .background(Color.Red)
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "close_icons",
                            tint = Color.White,
                            modifier = Modifier
                                .size(14.dp)
                        )
                        Text(
                            text = "dibatalkan", fontSize = 10.sp,
                            modifier = Modifier
                                .padding(start = 2.dp),
                            color = Color.White
                        )
                    }
                }

            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = modifier) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier) {
                        Text(text = "Lokasi")
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.LocationOn,
                                contentDescription = "location_icon",
                                tint = Color.Red,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(end = 4.dp)
                            )
                            order.lokasi_user?.let {
                                Text(
                                    text = it,
                                    color = Color.Red,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                    Column(modifier = Modifier.align(Alignment.Bottom)) {
                        Text(
                            text = "view in map",
                            fontWeight = FontWeight.Light,
                            modifier = Modifier
                                .clickable {}
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier) {
                        Text(text = "Lokasi")
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.LocationOn,
                                contentDescription = "location_icon",
                                tint = Color.Red,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(end = 4.dp)
                            )
                            order.lokasi_pengepul?.let {
                                Text(
                                    text = it,
                                    color = Color.Red,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                    Column(modifier = Modifier.align(Alignment.Bottom)) {
                        Text(
                            text = "view in map",
                            fontWeight = FontWeight.Light,
                            modifier = Modifier
                                .clickable {}
                        )
                    }
                }
            }
        }
        Row(modifier = Modifier.padding(bottom = 12.dp)) {
            Column() {
                Text(text = "Catatan :")
                order.catatan?.let { Text(text = it) }
            }
        }
        if(order.status_pemesanan.toString() == "Pengecekan"){
            Row(
                modifier = Modifier
                    .padding(bottom = 12.dp, top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "menu",
                    tint = colorResource(R.color.green_primary),
                    modifier = Modifier
                        .border(
                            border = BorderStroke(2.dp, colorResource(R.color.green_primary)),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(7.dp)
                )
                TerimaUangButton(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth(),
                    onClick = {}
                )
            }
        }
        Divider(
            thickness = 1.dp,
            color = colorResource(R.color.green_primary),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun TerimaUangButton(
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    onClick: () -> Unit,
){
    Row(
        modifier = modifier
            .clickable { onClick }
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(R.color.green_primary))
        ,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Terima Uang",
            modifier = Modifier
                .padding(horizontal = 12.dp , vertical = 10.dp),
            color = Color.White
        )
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun TransactionPreview() {
    val data = Orders(
        id = 1, username = "Rahmawati", jenis_sampah = "Minyak jelantah", hargaPerKg = 9500,
        berat_sampah = 10,
        points = 10,
        lokasi_pengepul = "Kalimalang", lokasi_user = "Pondok kelapa",
        catatan = null,
        status_pemesanan = "Pengecekan",
        createdAt = "2023-06-11T06:50:38.000Z",
        updatedAt = "2023-06-11T07:38:50.000Z"
    )

    TransactionItem(data)

}


//@Composable
//fun OrderItem(order: Orders) {
//    // Tampilkan informasi order
//    Text("Order ID: ${order.id}")
//    Text("Username: ${order.username}")
//    Text("Jenis Sampah: ${order.jenis_sampah}")
//    // Tambahkan informasi lainnya sesuai kebutuhan
//}


