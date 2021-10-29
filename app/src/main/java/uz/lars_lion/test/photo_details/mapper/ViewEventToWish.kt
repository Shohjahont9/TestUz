package uz.lars_lion.test.photo_details.mapper

import uz.lars_lion.test.photo_details.PhotoDetailsView.Event
import uz.lars_lion.test.photo_details.feature.PhotoDetailsFeature.Wish

internal object ViewEventToWish : (Event) -> Wish? {

    override fun invoke(event: Event): Wish? =
        when (event) {
            is Event.LikeClicked -> {null}
        }
}
