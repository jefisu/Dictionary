package com.jefisu.dictionary.feature.data.repository

import com.jefisu.dictionary.core.util.Resource
import com.jefisu.dictionary.feature.data.local.WordInfoDao
import com.jefisu.dictionary.feature.data.remote.DictionaryApi
import com.jefisu.dictionary.feature.domain.model.WordInfo
import com.jefisu.dictionary.feature.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfo = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfo))

        try {
            val remoteWordInfo = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfo.map { it.word })
            dao.insertWordInfos(remoteWordInfo.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Ops, algo deu errado!",
                data = wordInfo
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Não foi possível acessar o servidor, verifique sua conexão com a Internet.",
                data = wordInfo
            ))
        }

        val newWordInfo = dao.getWordInfos(word)
        emit(Resource.Success(data = newWordInfo.map { it.toWordInfo() }))
    }
}