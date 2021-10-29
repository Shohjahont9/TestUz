package uz.lars_lion.test.feed_container

import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import uz.lars_lion.test.feed_container.FeedContainer.Input
import uz.lars_lion.test.feed_container.FeedContainer.Output
import uz.lars_lion.test.feed_container.routing.FeedContainerRouter
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import uz.lars_lion.test.network.ApiService

interface FeedContainer : Rib, Connectable<Input, Output> {

    interface Dependency {
        val api: ApiService
    }

    sealed class Input

    sealed class Output {
        data class PhotoClicked(val id: String) : Output()
    }

    class Customisation(
        val viewFactory: FeedContainerView.Factory = FeedContainerViewImpl.Factory(),
        val transitionHandler: TransitionHandler<FeedContainerRouter.Configuration>? = null
    ) : RibCustomisation
}
