package uz.lars_lion.test.feed_container.routing

import uz.lars_lion.test.feed_container.FeedContainer
import uz.lars_lion.test.ui.main.photo_feed.PhotoFeed
import uz.lars_lion.test.ui.main.photo_feed.PhotoFeedBuilder
import uz.lars_lion.test.ui.main.photo_feed.PhotoFeedDataSource
import uz.lars_lion.test.ui.main.photo_feed.PhotoFeedDataSourceImpl

internal class FeedContainerChildBuilders(
    dependency: FeedContainer.Dependency
) {
    private val subtreeDeps = SubtreeDependency(dependency)

    val photoFeedBuilder = PhotoFeedBuilder(subtreeDeps)

    class SubtreeDependency(
        dependency: FeedContainer.Dependency
    ) : FeedContainer.Dependency by dependency, PhotoFeed.Dependency {
        override val dataSource: PhotoFeedDataSource = PhotoFeedDataSourceImpl(api)
    }
}



