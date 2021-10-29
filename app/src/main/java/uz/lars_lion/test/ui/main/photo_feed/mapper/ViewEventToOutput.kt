package uz.lars_lion.test.ui.main.photo_feed.mapper

import uz.lars_lion.test.ui.main.photo_feed.PhotoFeed.Output
import uz.lars_lion.test.ui.main.photo_feed.view.PhotoFeedView.Event

internal object ViewEventToOutput : (Event) -> Output? {
    override fun invoke(event: Event): Output? =
        when (event) {
            is Event.ArticleClicked -> Output.PhotoClicked(event.article)
            else -> null
        }

}
