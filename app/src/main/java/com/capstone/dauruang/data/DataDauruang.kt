package com.capstone.dauruang.data

import com.capstone.dauruang.R
import com.capstone.dauruang.model.ItemTrash
import com.capstone.dauruang.model.TabMenu

object DataDauruang {

    val tabMenu = listOf(
        TabMenu(
            id = 1,
            title = "Botol"
        ),
        TabMenu(
            id = 2,
            title = "Plastik"
        ),
        TabMenu(
            id = 3,
            title = "Kaca"
        ),
        TabMenu(
            id = 4,
            title = "Kertas"
        )
    )

    val botolMenu = listOf(
        ItemTrash(
            id = 1,
            title = "Image botol 1",
            description = "null",
            imageId =  R.drawable.botol_1,
            source = "app source"
        ),
        ItemTrash(
            id = 2,
            title = "Image botol 2",
            description = "null",
            imageId =  R.drawable.botol_2,
            source = "app source"
        ),
        ItemTrash(
            id = 3,
            title = "Image botol 3",
            description = "null",
            imageId =  R.drawable.botol_3,
            source = "app source"
        ),
    )

    val plasticMenu = listOf(
        ItemTrash(
            id = 1,
            title = "Image plastic 1",
            description = "null",
            imageId =  R.drawable.plastic_1,
            source = "app source"
        ),
        ItemTrash(
            id = 2,
            title = "Image botol 2",
            description = "null",
            imageId =  R.drawable.plastic_2,
            source = "app source"
        ),
        ItemTrash(
            id = 3,
            title = "Image botol 3",
            description = "null",
            imageId =  R.drawable.plastic_3,
            source = "app source"
        ),
    )

    val kacaMenu = listOf(
        ItemTrash(
            id = 1,
            title = "Image kaca 1",
            description = "null",
            imageId =  R.drawable.kaca_1,
            source = "app source"
        )
    )

    val kertasMenu = listOf(
        ItemTrash(
            id = 1,
            title = "Image kertas 1",
            description = "null",
            imageId =  R.drawable.kertas_1,
            source = "app source"
        ),
        ItemTrash(
            id = 1,
            title = "Image kertas 2",
            description = "null",
            imageId =  R.drawable.kertas_2,
            source = "app source"
        )
    )
}