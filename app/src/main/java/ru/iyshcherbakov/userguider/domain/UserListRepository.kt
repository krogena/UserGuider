package ru.iyshcherbakov.userguider.domain

interface UserListRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUserById(id: String): User?
}