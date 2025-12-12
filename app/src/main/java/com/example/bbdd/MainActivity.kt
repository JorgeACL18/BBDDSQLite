package com.example.bbdd

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bbdd.DAO.AppDatabase
import com.example.bbdd.ui.theme.BBDDTheme
import kotlinx.coroutines.GlobalScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BBDDTheme {
                val sharedPreferences = getSharedPreferences(Datos.PREF_NAME, Context.MODE_PRIVATE)
                val database = AppDatabase.getDatabase(this, GlobalScope)
                val jugadorDao = database.jugadorDao()
                val miViewModel: MyViewModel = viewModel(
                    factory = MyViewModelFactory(jugadorDao, sharedPreferences)
                )
                IU(miViewModel)
            }
        }
    }
}