package uz.lars_lion.test.ui.main.photo_feed.feature

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import uz.lars_lion.test.network.response.Article
import uz.lars_lion.test.ui.main.photo_feed.PhotoFeedDataSource
import uz.lars_lion.test.ui.main.photo_feed.feature.PhotoFeedFeature.*
import uz.lars_lion.test.utils.toObservable

internal class PhotoFeedFeature(
    dataSource: PhotoFeedDataSource
) : ActorReducerFeature<Wish, Effect, State, News>(
    initialState = State.Loaded(emptyList()),
    bootstrapper = BootStrapperImpl(),
    actor = ActorImpl(dataSource),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    sealed class State(
        open val photos: List<Article> = emptyList()
    ) {
        object InitialLoading : State()
        data class Loaded(
            override val photos: List<Article>,
        ) : State(photos)

    }

    sealed class Wish {
        object LoadNextPage : Wish()
    }

    sealed class Effect {
        object LoadingStarted : Effect()
        data class PageLoaded(val photos: List<Article>) : Effect()

    }

    sealed class News

    class BootStrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> = Wish.LoadNextPage.toObservable()
    }

    class ActorImpl(
        private val dataSource: PhotoFeedDataSource
    ) : Actor<State, Wish, Effect> {
        override fun invoke(state: State, wish: Wish): Observable<Effect> =
            when (wish) {
                is Wish.LoadNextPage ->
                    when (state) {
                        is State.InitialLoading -> Observable.empty()
                        else -> loadPage()
                    }
            }

        private fun loadPage(): Observable<Effect> {
            return dataSource.loadPhotos()
                .map<Effect> { photos ->
                    Effect.PageLoaded(photos.articles)
                }
                .toObservable()
                .startWith(Effect.LoadingStarted)
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State =
            when (effect) {
                is Effect.PageLoaded -> State.Loaded(
                    state.photos + effect.photos
                )
                is Effect.LoadingStarted -> when {
                    state is State.InitialLoading || state.photos.isEmpty() -> State.InitialLoading
                    else -> State.Loaded(state.photos)
                }
            }
    }

    class NewsPublisherImpl : NewsPublisher<Wish, Effect, State, News> {
        override fun invoke(wish: Wish, effect: Effect, state: State): News? =
            null
    }
}
