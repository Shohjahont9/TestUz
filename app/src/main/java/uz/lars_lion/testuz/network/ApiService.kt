package uz.lars_lion.testuz.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import uz.lars_lion.testuz.network.response.NewsResponse
import uz.lars_lion.testuz.utils.Constants

interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("everything?q=tesla&from=2021-09-27&sortBy=publishedAt")
    suspend fun getNews(
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse
}