package com.example.bbdd.DAO

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jugadores")
data class Jugador(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombre: String,
    val max: Int
)