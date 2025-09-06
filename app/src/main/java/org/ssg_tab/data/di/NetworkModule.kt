package org.ssg_tab.data.di

import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.ssg_tab.BuildConfig
import org.ssg_tab.core.network.TokenManager
import org.ssg_tab.data.remote.AuthInterceptor
import org.ssg_tab.data.service.OnboardingService
import org.ssg_tab.data.service.home.HomeFeedService
import org.ssg_tab.data.service.home.HomeLikeService
import org.ssg_tab.data.service.quiz.QuizService
import org.ssg_tab.data.service.login.AuthApiService
import org.ssg_tab.data.service.quiz.QuizCompleteService
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

//    @Provides
//    @Singleton
//    fun provideAuthInterceptor(tokenManager: TokenManager): AuthInterceptor {
//        return AuthInterceptor(tokenManager)
//    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor) // ← 이게 빠져있었음
        .build()
    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    @Provides
    @Singleton
    fun providesConverterFactory(json: Json): Converter.Factory =
        json.asConverterFactory("application/json".toMediaType())


    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(converterFactory)
        .client(client)
        .build()


    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("ssg_tab_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provdieQuizService(retrofit: Retrofit): QuizService {
        return retrofit.create(QuizService::class.java)
    }

    @Provides
    @Singleton
    fun provdieOnboardingService(retrofit: Retrofit): OnboardingService{
        return retrofit.create(OnboardingService::class.java)
    }

    @Provides
    @Singleton
    fun provdieHomeService(retrofit: Retrofit): HomeFeedService {
        return retrofit.create(HomeFeedService::class.java)
    }

    @Provides
    @Singleton
    fun provdieQuizCompleteService(retrofit: Retrofit): QuizCompleteService {
        return retrofit.create(QuizCompleteService::class.java)
    }

    @Provides
    @Singleton
    fun provdieHomeLikeeService(retrofit: Retrofit): HomeLikeService {
        return retrofit.create(HomeLikeService::class.java)
    }
}