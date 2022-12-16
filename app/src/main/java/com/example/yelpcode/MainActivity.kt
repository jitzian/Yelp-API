package com.example.yelpcode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.yelpcode.ui.app.YelpAppScreen
import com.example.yelpcode.ui.navigation.MainScreenView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YelpAppScreen {
                MainScreenView()
            }
        }
    }
}
