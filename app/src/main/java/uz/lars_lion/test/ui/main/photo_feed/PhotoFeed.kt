package uz.lars_lion.test.ui.main.photo_feed

import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import uz.lars_lion.test.network.response.Article
import uz.lars_lion.test.ui.main.photo_feed.PhotoFeed.Input
import uz.lars_lion.test.ui.main.photo_feed.PhotoFeed.Output
import uz.lars_lion.test.ui.main.photo_feed.view.PhotoFeedView
import uz.lars_lion.test.ui.main.photo_feed.view.PhotoFeedViewImpl

interface PhotoFeed : Rib, Connectable<Input, Output> {

    interface Dependency {
        val dataSource: PhotoFeedDataSource
    }

    sealed class Input

    sealed class Output {
        data class PhotoClicked(val photo: Article): Output()
    }

    class Customisation(
        val viewFactory: PhotoFeedView.Factory = PhotoFeedViewImpl.Factory()
    ) : RibCustomisation
}
