package com.example.easyselfhelp.ReadingPackage

data class Book(
    val title: String,
    val subtitle: String,
    val authors: List<String>,
    val url: String,
    val imageuri: String
)