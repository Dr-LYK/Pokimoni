package com.epita.pokimoni.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [PokemonData::class], version = 1)
abstract class PokemonDataBase : RoomDatabase() {

    abstract fun pokemonDataDao(): PokemonDataDao

    companion object {
        private var INSTANCE: PokemonDataBase? = null

        fun getInstance(context: Context): PokemonDataBase? {
            if (INSTANCE == null) {
                synchronized(PokemonDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PokemonDataBase::class.java,
                        "pokemon.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}