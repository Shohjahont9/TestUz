package uz.lars_lion.test.ui.main.photo_feed

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.binder.using
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import uz.lars_lion.test.ui.main.photo_feed.feature.PhotoFeedFeature
import uz.lars_lion.test.ui.main.photo_feed.mapper.StateToViewModel
import uz.lars_lion.test.ui.main.photo_feed.mapper.ViewEventToOutput
import uz.lars_lion.test.ui.main.photo_feed.mapper.ViewEventToWish
import uz.lars_lion.test.ui.main.photo_feed.view.PhotoFeedView

internal class PhotoFeedInteractor(
    buildParams: BuildParams<*>,
    private val feature: PhotoFeedFeature
) : Interactor<PhotoFeed, PhotoFeedView>(
    buildParams = buildParams
) {

    override fun onViewCreated(view: PhotoFeedView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
//            bind(feature to view using StateToViewModel)
            bind(view to feature using ViewEventToWish)
            bind(view to rib.output using ViewEventToOutput)
        }
    }
}


