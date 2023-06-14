package com.capstone.dauruang.ui.screen.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import com.capstone.dauruang.R
import com.capstone.dauruang.data.DataDauruang
import com.capstone.dauruang.model.MapState
import com.capstone.dauruang.model.ZoneClusterManager
import com.capstone.dauruang.ui.components.content.TypeTrashCard
import com.capstone.dauruang.ui.nav.BottomNavMainType
import com.capstone.dauruang.ui.screen.history.HistoryActivity
import com.capstone.dauruang.ui.screen.profile.ProfileActivity
import com.capstone.dauruang.ui.screen.scan.ScanActivity
import com.capstone.dauruang.ui.screen.transaction.TransactionActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    context: Context,
    modifier: Modifier = Modifier
        .background(Color.White),
    name: String?,
    photo: Uri?,
    state: MapState,
    setupClusterManager: (Context, GoogleMap) -> ZoneClusterManager,
    calculateZoneViewCenter: () -> LatLngBounds,
) {
    val homeScreenState = rememberSaveable { mutableStateOf(BottomNavMainType.HOME) }

    Scaffold(
        bottomBar = {
            BottomBar(
                homScreenState = homeScreenState,
                context = context
            )
        },
        content = { paddingValue ->
            Column(
                modifier = modifier
                    .padding(paddingValue)
                    .fillMaxSize()
            ) {
                //INclude Tab Sampah
                HeaderContent(context = context, name = name, photo = photo)

                // Bottom NavBar
                Column(
                    verticalArrangement = Arrangement.Bottom
                ) {
                    MapsView(
                        state = state,
                        setupClusterManager = setupClusterManager,
                        calculateZoneViewCenter = calculateZoneViewCenter
                    )
                }
            }
        }
    )
}

