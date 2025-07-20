package ru.iyshcherbakov.userguider.domain

class GetUserListUseCase (private val repository: UserListRepository){
    suspend fun getUserList(): List<User>{
        return repository.getUsers()
    }
}