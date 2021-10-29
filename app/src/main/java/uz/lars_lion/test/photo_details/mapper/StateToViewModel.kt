package uz.lars_lion.test.photo_details.mapper

import uz.lars_lion.test.photo_details.PhotoDetailsView.ViewModel
import uz.lars_lion.test.photo_details.feature.PhotoDetailsFeature.State

internal object StateToViewModel : (State) -> ViewModel {

    override fun invoke(state: State): ViewModel =
        when (state) {
            is State.Loading -> ViewModel.Loading
            is State.Loaded -> ViewModel.Loaded(state.detail)
        }
}
