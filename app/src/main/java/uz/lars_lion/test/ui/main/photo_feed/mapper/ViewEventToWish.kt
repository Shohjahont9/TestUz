package uz.lars_lion.test.ui.main.photo_feed.mapper

import uz.lars_lion.test.ui.main.photo_feed.feature.PhotoFeedFeature.Wish
import uz.lars_lion.test.ui.main.photo_feed.view.PhotoFeedView.Event

internal object ViewEventToWish : (Event) -> Wish? {

    override fun invoke(event: Event): Wish? =
        when (event) {
            is Event.RetryNextPageLoadingClicked -> Wish.LoadNextPage
            else -> null
        }
}
