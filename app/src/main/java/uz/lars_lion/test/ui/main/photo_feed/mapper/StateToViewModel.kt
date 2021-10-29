package uz.lars_lion.test.ui.main.photo_feed.mapper

import uz.lars_lion.test.ui.main.photo_feed.feature.PhotoFeedFeature.State
import uz.lars_lion.test.ui.main.photo_feed.view.PhotoFeedView.ViewModel

internal object StateToViewModel : (State) -> ViewModel {

    override fun invoke(state: State): ViewModel =
        when (state) {
            is State.InitialLoading -> ViewModel.InitialLoading
            is State.Loaded -> ViewModel.Loaded(state.photos)
        }
}
