package uz.lars_lion.test.ui.main

import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.Group
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
import uz.lars_lion.test.ui.main.item.ArticleListItem
import java.util.function.Consumer

@RequiresApi(Build.VERSION_CODES.N)
interface RootView : RibView, Consumer<RootView.ViewModel>,
    ObservableSource<RootView.Event> {

    interface Factory : ViewFactoryBuilder<Dependency, RootView>
    sealed class Event {
        object ScrolledToTheEnd : Event()
        object RetryInitialLoadingClicked : Event()
        object RetryNextPageLoadingClicked : Event()
        data class PhotoClicked(val article: ArticleLocal) : Event()
    }

    sealed class ViewModel {

        data class Loaded(val article: List<ArticleLocal>) : ViewModel()
        data class LoadingNext(val article: List<ArticleLocal>) : ViewModel()
        data class LoadingNextError(val article: List<ArticleLocal>) : ViewModel()
        object InitialLoading : ViewModel()
        object InitialLoadingError : ViewModel()

    }

    interface Dependency {
        val presenter: HelloWorldPresenter
    }

}

class HelloWorldViewImpl private constructor(
    override val androidView: ViewGroup,
    private val presenter: HelloWorldPresenter,
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

    private val initialLoader = findViewById<ProgressBar>(R.id.photoFeed_initialLoader)
    private val initialLoadingError = findViewById<Group>(R.id.photoFeed_initialLoadingError)
    private val retryInitialLoading = findViewById<Button>(R.id.photoFeed_retryInitialLoading)
    init {
        rvCard.adapter = adapter
        (rvCard.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        retryInitialLoading.setOnClickListener {
            events.accept(RootView.Event.RetryInitialLoadingClicked)
        }    }

    override fun accept(vm: RootView.ViewModel) {
        when (vm) {
            is RootView.ViewModel.Loaded -> showLoaded(vm)
            is RootView.ViewModel.InitialLoading -> showInitialLoading()
            is RootView.ViewModel.LoadingNext -> showNextPageLoading(vm)
            is RootView.ViewModel.LoadingNextError -> showNextPageLoadingError(vm)
            is RootView.ViewModel.InitialLoadingError -> showInitialLoadingError()
        }
    }

    private fun showNextPageLoadingError(vm: RootView.ViewModel.LoadingNextError) {
        showListItems(
            vm.article.toItems() + listOf(
                ArticleListItem.NextPageLoadingErrorItem {
                    events.accept(RootView.Event.RetryNextPageLoadingClicked)
                }
            )
        )
    }

    private fun showNextPageLoading(vm: RootView.ViewModel.LoadingNext) {
        showListItems(
            vm.article.toItems() + listOf(ArticleListItem.NextPageLoadingItem)
        )
    }

    private fun showLoaded(vm: RootView.ViewModel.Loaded) {
        showListItems(vm.article.toItems()) {
            events.accept(RootView.Event.ScrolledToTheEnd)
        }
    }

    private fun showListItems(items: List<ArticleListItem>, onEndReached: (() -> Unit)? = null) {
        adapter.submitList(items)
        initialLoader.visibility = View.GONE
        initialLoadingError.visibility = View.GONE
        rvCard.visibility = View.VISIBLE

        if (onEndReached != null) {
            rvCard.addOnScrollListener(
                object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        val endReached = !recyclerView.canScrollVertically(1)
                        if (endReached) {
                            onEndReached.invoke()
                        }
                    }
                }
            )
        } else {
            rvCard.clearOnScrollListeners()
        }

    }

    private fun showInitialLoadingError() {
        initialLoader.visibility = View.GONE
        rvCard.visibility = View.GONE
        initialLoadingError.visibility = View.VISIBLE
    }

    private fun showInitialLoading() {
        rvCard.visibility = View.GONE
        initialLoadingError.visibility = View.GONE
        initialLoader.visibility = View.VISIBLE
    }

    private fun List<ArticleLocal>.toItems(): List<ArticleListItem> = map {
        ArticleListItem.ArticleItem(
            article = it,
            onClicked = { events.accept(RootView.Event.PhotoClicked(it)) }
        )

    }


}