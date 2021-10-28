package uz.lars_lion.test.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(articleCacheEntity: ArticleCacheEntity)

    @Query("SELECT*FROM articles")
    suspend fun getArticles():List<ArticleCacheEntity>
}