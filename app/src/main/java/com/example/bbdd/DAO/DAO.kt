package com.example.bbdd.DAO

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {
    @Query("SELECT * FROM jugadores ORDER BY max DESC")
    fun getAllJugdores(): Flow<Jugador>

    @Insert
    suspend fun insertarJugador(play: Jugador)

    @Update
    suspend fun actualizarJugador(play: Jugador)

    @Delete
    suspend fun eliminarJugador(play: Jugador)

    @Query("SELECT * FROM jugadores WHERE nombre LIKE :nombreBuscado LIMIT 1")
    suspend fun getNombre(nombreBuscado: String): Jugador?

    @Query("SELECT * FROM jugadores ORDER BY max DESC LIMIT 1")
    suspend fun getRecord(): Jugador?
}