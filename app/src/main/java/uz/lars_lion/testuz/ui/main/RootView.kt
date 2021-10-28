package uz.lars_lion.testuz.ui.main

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.google.android.material.snackbar.Snackbar
import uz.lars_lion.testuz.R


interface RootView : RibView {

    interface Factory : ViewFactoryBuilder<Dependency, RootView>

    interface Dependency {
        val presenter: HelloWorldPresenter
    }

    fun setData(data: String)

    fun showCount(count: Int)
}

class HelloWorldViewImpl private constructor(
    override val androidView: ViewGroup,
    private val presenter: HelloWorldPresenter
) : AndroidRibView(), RootView {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_main
    ) : RootView.Factory {
        override fun invoke(deps: RootView.Dependency): ViewFactory<RootView> =
            ViewFactory {
                HelloWorldViewImpl(
                    androidView = it.inflate(layoutRes),
                    presenter = deps.presenter
                )
            }
    }

    private val rvCard: RecyclerView = androidView.findViewById(R.id.rv_card_news)
    private val rvItem: RecyclerView = androidView.findViewById(R.id.rv_item_news)

    init {
//        button.setOnClickListener { presenter.onShowCountClicked() }
    }

    override fun setData(data: String) {
//        textView.text = text

    }

    override fun showCount(count: Int) {
        Snackbar.make(androidView, "Current count: $count", Snackbar.LENGTH_SHORT).show()
    }
}