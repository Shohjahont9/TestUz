package uz.lars_lion.test.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArticleCacheEntity::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDAO
    companion object{
        val DATABASE_NAME:String = "article_db"
    }

}