package com.capstone.dauruang.model

import androidx.annotation.DrawableRes

data class Trash(
    val id: Int,
    val title: String,
    val description: String,
    @DrawableRes val imageId: Int,
    val source: String = "demo source"
)