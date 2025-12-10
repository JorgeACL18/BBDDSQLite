package com.example.bbdd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bbdd.ui.theme.BBDDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BBDDTheme {
                val miViewModel: MyViewModel = viewModel(factory = MyViewModelFactory(this))
                IU(miViewModel)
            }
        }
    }
}