package uz.lars_lion.test.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.lars_lion.test.network.ApiService
import uz.lars_lion.test.network.NetworkMapper
import uz.lars_lion.test.repository.MainRepository
import uz.lars_lion.test.room.ArticleDAO
import uz.lars_lion.test.room.CacheMapper

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        apiService: ApiService,
        articleDAO: ArticleDAO,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository = MainRepository(apiService, articleDAO, cacheMapper, networkMapper)
}