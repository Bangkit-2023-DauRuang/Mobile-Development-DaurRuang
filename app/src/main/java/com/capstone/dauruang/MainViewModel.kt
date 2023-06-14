package com.capstone.dauruang

import android.content.Context
import android.graphics.Color
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.capstone.dauruang.data.clusters.calculateCameraViewPoints
import com.capstone.dauruang.data.clusters.getCenterOfPolygon
import com.capstone.dauruang.model.MapState
import com.capstone.dauruang.model.ZoneClusterItem
import com.capstone.dauruang.model.ZoneClusterManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.ktx.model.polygonOptions
import kotlin.math.*

class MainViewModel : ViewModel()  {

    val MIN_DISTANCE = 2000

    fun generateRandomLatLng(centerLatLng: LatLng, distance: Int): LatLng {
        val radius = distance / 111000.0 // Konversi jarak ke derajat (sekitar 111.0 km per derajat)
        val u = Math.random()
        val v = Math.random()
        val w = radius * sqrt(u)
        val t = 2.0 * PI * v
        val x = w * cos(t)
        val y = w * sin(t)
        val newX = x / cos(centerLatLng.latitude)
        val newLng = centerLatLng.longitude + y
        return LatLng(centerLatLng.latitude + newX, newLng)
    }

    val state: MutableState<MapState> = mutableStateOf(
        MapState(
            lastKnownLocation = null,
            clusterItems = List(5) { index ->
                val centerLatLng = LatLng(-6.200000, 106.800000) // Koordinat pusat Jakarta sebagai titik acuan
                val distance = MIN_DISTANCE * index // Mengatur jarak antar zona berdasarkan index
                val randomLatLng = generateRandomLatLng(centerLatLng, distance)
                ZoneClusterItem(
                    id = "zone-${index + 1}",
                    title = "Zone ${index + 1}",
                    snippet = "This is Zone ${index + 1}.",
                    polygonOptions = polygonOptions {
                        add(randomLatLng)
                        add(LatLng(randomLatLng.latitude + 0.005, randomLatLng.longitude))
                        add(LatLng(randomLatLng.latitude + 0.005, randomLatLng.longitude + 0.005))
                        add(LatLng(randomLatLng.latitude, randomLatLng.longitude + 0.005))
                        fillColor(POLYGON_FILL_COLOR)
                    }
                )
            }
        )
    )

    fun getDeviceLocation(
        fusedLocationProviderClient: FusedLocationProviderClient
    ) {
        try {
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    state.value = state.value.copy(
                        lastKnownLocation = task.result,
                    )
                }
            }
        } catch (e: SecurityException) {
            // Show error or something
        }
    }

    fun setupClusterManager(
        context: Context,
        map: GoogleMap,
    ): ZoneClusterManager {
        val clusterManager = ZoneClusterManager(context, map)
        clusterManager.addItems(state.value.clusterItems)
        return clusterManager
    }

    fun calculateZoneLatLngBounds(): LatLngBounds {
        // Get all the points from all the polygons and calculate the camera view that will show them all.
        val latLngs = state.value.clusterItems.map { it.polygonOptions }
            .map { it.points.map { LatLng(it.latitude, it.longitude) } }.flatten()
        return latLngs.calculateCameraViewPoints().getCenterOfPolygon()
    }

    companion object {
        private val POLYGON_FILL_COLOR = Color.parseColor("#ABF44336")
    }


//    val state: MutableState<MapState> = mutableStateOf(
//        MapState(
//            lastKnownLocation = null,
//            clusterItems = listOf(
//                ZoneClusterItem(
//                    id = "zone-1",
//                    title = "Zone 1",
//                    snippet = "This is Zone 1.",
//                    polygonOptions = polygonOptions {
//                        add(LatLng(-6.200000, 106.700000))
//                        add(LatLng(-6.195000, 106.700000))
//                        add(LatLng(-6.195000, 106.705000))
//                        add(LatLng(-6.200000, 106.705000))
//                        fillColor(POLYGON_FILL_COLOR)
//                    }
//                ),
//                ZoneClusterItem(
//                    id = "zone-2",
//                    title = "Zone 2",
//                    snippet = "This is Zone 2.",
//                    polygonOptions = polygonOptions {
//                        add(LatLng(-6.200000, 106.710000))
//                        add(LatLng(-6.195000, 106.710000))
//                        add(LatLng(-6.195000, 106.715000))
//                        add(LatLng(-6.200000, 106.715000))
//                        fillColor(POLYGON_FILL_COLOR)
//                    }
//                ),
//                ZoneClusterItem(
//                    id = "zone-3",
//                    title = "Zone 3",
//                    snippet = "This is Zone 3.",
//                    polygonOptions = polygonOptions {
//                        add(LatLng(-6.200000, 106.720000))
//                        add(LatLng(-6.195000, 106.720000))
//                        add(LatLng(-6.195000, 106.725000))
//                        add(LatLng(-6.200000, 106.725000))
//                        fillColor(POLYGON_FILL_COLOR)
//                    }
//                ),
//                ZoneClusterItem(
//                    id = "zone-4",
//                    title = "Zone 4",
//                    snippet = "This is Zone 4.",
//                    polygonOptions = polygonOptions {
//                        add(LatLng(-6.200000, 106.730000))
//                        add(LatLng(-6.195000, 106.730000))
//                        add(LatLng(-6.195000, 106.735000))
//                        add(LatLng(-6.200000, 106.735000))
//                        fillColor(POLYGON_FILL_COLOR)
//                    }
//                ),
//                ZoneClusterItem(
//                    id = "zone-5",
//                    title = "Zone 5",
//                    snippet = "This is Zone 5.",
//                    polygonOptions = polygonOptions {
//                        add(LatLng(-6.200000, 106.740000))
//                        add(LatLng(-6.195000, 106.740000))
//                        add(LatLng(-6.195000, 106.745000))
//                        add(LatLng(-6.200000, 106.745000))
//                        fillColor(POLYGON_FILL_COLOR)
//                    }
//                )
//            )
//        )
//    )


}