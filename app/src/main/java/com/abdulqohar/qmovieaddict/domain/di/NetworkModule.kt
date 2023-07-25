package com.abdulqohar.qmovieaddict.domain.di

import android.content.Context
import com.abdulqohar.qmovieaddict.util.Constants.Companion.BASE_URL
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

class NetworkModule {

    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

//    @Provides
//    @Singleton
//    fun provideNetworkInterceptor(@ApplicationContext context: Context): NetworkInterceptor = NetworkInterceptor(context)

    @Provides
    @Singleton
    fun providesAuthorizationHeaderInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request().newBuilder().addHeader("Authorization", "Bearer "
//                "${preferences.getToken()}"
        ).build()
        chain.proceed(request)
    }
    @Provides
    @Singleton
    fun providesOkhttp(loggingInterceptor: HttpLoggingInterceptor, authHeaderInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(120L, TimeUnit.SECONDS)
            .readTimeout(120L, TimeUnit.SECONDS)
            .writeTimeout(120L, TimeUnit.SECONDS)
            .callTimeout(120L, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authHeaderInterceptor)
           .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()


}