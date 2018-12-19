package com.epita.pokimoni

import android.graphics.Bitmap

class Pokemon constructor(var id: Int,
                          var name: String,
                          var height: Int,
                          var weight: Int,
                          var type1: TypeColor,
                          var type2: TypeColor?,
                          var description: String,
                          var picture: Bitmap)