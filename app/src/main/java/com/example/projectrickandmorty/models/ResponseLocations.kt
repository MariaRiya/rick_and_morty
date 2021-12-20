package com.example.projectrickandmorty.models

data class ResponseLocations(
    var info : Info? = null,
    var results : MutableList<Location>? = null
)

data class Location(
    var id : String? = null,
    var name : String? = null,
    var type : String? = null,
    var dimension : String? = null,
    var residents: MutableList<String>? = null,
    var url : String? = null,
    var created: String? = null
)