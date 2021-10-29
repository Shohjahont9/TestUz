package uz.lars_lion.test.ui.main.photo_feed.view

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import uz.lars_lion.test.ui.main.photo_feed.view.*

internal class PhotosAdapter : ListAdapter<PhotoListItem, PhotoListItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListItemViewHolder =
        when (viewType) {
            photoViewType -> PhotoViewHolder.create(parent.context, parent)
            else -> throw IllegalArgumentException("View type $viewType is not supported by this adapter")
        }

    override fun onBindViewHolder(holder: PhotoListItemViewHolder, position: Int) {
        val item = getItem(position)
        when (item) {
            is PhotoListItem.PhotoItem -> (holder as? PhotoViewHolder)?.bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is PhotoListItem.PhotoItem -> photoViewType
        }

    companion object {
        private const val photoViewType = 1
        private const val nextLoadingViewType = 2
        private const val nextLoadingErrorViewType = 3
    }
}

private class DiffCallback : DiffUtil.ItemCallback<PhotoListItem>() {

    override fun areItemsTheSame(oldItem: PhotoListItem, newItem: PhotoListItem): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: PhotoListItem, newItem: PhotoListItem): Boolean =
        oldItem == newItem
}
