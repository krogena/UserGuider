package ru.iyshcherbakov.userguider.data.dto

data class ApiUser(
    val login: ApiLogin?,
    val name: ApiName?,
    val email: String?,
    val phone: String?,
    val picture: ApiPicture?,
    val location: ApiLocation?,
    val gender: String?,
    val dob: ApiDob?,
    val registered: ApiRegistered?
)

data class ApiLogin(val uuid: String)
data class ApiDob(val date: String)
data class ApiRegistered(val date: String)

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
