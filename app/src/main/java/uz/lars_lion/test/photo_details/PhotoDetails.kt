package uz.lars_lion.test.photo_details

import com.badoo.ribs.annotation.ExperimentalApi
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import uz.lars_lion.test.photo_details.PhotoDetails.Input
import uz.lars_lion.test.photo_details.PhotoDetails.Output
import uz.lars_lion.test.photo_details.routing.PhotoDetailsRouter
import com.badoo.ribs.portal.CanProvidePortal
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.badoo.ribs.rx2.clienthelper.connector.Connectable

interface PhotoDetails : Rib, Connectable<Input, Output> {

    @ExperimentalApi
    interface Dependency : CanProvidePortal {
        val photoDetailsDataSource: PhotoDetailsDataSource
    }

    sealed class Input

    sealed class Output

    @ExperimentalApi
    class Customisation(
        val viewFactory: PhotoDetailsView.Factory = PhotoDetailsViewImpl.Factory(),
        val transitionHandler: TransitionHandler<PhotoDetailsRouter.Configuration>? = null
    ) : RibCustomisation
}
