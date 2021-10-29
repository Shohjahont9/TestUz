package uz.lars_lion.test.network

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import uz.lars_lion.test.utils.Constants

object ApiFactory {
    private const val BASE_URL = Constants.BASE_URL
    fun api(
    ): ApiService =
        retrofit(
            client = okhttpClient(),
            baseUrl = BASE_URL
        ).create(ApiService::class.java)

    private fun okhttpClient(
    ): OkHttpClient =
        OkHttpClient.Builder()
            .build()

    private fun retrofit(client: OkHttpClient, baseUrl: String): Retrofit = Retrofit.Builder()
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()


}
