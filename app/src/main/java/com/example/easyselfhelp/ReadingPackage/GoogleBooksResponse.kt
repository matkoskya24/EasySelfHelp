package com.example.easyselfhelp.ReadingPackage

import com.squareup.moshi.Json

class GoogleBooksResponse {
    @Json(name = "items")
    lateinit var bookFeaturesList: List<BookFeatures>
}

class BookFeatures {
    @Json(name = "volumeInfo")
    lateinit var bookProperties: BookProperties
}

class BookProperties {
    @Json(name = "title")
    var title: String = ""

    @Json(name = "subtitle")
    var subtitle: String = ""

    @Json(name = "authors")
    lateinit var authors: List<String>

    @Json(name = "canonicalVolumeLink")
    var url: String = ""

    @Json(name = "imageLinks")
    lateinit var imageProperties: ImageLinkProperties
}

class ImageLinkProperties {
    @Json(name = "thumbnail")
    var uri = ""
}