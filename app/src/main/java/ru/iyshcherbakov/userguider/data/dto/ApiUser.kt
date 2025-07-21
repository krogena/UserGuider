package ru.iyshcherbakov.userguider.data.dto

data class ApiUser(
    val id: String,
    val name: ApiName,
    val email: String,
    val phone: String,
    val picture: ApiPicture,
    val location: ApiLocation
)

data class ApiName(
    val first: String,
    val last: String
)

data class ApiPicture(
    val medium: String,
    val large: String
)

data class ApiLocation(
    val city: String,
    val country: String,
    val street: ApiStreet,
    val coordinates: ApiCoordinates
)

data class ApiStreet(
    val name: String,
    val number: Int
)

data class ApiCoordinates(
    val latitude: String,
    val longitude: String
)
