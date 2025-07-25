package ru.iyshcherbakov.userguider.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.iyshcherbakov.userguider.domain.GetUserListUseCase
import ru.iyshcherbakov.userguider.domain.User
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _errors = MutableSharedFlow<String>()
    val errors: SharedFlow<String> = _errors

    fun loadUsers() {
        viewModelScope.launch {
            try {
                _users.value = getUserListUseCase()
            } catch (e: Exception) {
                Log.e("MainVM", "Error: ", e)
                _errors.emit(
                    when {
                        e is IOException -> "Нет интернет-соединения"
                        e is NullPointerException -> "Ошибка формата данных"
                        else -> "Ошибка: ${e.message ?: "неизвестная ошибка"}"
                    }
                )
            }
        }
    }
}