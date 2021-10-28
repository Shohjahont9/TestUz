package uz.lars_lion.testuz.network.response

import uz.lars_lion.testuz.network.response.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)