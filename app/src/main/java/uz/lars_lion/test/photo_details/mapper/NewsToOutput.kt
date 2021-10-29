package uz.lars_lion.test.photo_details.mapper

import uz.lars_lion.test.photo_details.PhotoDetails.Output
import uz.lars_lion.test.photo_details.feature.PhotoDetailsFeature.News

internal object NewsToOutput : (News) -> Output? {

    override fun invoke(news: News): Output? = null
}
