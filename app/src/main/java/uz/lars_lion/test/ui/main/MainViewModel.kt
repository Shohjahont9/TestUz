package uz.lars_lion.test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.lars_lion.test.model.ArticleLocal
import uz.lars_lion.test.repository.MainRepository
import uz.lars_lion.test.utils.DataState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<ArticleLocal>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<ArticleLocal>>> = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) = viewModelScope.launch {
        when (mainStateEvent) {
            is MainStateEvent.GetArticleEvents -> {
                mainRepository.getArticles().onEach { dataState ->
                    _dataState.value = dataState
                }.launchIn(viewModelScope)
            }

            is MainStateEvent.None -> {
                println("None")
            }
        }
    }
}

sealed class MainStateEvent {
    object GetArticleEvents : MainStateEvent()
    object None : MainStateEvent()
}