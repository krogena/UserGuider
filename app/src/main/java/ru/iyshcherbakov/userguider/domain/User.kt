package ru.iyshcherbakov.userguider.domain

data class User(
    // Краткая информация (для списка)
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val picture: String,
    val location: String,

    // Детали (для экрана пользователя)
    val fullPicture: String,
    val gender: String,
    val dob: String,
    val street: String,
    val registrationDate: String
)