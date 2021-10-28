package uz.lars_lion.test.ui.main

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams

class HelloWorldBuilder(
    private val dependency: RootMain.Dependency
) : SimpleBuilder<RootMain>() {

    override fun build(buildParams: BuildParams<Nothing?>): RootMain {
        val presenter = HelloWorldPresenterImpl(greeting = "Hello ${dependency.name}!")
        val viewDependencies: RootView.Dependency = object : RootView.Dependency {
            override val presenter: HelloWorldPresenter = presenter
        }
        return RootMainNode(
            buildParams = buildParams,
            viewFactory = HelloWorldViewImpl.Factory().invoke(deps = viewDependencies),
            plugins = listOf(presenter)
        )
    }
}

