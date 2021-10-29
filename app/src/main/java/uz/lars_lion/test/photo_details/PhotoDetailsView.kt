package uz.lars_lion.test.photo_details

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer
import uz.lars_lion.test.R
import uz.lars_lion.test.network.response.Article
import uz.lars_lion.test.network.response.NewsResponse
import uz.lars_lion.test.photo_details.PhotoDetailsView.Event
import uz.lars_lion.test.photo_details.PhotoDetailsView.ViewModel

interface PhotoDetailsView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event {
        object LikeClicked : Event()
    }

    sealed class ViewModel {
        object Loading : ViewModel()
        data class Loaded(val detail: List<Article>) : ViewModel()
    }

    interface Factory : ViewFactoryBuilder<Nothing?, PhotoDetailsView>
}


class PhotoDetailsViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : AndroidRibView(),
    PhotoDetailsView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_news
    ) : PhotoDetailsView.Factory {
        override fun invoke(deps: Nothing?): ViewFactory<PhotoDetailsView> = ViewFactory {
            PhotoDetailsViewImpl(
                it.inflate(layoutRes)
            )
        }
    }

//    private val loader = findViewById<ProgressBar>(R.id.photoDetail_loader)
//    private val photo = findViewById<ImageView>(R.id.photoDetail_photo)
//    private val userName = findViewById<TextView>(R.id.photoDetail_userName)
//    private val like = findViewById<FloatingActionButton>(R.id.photoDetail_like)
//    private val contentGroup = findViewById<Group>(R.id.photoDetail_content)


    override fun accept(vm: ViewModel) {
        when (vm) {
            is ViewModel.Loading -> showLoading()
            is ViewModel.Loaded -> showPhoto(vm.detail)
        }
    }

    private fun showPhoto(detail: List<Article>) {
//        contentGroup.visibility = VISIBLE
//        loader.visibility = GONE
//        photo.load(detail.articles[position].urlToImage)
//        userName.text = detail.user.username
//        like.setImageResource(if (detail.likedByUser) R.drawable.ic_like else R.drawable.ic_like_empty)
//        like.setOnClickListener {
//            events.accept(Event.LikeClicked)
//        }
    }

    private fun showLoading() {
//        loader.visibility = VISIBLE
//        contentGroup.visibility = GONE
    }
}
