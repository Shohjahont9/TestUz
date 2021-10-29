package uz.lars_lion.test.network

import uz.lars_lion.test.model.ArticleLocal
import uz.lars_lion.test.network.response.Article
import uz.lars_lion.test.utils.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor(
) : EntityMapper<Article, ArticleLocal> {
    override fun mapFromEntity(entity: Article): ArticleLocal {
        return ArticleLocal(
            author = entity.author,
            title = entity.title,
            description = entity.description,
            publishedAt = entity.publishedAt,
            urlToImage = entity.urlToImage
        )
    }


   fun mapFromNetworkEntityList(entities: List<Article>):List<ArticleLocal>{
        return entities.map { mapFromEntity(it) }
    }

    override fun mapToEntity(domainModel: ArticleLocal): Article {
        TODO("Not yet implemented")
    }
}