package uz.lars_lion.test.ui.main.photo_feed

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.lars_lion.test.network.ApiService
import uz.lars_lion.test.network.response.Article
import uz.lars_lion.test.network.response.NewsResponse

interface PhotoFeedDataSource {
     fun loadPhotos(): Single<NewsResponse>
}

//data class Article(val author: String,
//                   val content: String,
//                   val description: String,
//                   val publishedAt: String,
//                   val source: Source,
//                   val title: String,
//                   val url: String,
//                   val urlToImage: String) {
//
//}

data class PhotoFeedDataSourceImpl(
    private val api: ApiService
) : PhotoFeedDataSource {

    override fun loadPhotos(): Single<NewsResponse>  {
        return api.getNews().observeOn(AndroidSchedulers.mainThread())
    }
}
