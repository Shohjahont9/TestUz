package uz.lars_lion.test.logged_in_container

import com.badoo.ribs.annotation.ExperimentalApi
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.badoo.ribs.portal.CanProvidePortal
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import uz.lars_lion.test.logged_in_container.LoggedInContainer.Input
import uz.lars_lion.test.logged_in_container.LoggedInContainer.Output
import uz.lars_lion.test.logged_in_container.routing.LoggedInContainerRouter
import uz.lars_lion.test.network.ApiService

interface LoggedInContainer : Rib, Connectable<Input, Output> {

    @ExperimentalApi
    interface Dependency : CanProvidePortal {
        val api: ApiService
    }

    sealed class Input

    sealed class Output

    @ExperimentalApi
    class Customisation(
        val transitionHandler: TransitionHandler<LoggedInContainerRouter.Configuration>? = null
    ) : RibCustomisation
}
