package uz.lars_lion.test.feed_container

import androidx.lifecycle.Lifecycle
import com.badoo.binder.using
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.clienthelper.childaware.whenChildBuilt
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import uz.lars_lion.test.feed_container.mapper.FeedOutputToContainerOutput
import uz.lars_lion.test.ui.main.photo_feed.PhotoFeed

internal class FeedContainerInteractor(
    buildParams: BuildParams<*>
) : Interactor<FeedContainer, FeedContainerView>(
    buildParams = buildParams
) {

    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
        }
        whenChildBuilt<PhotoFeed>(nodeLifecycle) { commonLifecycle, child ->
            commonLifecycle.createDestroy {
                bind(child.output to rib.output using FeedOutputToContainerOutput)
            }
        }
    }

    override fun onViewCreated(view: FeedContainerView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
        }
    }

}
