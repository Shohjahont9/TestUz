package uz.lars_lion.test.ui.main

import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import uz.lars_lion.test.R
import uz.lars_lion.test.adapters.CardNewsAdapter
import uz.lars_lion.test.model.ArticleLocal
import java.util.function.Consumer

@RequiresApi(Build.VERSION_CODES.N)
interface RootView : RibView, Consumer<RootView.ViewModel>,
    ObservableSource<RootView.Event> {

    interface Factory : ViewFactoryBuilder<Dependency, RootView>
    sealed class Event {
        data class ArticleClicked(val article: ArticleLocal) : Event()
    }

    sealed class ViewModel {

        data class Loaded(val article: List<ArticleLocal>) : ViewModel()

    }

    interface Dependency {
        val presenter: HelloWorldPresenter
    }

}

class HelloWorldViewImpl private constructor(
    override val androidView: ViewGroup,
    presenter: HelloWorldPresenter,
    private val events: PublishRelay<RootView.Event> = PublishRelay.create()
) : AndroidRibView(), RootView, ObservableSource<RootView.Event> by events,
    io.reactivex.functions.Consumer<RootView.ViewModel> {

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

    private val adapter = CardNewsAdapter()
    private val rvCard: RecyclerView = androidView.findViewById(R.id.rv_card_news)
    private val rvItem: RecyclerView = androidView.findViewById(R.id.rv_item_news)
    init {
        rvCard.adapter = adapter
        (rvCard.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
            }

    override fun accept(vm: RootView.ViewModel) {
        when (vm) {
            is RootView.ViewModel.Loaded -> showLoaded(vm)
        }
    }


    private fun showLoaded(vm: RootView.ViewModel.Loaded) {
        showListItems(vm.article)
    }

    private fun showListItems(items: List<ArticleLocal>) {
        adapter.submitList(items)
        rvCard.visibility = View.VISIBLE

    }



}