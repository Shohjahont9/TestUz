package uz.lars_lion.test.ui.main.root

import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import uz.lars_lion.test.ui.main.root.Root
import uz.lars_lion.test.ui.main.root.Root.Input
import uz.lars_lion.test.ui.main.root.Root.Output

class RootNode internal constructor(
    buildParams: BuildParams<*>,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) : Node<Nothing>(
    buildParams = buildParams,
    viewFactory = null,
    plugins = plugins
), Root, Connectable<Input, Output> by connector
