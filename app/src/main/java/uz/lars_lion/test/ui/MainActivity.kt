package uz.lars_lion.test.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.modality.BuildContext.Companion.root
import dagger.hilt.android.AndroidEntryPoint
import uz.lars_lion.test.R
import uz.lars_lion.test.model.ArticleLocal
import uz.lars_lion.test.ui.main.HelloWorldBuilder
import uz.lars_lion.test.ui.main.MainStateEvent
import uz.lars_lion.test.ui.main.MainViewModel
import uz.lars_lion.test.ui.main.RootMain
import uz.lars_lion.test.utils.DataState

@AndroidEntryPoint
class MainActivity : RibActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetArticleEvents)
    }

    override val rootViewGroup: ViewGroup
        get() = findViewById(R.id.root)

    private val deps = object : RootMain.Dependency {
        override val name: String= "Shohjahon"
    }

    override fun createRib(savedInstanceState: Bundle?): Rib =
        HelloWorldBuilder(
            object : RootMain.Dependency {
                override val name: String = "Shohjahon"
            }
        ).build(
            root(savedInstanceState)
        )


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