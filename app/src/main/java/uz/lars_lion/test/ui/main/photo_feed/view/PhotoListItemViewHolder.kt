package uz.lars_lion.test.ui.main.photo_feed.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.lars_lion.test.R


abstract class PhotoListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

internal class PhotoViewHolder(private val photoView: View) :
    PhotoListItemViewHolder(photoView) {

    fun bind(item: PhotoListItem.PhotoItem) {
        with(item) {
            val imageView = photoView.findViewById<ImageView>(R.id.img_card)
            imageView.load(article.url)
            photoView.setOnClickListener {
                onClicked(article)
            }
        }
    }

    companion object {
        fun create(context: Context, parent: ViewGroup) = PhotoViewHolder(
            LayoutInflater.from(context).inflate(R.layout.ite_card, parent, false)
        )
    }
}

