package uz.lars_lion.test.photo_details

import androidx.lifecycle.Lifecycle
import com.badoo.binder.using
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.annotation.ExperimentalApi
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import uz.lars_lion.test.photo_details.feature.PhotoDetailsFeature
import uz.lars_lion.test.photo_details.mapper.NewsToOutput
import uz.lars_lion.test.photo_details.mapper.StateToViewModel
import uz.lars_lion.test.photo_details.mapper.ViewEventToWish
import uz.lars_lion.test.photo_details.routing.PhotoDetailsRouter.Configuration
import com.badoo.ribs.routing.source.backstack.BackStack

@ExperimentalApi
internal class PhotoDetailsInteractor(
    buildParams: BuildParams<*>,
    private val backStack: BackStack<Configuration>,
    private val feature: PhotoDetailsFeature
) : Interactor<PhotoDetails, PhotoDetailsView>(
    buildParams = buildParams
) {

    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
//            bind(feature.news to rib.output using NewsToOutput)
        }
    }

    override fun onViewCreated(view: PhotoDetailsView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            bind(feature to view using StateToViewModel)
            bind(view to feature using ViewEventToWish)
        }
    }
}
