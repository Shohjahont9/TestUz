package uz.lars_lion.testuz.ui.main

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.view.ViewFactory

internal class RootMainNode(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<RootView>?,
    plugins: List<HelloWorldPresenterImpl>
) : Node<RootView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), RootMain