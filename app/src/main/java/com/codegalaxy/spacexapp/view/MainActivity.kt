package com.codegalaxy.spacexapp.view

import SpaceXLaunchesScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import calculateDeviceSizeClass
import com.codegalaxy.spacexapp.ui.theme.SpaceXAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            SpaceXAppTheme {
                //calculating device size class and pass it to SpaceXLaunchesScreen
                val deviceSizeClass = calculateDeviceSizeClass()
                SpaceXLaunchesScreen(deviceSizeClass = deviceSizeClass)
            }
        }
    }
}
