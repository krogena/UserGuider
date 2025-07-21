package ru.iyshcherbakov.userguider.data

import retrofit2.http.GET
import ru.iyshcherbakov.userguider.data.dto.ApiUser

interface UserService {
    @GET("api/?results=15")
    suspend fun getUsers(): ApiResponse<ApiUser>
}