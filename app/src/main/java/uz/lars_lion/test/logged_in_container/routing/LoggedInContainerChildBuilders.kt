package uz.lars_lion.test.logged_in_container.routing

import com.badoo.ribs.annotation.ExperimentalApi
import uz.lars_lion.test.feed_container.FeedContainer
import uz.lars_lion.test.feed_container.FeedContainerBuilder
import uz.lars_lion.test.photo_details.PhotoDetails
import uz.lars_lion.test.photo_details.PhotoDetailsBuilder
import uz.lars_lion.test.photo_details.PhotoDetailsDataSource
import uz.lars_lion.test.photo_details.PhotoDetailsDataSourceImpl
import uz.lars_lion.test.logged_in_container.LoggedInContainer

@ExperimentalApi
internal class LoggedInContainerChildBuilders(
    dependency: LoggedInContainer.Dependency
) {
    private val subtreeDeps = SubtreeDeps(dependency)

    val photoFeedBuilder: FeedContainerBuilder = FeedContainerBuilder(subtreeDeps)
    val photoDetailsBuilder: PhotoDetailsBuilder = PhotoDetailsBuilder(subtreeDeps)

    private class SubtreeDeps(dependency: LoggedInContainer.Dependency) :
        LoggedInContainer.Dependency by dependency,
        FeedContainer.Dependency, PhotoDetails.Dependency {

        override val photoDetailsDataSource: PhotoDetailsDataSource =
            PhotoDetailsDataSourceImpl(api)
    }
}
