package ru.iyshcherbakov.userguider.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.iyshcherbakov.userguider.data.UserService
import ru.iyshcherbakov.userguider.domain.User.Companion.BASE_URL
import javax.inject.Singleton
import kotlin.jvm.java

/**
 * Модуль Dagger Hilt для предоставления зависимостей уровня приложения.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Предоставляет базовый URL для API.
    @Provides
    fun baseUrl() = BASE_URL


    // Создает и настраивает HTTP-логгер для отладки сетевых запросов.
    // Устанавливает уровень логирования BODY для вывода тел запросов и ответов.
    @Provides
    fun logging() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // Создает и настраивает OkHttpClient с добавленным логгером.
    @Provides
    fun okHttpClient() = OkHttpClient.Builder().addInterceptor(logging()).build()

    // Предоставляет Retrofit-клиент как синглтон для работы с API.
    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): UserService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient())
        .build()
        .create(UserService::class.java)
}