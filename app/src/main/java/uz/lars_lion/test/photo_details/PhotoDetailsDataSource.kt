package uz.lars_lion.test.photo_details

import io.reactivex.Single
import uz.lars_lion.test.network.ApiService
import uz.lars_lion.test.network.response.NewsResponse

interface PhotoDetailsDataSource {
    fun getPhoto(id: String): Single<NewsResponse>
}


class PhotoDetailsDataSourceImpl(private val api: ApiService) : PhotoDetailsDataSource {
    override  fun getPhoto(id: String): Single<NewsResponse> = api.getNews()

}
