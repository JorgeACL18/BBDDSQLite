package com.example.bbdd.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Jugador::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jugadorDao(): DAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder<AppDatabase>(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "jugadores_database"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            INSTANCE?.let { database ->
                                scope.launch {
                                    val dao = database.jugadorDao()

                                    dao.insertarJugador(Jugador(nombre = "Jugador1", max = 5))
                                    dao.insertarJugador(Jugador(nombre = "Jugador2", max = 10))
                                }
                            }
                        }
                    })
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
