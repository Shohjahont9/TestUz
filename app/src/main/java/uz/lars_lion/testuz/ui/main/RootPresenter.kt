package uz.lars_lion.testuz.ui.main

import androidx.lifecycle.Lifecycle
import com.badoo.ribs.android.subscribe
import com.badoo.ribs.core.plugin.ViewAware

interface HelloWorldPresenter {

    fun onShowCountClicked()
}

internal class HelloWorldPresenterImpl(
    private val greeting: String
) : HelloWorldPresenter, ViewAware<RootView> {

    private var view: RootView? = null
    private var count: Int = 0

    override fun onViewCreated(view: RootView, viewLifecycle: Lifecycle) {
        view.setData(greeting)
        viewLifecycle.subscribe(
            onCreate = { this@HelloWorldPresenterImpl.view = view },
            onDestroy = { this@HelloWorldPresenterImpl.view = null }
        )
    }

    override fun onShowCountClicked() {
        view?.showCount(++count)
    }
}