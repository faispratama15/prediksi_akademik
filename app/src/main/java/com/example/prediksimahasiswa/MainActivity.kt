package com.example.prediksimahasiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.prediksimahasiswa.ui.HomeScreen
import com.example.prediksimahasiswa.ui.PredictionScreen
import com.example.prediksimahasiswa.ui.theme.PrediksiMahasiswaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            PrediksiMahasiswaTheme {

                var currentScreen by remember {
                    mutableStateOf("home")
                }

                when (currentScreen) {

                    "home" -> {

                        HomeScreen(
                            onStartPrediction = {
                                currentScreen = "prediction"
                            }
                        )
                    }

                    "prediction" -> {

                        PredictionScreen(
                            onBack = {
                                currentScreen = "home"
                            }
                        )
                    }
                }
            }
        }
    }
}