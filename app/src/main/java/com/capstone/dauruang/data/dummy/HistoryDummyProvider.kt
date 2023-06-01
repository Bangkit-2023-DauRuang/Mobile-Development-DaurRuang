package com.capstone.dauruang.data.dummy

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.capstone.dauruang.model.History


class HistoryDummyProvider : PreviewParameterProvider<History> {
    override val values: Sequence<History>
        get() = sequenceOf(
            History(1, "Penjualan Sampah Berhasil", "20-06-2023", "16:03:23", "50000", 12),
            History(2, "Penjualan Sampah Berhasil", "20-06-2023", "16:03:23", "50000", 12),
            History(3, "Penukaran Poin", "20-06-2023", "16:03:23", null, 12),
            History(3, "Penukaran Poin", "20-06-2023", "16:03:23", null, 0)
        )
}

object HistoryDataProvider {
    val history = mutableListOf(
        History(
            id = 1,
            title = "Penjualan Sampah Berhasil",
            date = "20-06-2023",
            time = "16:03:23",
            money = "50000",
            point = 12
        ),

        History(
            id = 2,
            title = "Penjualan Sampah Berhasil",
            date = "20-06-2023",
            time = "16:03:23",
            money = "50000",
            point = 12),
        History(
            id = 3,
            title = "Penukaran Poin",
            date = "20-06-2023",
            time = "16:03:23",
            money = null,
            point = 12),
        History(
            id = 3,
            title = "Penukaran Poin",
            date = "20-06-2023",
            time = "16:03:23",
            money = null,
            point = 0)
    )
}