package ru.iyshcherbakov.userguider.domain

import javax.inject.Inject

class GetUserListUseCase @Inject constructor (private val repository: UserListRepository){
    suspend operator fun invoke(): List<User>{
        return repository.getUsers()
    }
}