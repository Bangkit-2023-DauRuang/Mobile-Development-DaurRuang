package com.capstone.dauruang.ui.components.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.capstone.dauruang.data.dummy.TransactionDummyProvider
import com.capstone.dauruang.model.Transaction

@Composable
fun Transaction(

) {

}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun TransactionPreview(@PreviewParameter(TransactionDummyProvider::class) data: Transaction){

}



