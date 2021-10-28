package uz.lars_lion.testuz.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.lars_lion.testuz.room.ArticleCacheEntity

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(articleCacheEntity: ArticleCacheEntity)

    @Query("SELECT*FROM articles")
    suspend fun getArticles():List<ArticleCacheEntity>
}