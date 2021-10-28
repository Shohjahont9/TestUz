package uz.lars_lion.testuz.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleCacheEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    var title:String?,
    var description:String?,
    var publishedAt:String?,
    var urlToImage:String?,
    var author:String?,
)