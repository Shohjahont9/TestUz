package uz.lars_lion.test.ui.main

import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import uz.lars_lion.test.model.ArticleLocal
import uz.lars_lion.test.ui.main.item.PhotoFeedDataSource

interface RootMain : Rib {

    interface Dependency {
        val dataSource: PhotoFeedDataSource
    }
}

interface ArticleFeed : Rib, Connectable<ArticleFeed.Input, ArticleFeed.Output> {

    interface Dependency {
        val dataSource: PhotoFeedDataSource
    }

    sealed class Input

    sealed class Output {
        data class PhotoClicked(val photo: ArticleLocal): Output()
    }

    class Customisation(
        val viewFactory: RootView.Factory = HelloWorldViewImpl.Factory()
    ) : RibCustomisation
}
