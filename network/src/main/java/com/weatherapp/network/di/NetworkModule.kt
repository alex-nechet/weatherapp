package com.weatherapp.network.di


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.weatherapp.network.BuildConfig
import com.weatherapp.network.ResponseConverter
import com.weatherapp.network.WeatherApi
import com.weatherapp.network.datasource.WeatherRemoteDataSource
import com.weatherapp.network.datasource.WeatherRemoteDataSourceImpl
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private const val BASE_URL =
    "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/"
private const val API_KEY = "api-key"
private const val LOGGING_INTERCEPTOR = "logging-interceptor"

private val network = module {

    single(named(API_KEY)) {
        Interceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("key", "your-actual-api-key")
                .build()

            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)

            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    single<Interceptor>(named(LOGGING_INTERCEPTOR)) {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return@single httpLoggingInterceptor
    }

    single {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(get<Interceptor>(named(LOGGING_INTERCEPTOR)))
        }
        clientBuilder.addInterceptor(get<Interceptor>(named(API_KEY)))
        clientBuilder.build()
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single { ResponseConverter(get()) }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build() // Doesn't require the adapter
    }
}

private val remoteDataSourceModule = module {
    single<WeatherRemoteDataSource> { WeatherRemoteDataSourceImpl(get(), get()) }
}

private val weatherApi = module {
    single<WeatherApi> { get<Retrofit>().create(WeatherApi::class.java) }
}

val networkModule = listOf(network, remoteDataSourceModule, weatherApi)
