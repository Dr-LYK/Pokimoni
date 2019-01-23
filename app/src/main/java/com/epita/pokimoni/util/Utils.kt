package com.epita.pokimoni.util

import com.epita.pokimoni.model.TypeColor


class Utils {

    companion object {

        /*private val list: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74,
            75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 94, 100, 104, 105, 107, 112, 115, 120,
            121, 122, 123, 127, 129, 130, 132, 133, 134, 135, 136, 137, 138, 139, 142, 143, 144, 145, 146, 147, 148,
            149, 150, 151)

        fun getRandomPokemon(): String? {
            val i: Int = list.random()
            return when (i) {
                in 1..9 -> "00$i.sfb"
                in 10..99 -> "0$i.sfb"
                in 100..151 -> "$i.sfb"
                else -> null
            }
        }*/

        private val list: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42)

        fun getRandomPokemon(): String? {
            val i: Int = list.random()
            return when (i) {
                in 1..9 -> "00$i.sfb"
                in 10..42 -> "0$i.sfb"
                else -> null
            }
        }

        fun stringToTypeColor(string: String?): TypeColor? {
            when (string) {
                "fighting" -> return TypeColor.FIGHTING
                "dragon" -> return TypeColor.DRAGON
                "water" -> return TypeColor.WATER
                "electric" -> return TypeColor.ELECTRIC
                "fire" -> return TypeColor.FIRE
                "ice" -> return TypeColor.ICE
                "bug" -> return TypeColor.BUG
                "normal" -> return TypeColor.NORMAL
                "grass" -> return TypeColor.GRASS
                "poison" -> return TypeColor.POISON
                "psychic" -> return TypeColor.PSYCHIC
                "rock" -> return TypeColor.ROCK
                "ground" -> return TypeColor.GROUND
                "ghost" -> return TypeColor.GHOST
                "flying" -> return TypeColor.FLYING
                else -> return null
            }
        }

        fun fileToIndex(file: String): Int {
            return file.substring(0, 3).toInt()
        }
    }
}