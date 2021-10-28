package uz.lars_lion.testuz.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.viewModels
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.modality.BuildContext.Companion.root
import dagger.hilt.android.AndroidEntryPoint
import uz.lars_lion.testuz.R
import uz.lars_lion.testuz.ui.main.HelloWorldBuilder
import uz.lars_lion.testuz.ui.main.MainViewModel
import uz.lars_lion.testuz.ui.main.RootMain

@AndroidEntryPoint
class MainActivity : RibActivity() {

    private val viewModel: MainViewModel by viewModels()

    override val rootViewGroup: ViewGroup
        get() = findViewById(R.id.root)

    private val deps = object : RootMain.Dependency {
        override val name: String
            get() = "Shohjahon"
    }

    override fun createRib(savedInstanceState: Bundle?): Rib =
        HelloWorldBuilder(
            object : RootMain.Dependency {
                override val name: String = "Shohjahon"
            }
        ).build(
            root(savedInstanceState)
        )


//        subscribeObservers()
//        viewModel.setStateEvent(MainStateEvent.GetArticleEvents)



//    private fun subscribeObservers() {
//        viewModel.dataState.observe(this, Observer { dataState ->
//            when (dataState) {
//                is DataState.Success<List<ArticleLocal>> -> {
//                    progress_bar.visible(false)
//                    toast("Success")
//                    val listOfData = ArrayList<String>()
//                    dataState.data.forEach {
//                        listOfData.add(it.author.toString())
//                    }
//                    text.text = listOfData.toString()
//
//                }
//                is DataState.Error -> {
//                    progress_bar.visible(false)
//                    toast(dataState.exception.toString())
//                }
//
//                is DataState.Loading -> {
//                    progress_bar.visible(true)
//                    toast("Loading")
//                }
//            }
//        })
//    }

}