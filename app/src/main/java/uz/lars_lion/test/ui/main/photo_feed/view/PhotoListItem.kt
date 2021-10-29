package uz.lars_lion.test.ui.main.photo_feed.view

import uz.lars_lion.test.network.response.Article

sealed class PhotoListItem(val title: String) {
    data class PhotoItem(val article: Article, val onClicked: (Article) -> Unit) : PhotoListItem(article.title)
//    object NextPageLoadingItem : PhotoListItem("nextPageLoading")
//    data class NextPageLoadingErrorItem(val onClicked: () -> Unit) :
//        PhotoListItem("nextPageLoadingError")
}
