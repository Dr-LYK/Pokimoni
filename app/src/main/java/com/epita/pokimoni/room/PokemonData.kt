package com.epita.pokimoni.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.epita.pokimoni.model.TypeColor

@Entity(tableName = "pokemonData")
data class PokemonData(@PrimaryKey(autoGenerate = true)
                       var id: Long?,
                       @ColumnInfo(name = "index") var index: Int,
                       @ColumnInfo(name = "name") var name: String,
                       @ColumnInfo(name = "type1") var type1: String,
                       @ColumnInfo(name = "type2") var type2: String?,
                       @ColumnInfo(name = "count") var count: Int) {

    @Ignore
    constructor(): this(null, 0, "", "", null, 0)
}