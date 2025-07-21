package ru.iyshcherbakov.userguider.data.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Инициализация Hilt в приложении
@HiltAndroidApp
class UserApp: Application()