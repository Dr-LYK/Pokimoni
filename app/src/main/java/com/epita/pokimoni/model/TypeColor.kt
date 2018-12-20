package com.epita.pokimoni.model

enum class TypeColor(val typeName: String, val color: Int, val border: Int) {
    FIGHTING("fighting", 0xBE322E, 0x7C201D),
    DRAGON("dragon", 0x7043F4, 0x492B9E),
    WATER("water", 0x6A92ED, 0x455F9A),
    ELECTRIC("electric", 0xF7CF43, 0xA0862B),
    FIRE("fire", 0xEE803B, 0x9B5326),
    ICE("ice", 0x9AD8D7, 0x648D8D),
    BUG("bug", 0xA8B732, 0x6D7720),
    NORMAL("normal", 0xA8A77A, 0x6D6D4F),
    GRASS("grass", 0x7AC657, 0x508138),
    POISON("poison", 0x9F449E, 0x672C67),
    PSYCHIC("psychic", 0xF65B89, 0x9F3B5A),
    ROCK("rock", 0xB79F41, 0x78672A),
    GROUND("ground", 0xDFBF6E, 0x917C48),
    GHOST("ghost", 0x705A96, 0x493A62),
    FLYING("flying", 0xA893ED, 0x6D609A)
}