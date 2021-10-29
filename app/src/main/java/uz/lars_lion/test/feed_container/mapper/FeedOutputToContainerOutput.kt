package uz.lars_lion.test.feed_container.mapper

import uz.lars_lion.test.feed_container.FeedContainer
import uz.lars_lion.test.ui.main.photo_feed.PhotoFeed

internal object FeedOutputToContainerOutput : (PhotoFeed.Output) -> FeedContainer.Output? {
    override fun invoke(output: PhotoFeed.Output): FeedContainer.Output? =
        when (output) {
            is PhotoFeed.Output.PhotoClicked -> FeedContainer.Output.PhotoClicked(output.photo.title)
        }
}
