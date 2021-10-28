package uz.lars_lion.test.network.response

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)