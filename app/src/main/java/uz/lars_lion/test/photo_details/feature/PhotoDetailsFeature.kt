package uz.lars_lion.test.photo_details.feature

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import uz.lars_lion.test.network.response.Article
import uz.lars_lion.test.photo_details.PhotoDetailsDataSource
import uz.lars_lion.test.ui.main.photo_feed.feature.PhotoFeedFeature
import uz.lars_lion.test.utils.toObservable

internal class PhotoDetailsFeature(
    photoId: String,
    photoDetailsDataSource: PhotoDetailsDataSource
) : ActorReducerFeature<PhotoDetailsFeature.Wish, PhotoDetailsFeature.Effect, PhotoDetailsFeature.State, PhotoDetailsFeature.News>(
    initialState = State.Loading,
    bootstrapper = BootStrapperImpl(),
    actor = ActorImpl(photoDetailsDataSource, photoId),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {


    sealed class State {
        object Loading : State()
        data class Loaded(val detail: List<Article>) : State()
    }

    sealed class Wish {
        object LoadPhoto : Wish()
    }

    sealed class Effect {
        object LoadingPhotoStarted : Effect()
        data class PhotoLoaded(val detail: List<Article>) : Effect()
    }

    sealed class News {
        object LikeOrUnlikeFailed : News()
    }

    class BootStrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> = Wish.LoadPhoto.toObservable()

//        override fun invoke(): Observable<Wish> = Wish.LoadPhoto.toObservable()
    }

    class ActorImpl(
        private val photoDetailsDataSource: PhotoDetailsDataSource,
        private val photoId: String
    ) : Actor<State, Wish, Effect> {
        override fun invoke(state: State, wish: Wish): Observable<out Effect> =
            when (wish) {
                Wish.LoadPhoto -> photoDetailsDataSource.getPhoto(photoId)
                    .map { Effect.PhotoLoaded(it.articles) }
                    .toObservable()
            }


    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State {
         return   when (effect) {
                is Effect.LoadingPhotoStarted -> State.Loading
                is Effect.PhotoLoaded -> State.Loaded(effect.detail)

            }
        }

    }

        class NewsPublisherImpl : NewsPublisher<Wish, Effect, State, News> {
            override fun invoke(wish: Wish, effect: Effect, state: State): News? =
                when (effect) {
                    is Effect.PhotoLoaded -> News.LikeOrUnlikeFailed
                    else -> null
                }
        }

}
