package ru.iyshcherbakov.userguider.domain

class GetUserDetailsUseCase (private val repository: UserListRepository){
    suspend fun getDetails(userId: String): User?{
        return repository.getUserById(userId)
    }
}