@Composable
fun HeaderContent(
    context: Context,
    modifier: Modifier = Modifier,
    name: String?,
    photo: Uri?
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
                                text = name.toString(),
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
                                painter = if(photo != null) rememberAsyncImagePainter(photo) else painterResource(
                                    R.drawable.image_default
                                ),
                                contentDescription = "profile_photo",
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                                    .clip(shape = RoundedCornerShape(100.dp))
                                    .clickable {
                                        context.startActivity(
                                            ProfileActivity.newIntent(
                                                context
                                            )
                                        )
                                    }
                            )
                            Icon(
                                imageVector = Icons.Outlined.History,
                                contentDescription = "history_icon",
                                modifier = modifier
                                    .size(28.dp)
                                    .clickable {
                                        context.startActivity(
                                            HistoryActivity.newIntent(
                                                context
                                            )
                                        )
                                    },
                                tint = colorResource(R.color.green_primary),
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
fun CustomMenuButtonMaps(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier
        .padding(end = 12.dp)
){

    Surface(
        color = when {
            selected -> colorResource(R.color.green_primary).copy(alpha = 0.9f)
            else -> colorResource(R.color.green_transparant)
        },
        contentColor = when {
            selected -> Color.White
            else -> colorResource(R.color.green_primary)
        },
        shape = RoundedCornerShape(100.dp),
        modifier = modifier
            .width(93.dp)
            .height(37.dp)
            .graphicsLayer(alpha = 0.8f)
            .border(
                2.dp,
                colorResource(R.color.green_primary),
                shape = RoundedCornerShape(100.dp)
            )
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
fun MapsView(
    state: MapState,
    setupClusterManager: (Context, GoogleMap) -> ZoneClusterManager,
    calculateZoneViewCenter: () -> LatLngBounds,
) {
    val context = LocalContext.current
    val mapView = rememberMapView()

    var latitude by remember { mutableDoubleStateOf(49.110) }
    var longitude by remember { mutableDoubleStateOf(-122.554) }

    val tabMenu = remember { DataDauruang.tabMenu.filter { it.id > 0 } }
    val selectedIndex = remember { mutableIntStateOf(0) }

    val boxModifier = Modifier
        .fillMaxWidth()

    // SetUp Maps
    val mapProperties = MapProperties(
        isMyLocationEnabled = state.lastKnownLocation != null,
    )
    val cameraPositionState = rememberCameraPositionState()

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Recycler Center",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = colorResource(R.color.text_primary),
            modifier = Modifier
                .padding(top = 12.dp, bottom = 8.dp)
        )
        Box(
            modifier = boxModifier
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                .clip(shape = RoundedCornerShape(12.dp))
                .border(
                    3.dp,
                    colorResource(R.color.green_primary).copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            GoogleMap(
                modifier = Modifier
                        .fillMaxSize(),
                properties = mapProperties,
                cameraPositionState = cameraPositionState
            ) {

                val context = LocalContext.current
                val scope = rememberCoroutineScope()
                MapEffect(state.clusterItems) { map ->
                    if (state.clusterItems.isNotEmpty()) {
                        val clusterManager = setupClusterManager(context, map)
                        map.setOnCameraIdleListener(clusterManager)
                        map.setOnMarkerClickListener(clusterManager)
                        state.clusterItems.forEach { clusterItem ->
                            map.addPolygon(clusterItem.polygonOptions)
                        }
                        map.setOnMapLoadedCallback {
                            if (state.clusterItems.isNotEmpty()) {
                                scope.launch {
                                    cameraPositionState.animate(
                                        update = CameraUpdateFactory.newLatLngBounds(
                                            calculateZoneViewCenter(),
                                            0
                                        ),
                                    )
                                }
                            }
                        }
                    }
                }

                MarkerInfoWindow(
                    state = rememberMarkerState(position = LatLng(latitude, latitude)),
                    snippet = "Some stuff",
                    onClick = {
                        // This won't work :(
                        System.out.println("Mitchs_: Cannot be clicked")
                        true
                    },
                    draggable = true,

//                    state = rememberMarkerState(position = LatLng(49.1, -122.5)),

                )

            }

            // Tab Menu Maps
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ){
                ScrollableTabRow(
                    selectedTabIndex = selectedIndex.value,
                    divider = {},
                    edgePadding = 4.dp,
                    indicator = noIndicator,
                    containerColor = Color.Transparent,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 8.dp, vertical = 12.dp),
                ) {
                    tabMenu.forEachIndexed { index, tabMenu ->
                        Tab(
                            selected = index == selectedIndex.value,
                            onClick = {
                                selectedIndex.value = index
                                latitude = tabMenu.latitude
                                longitude = tabMenu.longtitude
                                Toast.makeText(context, "${latitude} ${longitude}", Toast.LENGTH_LONG).show()
                            },
                        ) {
                            CustomMenuButtonMaps(
                                text = tabMenu.title,
                                selected = index == selectedIndex.value,
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun rememberMapView(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }
    return mapView
}


// Bottom Bar
@Composable
fun BottomBar(
    modifier: Modifier = Modifier
        .background(Color.White),
    context: Context,
    homScreenState: MutableState<BottomNavMainType>,
) {
    var animate by remember { mutableStateOf(false) }
    val selectedIndex = remember { mutableStateOf(0) }

    NavigationBar(
        modifier = modifier,
        containerColor = Color.White
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = colorResource(R.color.green_primary),
                    modifier = Modifier
                        .size(32.dp)
                )
            },
            selected = homScreenState.value == BottomNavMainType.HOME,
            onClick = {
                homScreenState.value = BottomNavMainType.HOME
                animate = false
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.Transparent,
                indicatorColor = Color.White
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.icon_scan),
                    contentDescription = "Scan",
                    tint = Color.White,
                    modifier = Modifier
                        .size(36.dp)
                )
            },
            selected = homScreenState.value == BottomNavMainType.SCAN,
            onClick = {
                context.startActivity(ScanActivity.newIntent(context))

                homScreenState.value = BottomNavMainType.SCAN
                animate = false
            },
            modifier = Modifier
                .padding(12.dp)
                .background(
                    color = colorResource(R.color.green_primary),
                    shape = RoundedCornerShape(50.dp)
                ),
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = colorResource(R.color.green_primary),
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.Transparent,
                indicatorColor = colorResource(R.color.green_primary)
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.icon_transaction),
                    contentDescription = "Scan",
                    tint = colorResource(R.color.green_primary),
                    modifier = Modifier
                        .size(30.dp)
                )
            },
            selected = homScreenState.value == BottomNavMainType.TRANSACTION,
            onClick = {
                context.startActivity(TransactionActivity.newIntent(context))

                homScreenState.value = BottomNavMainType.TRANSACTION
                animate = false
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.Transparent,
                indicatorColor = Color.White
            )
        )
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun HomeScreenPreview() {
//    val context = LocalContext.current
//    HomeScreen()
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun MapViewPreview() {
//    MapsView()
}

@Preview(showBackground = true)
@Composable
fun TabLayoutTrashPreview() {

    val homeScreenState = rememberSaveable { mutableStateOf(BottomNavMainType.HOME) }
    val context = LocalContext.current

    Column() {
        CustomMenuTabs(
            text = "Botol",
            selected = false
        )
        TabLayoutTrash()
        CustomMenuButtonMaps(
            text = "Botol",
            selected = true
        )
        BottomBar(
            homScreenState = homeScreenState,
            context = context
        )
    }

}