package com.epita.pokimoni.api.model

class PokemonJsonModel constructor(val id: Int?,
                                   val name: String?,
                                   val height: Int?,
                                   val weight: Int?,
                                   val types: ArrayList<TypesJsonModel>?,
                                   val species: SpeciesJsonModel?,
                                   val sprites: SpritesJsonModel?)