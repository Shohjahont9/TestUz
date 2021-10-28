package uz.lars_lion.testuz.room

import uz.lars_lion.testuz.model.ArticleLocal
import uz.lars_lion.testuz.utils.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor(): EntityMapper<ArticleCacheEntity, ArticleLocal> {
    override fun mapFromEntity(entity: ArticleCacheEntity): ArticleLocal {
        return ArticleLocal(
            author = entity.author,
            title = entity.title,
            description = entity.description,
            publishedAt = entity.publishedAt,
            urlToImage = entity.urlToImage
        )
    }

    fun mapFromEntityList(entities: List<ArticleCacheEntity>):List<ArticleLocal>{
        return entities.map { mapFromEntity(it) }
    }

    override fun mapToEntity(domainModel: ArticleLocal): ArticleCacheEntity {
        return ArticleCacheEntity(
            author = domainModel.author,
            title = domainModel.title,
            description = domainModel.description,
            publishedAt = domainModel.publishedAt,
            urlToImage = domainModel.urlToImage,
            id = null
        )
    }
}