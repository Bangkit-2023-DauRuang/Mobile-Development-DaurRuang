package com.capstone.dauruang.data

import com.capstone.dauruang.R
import com.capstone.dauruang.model.Trash
import com.capstone.dauruang.model.TabMenu

object DataDauruang {

    val tabMenu = listOf(
        TabMenu(
            id = 1,
            title = "Botol",
            latitude = -6.307143,
            longtitude = 106.692525
        ),
        TabMenu(
            id = 2,
            title = "Plastik",
            latitude = -4.307143,
            longtitude = 106.692525
        ),
        TabMenu(
            id = 3,
            title = "Kaca",
            latitude = -2.307143,
            longtitude = 104.692525
        ),
        TabMenu(
            id = 4,
            title = "Kertas",
            latitude = -7.307143,
            longtitude = 102.692525
        )
    )

    val botolMenu = listOf(
        Trash(
            id = 1,
            title = "Image botol 1",
            description = "null",
            imageId =  R.drawable.botol_1,
            source = "app source"
        ),
        Trash(
            id = 2,
            title = "Image botol 2",
            description = "null",
            imageId =  R.drawable.botol_2,
            source = "app source"
        ),
        Trash(
            id = 3,
            title = "Image botol 3",
            description = "null",
            imageId =  R.drawable.botol_3,
            source = "app source"
        ),
    )

    val plasticMenu = listOf(
        Trash(
            id = 1,
            title = "Image plastic 1",
            description = "null",
            imageId =  R.drawable.plastic_1,
            source = "app source"
        ),
        Trash(
            id = 2,
            title = "Image botol 2",
            description = "null",
            imageId =  R.drawable.plastic_2,
            source = "app source"
        ),
        Trash(
            id = 3,
            title = "Image botol 3",
            description = "null",
            imageId =  R.drawable.plastic_3,
            source = "app source"
        ),
    )

    val kacaMenu = listOf(
        Trash(
            id = 1,
            title = "Image kaca 1",
            description = "null",
            imageId =  R.drawable.kaca_1,
            source = "app source"
        )
    )

    val kertasMenu = listOf(
        Trash(
            id = 1,
            title = "Image kertas 1",
            description = "null",
            imageId =  R.drawable.kertas_1,
            source = "app source"
        ),
        Trash(
            id = 1,
            title = "Image kertas 2",
            description = "null",
            imageId =  R.drawable.kertas_2,
            source = "app source"
        )
    )
}