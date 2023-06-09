package com.capstone.dauruang.data.dummy

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.capstone.dauruang.model.History
import com.capstone.dauruang.model.Location
import com.capstone.dauruang.model.Transaction
import java.util.Date

class TransactionDummyProvider : PreviewParameterProvider<Transaction> {

    val locationUser1 = Location("lat1", "lon1")
    val locationUser2 = Location("lat2", "lon2")
    val locationDaurulang1 = Location("lat3", "lon3")
    val locationDaurulang2 = Location("lat4", "lon4")

    val currentDate = Date()

    override val values: Sequence<Transaction>
        get() = sequenceOf(
            Transaction(
                id = 1,
                weight = 2,
                point = 12,
                price = "25000",
                status = "selesai",
                loc_user = listOf(locationUser1, locationUser2),
                loc_daurulang = listOf(locationDaurulang1, locationDaurulang2),
                date = currentDate,
                note = "This is a dummy transaction"
            ),
            Transaction(
                id = 2,
                weight = 2,
                point = 12,
                price = "35000",
                status = "selesai",
                loc_user = listOf(locationUser1, locationUser2),
                loc_daurulang = listOf(locationDaurulang1, locationDaurulang2),
                date = currentDate,
                note = "This is a dummy transaction"
            ),
            Transaction(
                id = 3,
                weight = 2,
                point = 12,
                price = "25000",
                status = "selesai",
                loc_user = listOf(locationUser1, locationUser2),
                loc_daurulang = listOf(locationDaurulang1, locationDaurulang2),
                date = currentDate,
                note = "This is a dummy transaction"
            ),
            Transaction(
                id = 4,
                weight = 2,
                point = 12,
                price = "25000",
                status = "selesai",
                loc_user = listOf(locationUser1, locationUser2),
                loc_daurulang = listOf(locationDaurulang1, locationDaurulang2),
                date = currentDate,
                note = "This is a dummy transaction"
            ),
            Transaction(
                id = 5,
                weight = 2,
                point = 12,
                price = "25000",
                status = "selesai",
                loc_user = listOf(locationUser1, locationUser2),
                loc_daurulang = listOf(locationDaurulang1, locationDaurulang2),
                date = currentDate,
                note = "This is a dummy transaction"
            )
        )
}

object TransactionDataProvider {

    val locationUser1 = Location("lat1", "lon1")
    val locationUser2 = Location("lat2", "lon2")
    val locationDaurulang1 = Location("lat3", "lon3")
    val locationDaurulang2 = Location("lat4", "lon4")
    val currentDate = Date()

    val transaction = mutableListOf(
        Transaction(
            id = 5,
            weight = 2,
            point = 12,
            price = "25000",
            status = "selesai",
            loc_user = listOf(locationUser1, locationUser2),
            loc_daurulang = listOf(locationDaurulang1, locationDaurulang2),
            date = currentDate,
            note = "This is a dummy transaction"
        ),
        Transaction(
            id = 6,
            weight = 2,
            point = 13,
            price = "25000",
            status = "selesai",
            loc_user = listOf(locationUser1, locationUser2),
            loc_daurulang = listOf(locationDaurulang1, locationDaurulang2),
            date = currentDate,
            note = "This is a dummy transaction"
        ),
        Transaction(
            id = 7,
            weight = 2,
            point = 14,
            price = "25000",
            status = "selesai",
            loc_user = listOf(locationUser1, locationUser2),
            loc_daurulang = listOf(locationDaurulang1, locationDaurulang2),
            date = currentDate,
            note = "This is a dummy transaction"
        ),
        Transaction(
            id = 8,
            weight = 2,
            point = 12,
            price = "25000",
            status = "selesai",
            loc_user = listOf(locationUser1, locationUser2),
            loc_daurulang = listOf(locationDaurulang1, locationDaurulang2),
            date = currentDate,
            note = "This is a dummy transaction"
        ),
    )
}