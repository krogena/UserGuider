package ru.iyshcherbakov.userguider.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.iyshcherbakov.userguider.data.dto.ApiUser
import ru.iyshcherbakov.userguider.domain.User
import ru.iyshcherbakov.userguider.domain.UserListRepository
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val service: UserService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserListRepository {

    override suspend fun getUsers(): List<User> = withContext(dispatcher) {
        try {
            // 1. Получаем данные из API
            val apiResponse = service.getUsers()

            // 2. Маппим в доменные модели
            apiResponse.results.map { apiUser ->
                apiUser.toDomainUser()
            }
        } catch (e: Exception) {
            // 3. Обработка ошибок
            throw when (e) {
                is IOException -> UserDataException.NetworkError
                else -> UserDataException.UnknownError
            }
        }
    }

    override suspend fun getUserById(id: String): User? = withContext(dispatcher) {
        try {
            // Оптимизация: сначала проверяем локальное хранилище (если есть)
            // Если нет - грузим свежий список и ищем пользователя
            getUsers().firstOrNull { it.id == id }
        } catch (e: Exception) {
            null // или пробрасываем ошибку
        }
    }
}

// Расширение для маппинга
private fun ApiUser.toDomainUser(): User {
    return User(
        id = this.login?.uuid ?: "", // Добавьте проверку на null
        name = "${this.name?.first ?: ""} ${this.name?.last ?: ""}".trim(),
        email = this.email ?: "",
        phone = this.phone ?: "",
        picture = this.picture?.medium ?: "",
        location = "${this.location?.city ?: ""}, ${this.location?.country ?: ""}",
        fullPicture = this.picture?.large ?: "",
        gender = this.gender ?: "",
        dob = this.dob?.date ?: "",
        street = "${this.location?.street?.name ?: ""}, ${this.location?.street?.number ?: 0}",
        registrationDate = this.registered?.date ?: ""
    )
}

// Ошибки
sealed class UserDataException : Exception() {
    object NetworkError : UserDataException()
    object UnknownError : UserDataException()
}