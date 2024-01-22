package uk.acm0x.beers.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object PunkClient {
    private const val BASE_URL = "https://api.punkapi.com/v2/"

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit by lazy {
        val jsonConfig = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                jsonConfig.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()
    }

    val beersService: BeersService by lazy {
        retrofit.create(BeersService::class.java)
    }
}