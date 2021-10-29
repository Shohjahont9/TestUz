package uz.lars_lion.test.ui.main.root.routing

import com.badoo.ribs.annotation.ExperimentalApi
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import uz.lars_lion.test.ui.main.root.Root
import com.badoo.ribs.portal.Portal
import com.badoo.ribs.portal.PortalBuilder
import com.badoo.ribs.routing.resolution.ChildResolution.Companion.child
import com.badoo.ribs.routing.resolution.Resolution
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import uz.lars_lion.test.logged_in_container.LoggedInContainer
import uz.lars_lion.test.logged_in_container.LoggedInContainerBuilder

@ExperimentalApi
internal class RootChildBuilders(
    dependency: Root.Dependency,
) {
    private val subtreeDeps = SubtreeDependency(dependency)

    val loggedInContainerBuilder: PortalBuilder =
        PortalBuilder(
            object : Portal.Dependency {
                override val defaultResolution: (Portal.OtherSide) -> Resolution =
                    { portal ->
                        child { loggedInContainerBuilder(portal).build(it) }
                    }
            }
        )

//    val loginBuilder: LoginBuilder = LoginBuilder(subtreeDeps)

    private fun loggedInContainerBuilder(
        portal: Portal.OtherSide
    ): LoggedInContainerBuilder {
        return LoggedInContainerBuilder(
            dependency = object : Root.Dependency by subtreeDeps,
                LoggedInContainer.Dependency {
                override val portal: Portal.OtherSide = portal

            }
        )
    }

    private class SubtreeDependency(
        dependency: Root.Dependency,
    ) : Root.Dependency by dependency,
        Login.Dependency
}


interface Login : Rib, Connectable<Login.Input, Login.Output> {

    interface Dependency  {
    }

    sealed class Input

    sealed class Output

    class Customisation : RibCustomisation
}

