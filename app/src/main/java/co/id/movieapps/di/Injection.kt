package co.id.movieapps.di

import co.id.movieapps.data.mapper.*
import co.id.movieapps.data.remote.ApiInterface
import co.id.movieapps.data.remote.BaseInterceptor
import co.id.movieapps.data.repository.*
import co.id.movieapps.ui.base.Constant
import co.id.movieapps.ui.detail.movie.DetailViewModel
import co.id.movieapps.ui.detail.tv.DetailTvViewModel
import co.id.movieapps.ui.movies.MoviesViewModel
import co.id.movieapps.ui.tvShow.TvShowsViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { BaseInterceptor() }
    single { createOkHttpClient(get()) }
    single { createWebService<ApiInterface>(get(), Constant.BASE_URL) }
}

val dataModule = module {
    //Repository
    single { MovieRepositoryImpl(get(), get()) as MovieRepository }
    single { TvShowRepositoryImpl(get(), get()) as TvShowRepository }
    single { DetailMovieRepositoryImpl(get(), get()) as DetailRepository }
    single { GenreMovieRepositoryImpl(get(), get()) as GenreMovieRepository }
    single { GenreTvRepositoryImpl(get(), get()) as GenreTvRepository }
    single { DetailTvRepositoryImpl(get(), get()) as DetailTvRepository }

    //Mapper
    single { MovieMapper() }
    single { TvShowMapper() }
    single { DetailMovieMapper() }
    single { GenreItemMapper() }
    single { DetailTvMapper() }

    //ViewModel
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowsViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { DetailTvViewModel(get(), get()) }

}

fun createOkHttpClient(interceptor: BaseInterceptor): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val timeout = 60L
    return OkHttpClient.Builder()
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(interceptor)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

val myAppModule = listOf(appModule, dataModule)