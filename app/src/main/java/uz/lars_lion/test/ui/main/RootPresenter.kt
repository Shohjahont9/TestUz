package uz.lars_lion.test.ui.main

import androidx.lifecycle.Lifecycle
import com.badoo.ribs.android.subscribe
import com.badoo.ribs.core.plugin.ViewAware

interface HelloWorldPresenter {
    fun onShowCountClicked()

}

internal class HelloWorldPresenterImpl(
    private val greeting: String,
) : HelloWorldPresenter, ViewAware<RootView> {

    private var view: RootView? = null

    private lateinit var viewModel: MainViewModel


    override fun onViewCreated(view: RootView, viewLifecycle: Lifecycle) {
        viewLifecycle.subscribe(
            onCreate = { this@HelloWorldPresenterImpl.view = view },
            onDestroy = { this@HelloWorldPresenterImpl.view = null }
        )


    }

    override fun onShowCountClicked() {

    }
}