package ru.iyshcherbakov.userguider.data

data class ApiResponse<T>(
    val results: List<T>
)
