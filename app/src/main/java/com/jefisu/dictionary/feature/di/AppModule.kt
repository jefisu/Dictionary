package com.jefisu.dictionary.feature.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.jefisu.dictionary.feature.data.local.Converters
import com.jefisu.dictionary.feature.data.local.WordInfoDao
import com.jefisu.dictionary.feature.data.local.WordInfoDatabase
import com.jefisu.dictionary.feature.data.remote.DictionaryApi
import com.jefisu.dictionary.feature.data.repository.WordRepositoryImpl
import com.jefisu.dictionary.feature.domain.repository.WordRepository
import com.jefisu.dictionary.feature.domain.use_case.GetWordInfo
import com.jefisu.dictionary.core.util.GsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "dictionar_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideWordDao(db: WordInfoDatabase): WordInfoDao {
        return db.dao
    }

    @Provides
    @Singleton
    fun provideWordRepository(api: DictionaryApi, dao: WordInfoDao): WordRepository {
        return WordRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordRepository): GetWordInfo {
        return GetWordInfo(repository)
    }
}