package uz.lars_lion.test.ui.main.root

import com.badoo.ribs.annotation.ExperimentalApi
import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import uz.lars_lion.test.ui.main.root.routing.RootChildBuilders
import uz.lars_lion.test.ui.main.root.routing.RootRouter
import uz.lars_lion.test.ui.main.root.routing.RootRouter.Configuration

@ExperimentalApi
class RootBuilder(
    private val dependency: Root.Dependency
) : SimpleBuilder<Root>() {


    private val builders = RootChildBuilders(dependency)

    override fun build(buildParams: BuildParams<Nothing?>): Root {
        val customisation = buildParams.getOrDefault(Root.Customisation())
        val backStack = BackStack<Configuration>(
            buildParams = buildParams,
            initialConfiguration = Configuration.Content.LoggedIn
        )
        val interactor = RootInteractor(
            buildParams = buildParams,
            backStack = backStack,
        )
        val router = RootRouter(
            buildParams = buildParams,
            builders = builders,
            routingSource = backStack,
            transitionHandler = customisation.transitionHandler
        )

        return RootNode(
            buildParams = buildParams,
            plugins = listOf(interactor, router)
        )
    }

}
