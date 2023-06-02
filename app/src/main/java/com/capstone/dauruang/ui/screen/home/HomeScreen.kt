package com.capstone.dauruang.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.dauruang.R
import com.capstone.dauruang.data.DataDauruang
import com.capstone.dauruang.ui.components.content.TypeTrashCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
        .background(Color.White)

) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        HeaderContent()

        // TabLayout Sampah ad Map

    }

}

@Composable
fun HeaderContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column() {
            Row(
                modifier = Modifier
                    .height(126.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .width(80.dp)
                        .background(
                            shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
                            color = colorResource(R.color.green_primary)
                        ),
                ) {
                    Image(
                        painter = painterResource(R.drawable.whitelogo_dauruang),
                        contentDescription = "logo_dauruang_landscape",
                        modifier = Modifier
                            .rotate(90f)
                            .height(124.dp)

                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .padding(end = 8.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier
                                .padding(end = 12.dp)
                        ) {
                            Text(
                                text = "David Nasrulloh",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                            Text(
                                text = "12 Point",
                                fontSize = 12.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier
                                    .padding(top = 4.dp)
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.david),
                                contentDescription = "profile_photo",
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                                    .clickable {}
                            )
                            Icon(
                                imageVector = Icons.Outlined.History,
                                contentDescription = "history_icon",
                                modifier = modifier
                                    .size(28.dp),
                                tint = colorResource(R.color.green_primary)
                            )

                        }
                    }
                    Box(
                        modifier = Modifier
                            .size(width = 20.dp, height = 126.dp)
                            .background(color = colorResource(R.color.green_primary))
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            TabLayoutTrash()
        }
    }
}

private val noIndicator: @Composable (List<TabPosition>) -> Unit = {}

@Composable
fun TabLayoutTrash() {
    val tabMenu = remember { DataDauruang.tabMenu.filter { it.id > 0 } }
    val selectedIndex = remember { mutableStateOf(0) }

    val botolMenu = remember { DataDauruang.botolMenu.filter { it.id > 0 } }
    val plasticMenu = remember { DataDauruang.plasticMenu.filter { it.id > 0 } }
    val kacaMenu = remember { DataDauruang.kacaMenu.filter { it.id > 0 } }
    val kertasMenu = remember { DataDauruang.kertasMenu.filter { it.id > 0 } }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedIndex.value,
            divider = {},
            edgePadding = 12.dp,
            indicator = noIndicator,
            containerColor = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            tabMenu.forEachIndexed { index, tabMenu ->
                Tab(
                    selected = index == selectedIndex.value,
                    onClick = {
                        selectedIndex.value = index
                    }
                ) {
                    CustomMenuTabs(
                        text = tabMenu.title,
                        selected = index == selectedIndex.value,
                    )
                }
            }
        }

        if (selectedIndex.value == 0) {
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(botolMenu) { item ->
                    TypeTrashCard(
                        imageUrl = item.imageId,
                        title = item.title,
                        modifier = Modifier
                            .size(112.dp)
                    )
                    Spacer(modifier = Modifier.padding(end = 12.dp))
                }
            }
        }

        if (selectedIndex.value == 1) {
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(plasticMenu) { item ->
                    TypeTrashCard(
                        imageUrl = item.imageId,
                        title = item.title,
                        modifier = Modifier
                            .size(112.dp)
                    )
                    Spacer(modifier = Modifier.padding(end = 12.dp))
                }
            }
        }

        if (selectedIndex.value == 2) {
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(kacaMenu) { item ->
                    TypeTrashCard(
                        imageUrl = item.imageId,
                        title = item.title,
                        modifier = Modifier
                            .size(112.dp)
                    )
                    Spacer(modifier = Modifier.padding(end = 12.dp))
                }
            }
        }

        if (selectedIndex.value == 3) {
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(kertasMenu) { item ->
                    TypeTrashCard(
                        imageUrl = item.imageId,
                        title = item.title,
                        modifier = Modifier
                            .size(112.dp)
                    )
                    Spacer(modifier = Modifier.padding(end = 12.dp))
                }
            }
        }

    }

}

@Composable
fun CustomMenuTabs(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier
        .padding(end = 12.dp)
) {
    Surface(
        color = when {
            selected -> colorResource(R.color.green_primary)
            else -> colorResource(R.color.green_transparant)
        },
        contentColor = when {
            selected -> Color.White
            else -> colorResource(R.color.green_primary)
        },
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .width(112.dp)
            .height(if (selected) 56.dp else 40.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = text, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun MapsView() {

}

@Composable
fun BottomBar() {

}


@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Preview(showBackground = true)
@Composable
fun TabLayoutTrashPreview() {
    CustomMenuTabs(
        text = "Botol",
        selected = false
    )
    TabLayoutTrash()
}