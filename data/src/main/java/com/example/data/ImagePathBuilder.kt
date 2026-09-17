package com.example.network

const val DEFAULT_QUALITY = "w500"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"

fun getImageUrl(path: String, quality: String = DEFAULT_QUALITY): String {
    return "$IMAGE_BASE_URL$quality$path"
}
