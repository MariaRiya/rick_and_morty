package com.example.projectrickandmorty.ui

import android.graphics.Bitmap
//import com.example.projectrickandmorty.ui.characters.Character
import com.google.gson.annotations.SerializedName
import java.util.*

data class Responsee(
    var info : Info? = null,
    var results : MutableList<Characters>? = null
)

data class Info(
    var count : Int? =null,
    var pages : Int? = null,
    var next : String? = null,
    var prev : String? = null
)

data class Characters(
    var id : String? = null,
    var name : String? = null,
    var status : String? = null,
    var species : String? = null,
    var type : String? = null,
    var gender : String? = null,
    var origin : Origin? = null,
    var location : Location? = null,
    var image : String? = null,
    var episode : MutableList<String>? = null,
    var url : String? = null,
    var created: String? = null

)

data class Origin(
    var name : String? = null,
    var url : String? = null
)

data class Location(
    var name : String? = null,
    var url : String? = null
)

data class Episode(
    var episodes : MutableList<String>? = null
)
