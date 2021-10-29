package uz.lars_lion.test.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.lars_lion.test.model.ArticleLocal
import uz.lars_lion.test.network.ApiService
import uz.lars_lion.test.network.NetworkMapper
import uz.lars_lion.test.room.ArticleDAO
import uz.lars_lion.test.room.CacheMapper
import uz.lars_lion.test.utils.DataState
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val articleDAO: ArticleDAO,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getArticles(): Flow<DataState<List<ArticleLocal>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkNews = apiService.getNews()
//            val articlesNetwork = networkNews.
//            val articles = networkMapper.mapFromNetworkEntityList(articlesNetwork)
//            for (article in articles) {
//                articleDAO.insertArticle(cacheMapper.mapToEntity(article))
//            }
//            val cachedArticles = articleDAO.getArticles()
//            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedArticles)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
            println("Men shu yerdaman")
        }
    }
}