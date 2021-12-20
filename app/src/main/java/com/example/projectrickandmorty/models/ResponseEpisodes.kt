package com.example.projectrickandmorty.models



data class ResponseEpisodes(
        var info : Info? = null,
        var results : MutableList<Episode>? = null
    )

    data class Info(
        var count : Int? =null,
        var pages : Int? = null,
        var next : String? = null,
        var prev : String? = null
    )

data class Episode(
    var id : String? = null,
    var name : String? = null,
    var air_date : String? = null,
    var episode : String? = null,
    var characters: MutableList<String>? = null,
    var url : String? = null,
    var created: String? = null
)

