package uz.lars_lion.testuz.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.lars_lion.testuz.room.ArticleCacheEntity
import uz.lars_lion.testuz.room.ArticleDAO

@Database(entities = [ArticleCacheEntity::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDAO
    companion object{
        val DATABASE_NAME:String = "article_db"
    }

}