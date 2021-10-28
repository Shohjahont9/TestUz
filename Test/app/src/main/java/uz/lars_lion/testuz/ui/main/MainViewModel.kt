package uz.lars_lion.testuz.ui.main

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.lars_lion.testuz.model.ArticleLocal
import uz.lars_lion.testuz.repository.MainRepository
import uz.lars_lion.testuz.utils.DataState
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