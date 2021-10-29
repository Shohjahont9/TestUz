package uz.lars_lion.test.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.badoo.mvicore.android.BuildConfig
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.annotation.ExperimentalApi
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.modality.BuildContext
import com.badoo.ribs.core.modality.BuildContext.Companion.root
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.plugin.utils.debug.DebugControlsHost
import com.badoo.ribs.core.plugin.utils.debug.GrowthDirection
import com.badoo.ribs.debug.TreePrinter
import dagger.hilt.android.AndroidEntryPoint
import uz.lars_lion.test.R
import uz.lars_lion.test.di.RetrofitModule
import uz.lars_lion.test.model.ArticleLocal
import uz.lars_lion.test.network.ApiFactory
import uz.lars_lion.test.network.ApiService
import uz.lars_lion.test.ui.main.*
import uz.lars_lion.test.ui.main.root.Root
import uz.lars_lion.test.ui.main.root.RootBuilder
import uz.lars_lion.test.utils.DataState

@AndroidEntryPoint
class MainActivity : RibActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)

//        subscribeObservers()
//        viewModel.setStateEvent(MainStateEvent.GetArticleEvents)
    }

    override val rootViewGroup: ViewGroup
        get() = findViewById(R.id.root)

    @ExperimentalApi
    override fun createRib(savedInstanceState: Bundle?): Rib =
        buildRootNode(root(
            savedInstanceState = savedInstanceState,
            defaultPlugins = { node ->
                if (BuildConfig.DEBUG) {
                    listOfNotNull(
                        if (node.isRoot) createDebugControlHost() else null
                    )
                } else emptyList()
            }
        ))

    private fun createDebugControlHost(): Plugin =
        DebugControlsHost(
            viewGroupForChildren = { findViewById(R.id.debug_controls_host) },
            growthDirection = GrowthDirection.BOTTOM,
            defaultTreePrinterFormat = TreePrinter.FORMAT_SIMPLE
        )


    @ExperimentalApi
    private fun buildRootNode(
        buildContext: BuildContext
    ): Root {

        return RootBuilder(
            object : Root.Dependency {
                override val api: ApiService = api()
            }
        ).build(buildContext)
    }

    private fun api(): ApiService = ApiFactory.api()

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<ArticleLocal>> -> {

                }
                is DataState.Error -> {
                }

                is DataState.Loading -> {
                }
            }
        })
    }


}