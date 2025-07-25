package ru.iyshcherbakov.userguider.domain

import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor (private val repository: UserListRepository){
    suspend operator fun invoke(userId: String): User?{
        return repository.getUserById(userId)
    }
}