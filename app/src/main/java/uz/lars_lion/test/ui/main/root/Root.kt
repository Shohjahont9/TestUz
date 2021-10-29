package uz.lars_lion.test.ui.main.root

import com.badoo.ribs.annotation.ExperimentalApi
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import uz.lars_lion.test.network.ApiService
import uz.lars_lion.test.ui.main.root.Root.Input
import uz.lars_lion.test.ui.main.root.Root.Output
import uz.lars_lion.test.ui.main.root.routing.RootRouter

interface Root : Rib, Connectable<Input, Output> {

    interface Dependency  {
        val api: ApiService
    }

    sealed class Input

    sealed class Output

    @ExperimentalApi
    class Customisation(
        val transitionHandler: TransitionHandler<RootRouter.Configuration>? = null
    ) : RibCustomisation
}
