package uz.lars_lion.test.ui.main.photo_feed.view

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
import uz.lars_lion.test.network.NetworkMapper
import uz.lars_lion.test.network.response.Article

@RequiresApi(Build.VERSION_CODES.N)
interface PhotoFeedView : RibView, java.util.function.Consumer<PhotoFeedView.ViewModel>,
    ObservableSource<PhotoFeedView.Event> {

    interface Factory : ViewFactoryBuilder<Nothing?, PhotoFeedView>
    sealed class Event {
        object RetryNextPageLoadingClicked : Event()
        data class ArticleClicked(val article: Article) : Event()
    }

    sealed class ViewModel {
        object InitialLoading : ViewModel()
        data class Loaded(val articles: List<Article>) : ViewModel()
    }

}

class PhotoFeedViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<PhotoFeedView.Event> = PublishRelay.create()
) : AndroidRibView(), PhotoFeedView, ObservableSource<PhotoFeedView.Event> by events,
    io.reactivex.functions.Consumer<PhotoFeedView.ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_main
    ) : PhotoFeedView.Factory {
        override fun invoke(deps: Nothing?): ViewFactory<PhotoFeedView> =
            ViewFactory {
                PhotoFeedViewImpl(
                    androidView = it.inflate(layoutRes),
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

    override fun accept(vm: PhotoFeedView.ViewModel) {
        when (vm) {
            is PhotoFeedView.ViewModel.Loaded -> showLoaded(vm)
        }
    }


    private fun showLoaded(vm: PhotoFeedView.ViewModel.Loaded) {
        showListItems(vm.articles)
    }

    private fun showListItems(items: List<Article>) {
        adapter.submitList(NetworkMapper().mapFromNetworkEntityList(items))
        rvCard.visibility = View.VISIBLE

    }


}