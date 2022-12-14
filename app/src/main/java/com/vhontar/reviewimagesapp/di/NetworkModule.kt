package com.vhontar.reviewimagesapp.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.vhontar.reviewimagesapp.BuildConfig
import com.vhontar.reviewimagesapp.datasource.network.HitsNetworkService
import com.vhontar.reviewimagesapp.datasource.network.HitsNetworkServiceImpl
import com.vhontar.reviewimagesapp.datasource.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import java.time.Duration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun providesNetworkService(okHttpClient: OkHttpClient): NetworkService {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }

        return Retrofit.Builder()
            .baseUrl(BuildConfig.PIXABAY_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()
            .create(NetworkService::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(Duration.ofSeconds(20))
            .readTimeout(Duration.ofSeconds(20))
            .callTimeout(Duration.ofSeconds(30))
            .addInterceptor { chain ->
                val request = chain.request()
                val originalHttpUrl = request.url

                val changedUrl = originalHttpUrl.newBuilder()
                    .addQueryParameter("key", BuildConfig.PIXABAY_KEY)
                    .build()

                val changedRequestBuilder = Request.Builder()
                    .url(changedUrl)
                    .build()

                Log.d("OkHttp", changedRequestBuilder.url.toString())
                chain.proceed(changedRequestBuilder)
            }
            .build()

        return client
    }
}