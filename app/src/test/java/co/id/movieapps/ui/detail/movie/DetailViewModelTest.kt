package co.id.movieapps.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.id.movieapps.data.entity.domain.movie.GenresItemDomain
import co.id.movieapps.data.entity.domain.movie.ResponseDetailMovieDomain
import co.id.movieapps.data.local.dao.MoviesDao
import co.id.movieapps.data.repository.DetailRepository
import co.id.movieapps.data.repository.GenreMovieRepository
import co.id.movieapps.ui.base.Constant
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var response: ResponseDetailMovieDomain

    @Mock
    private lateinit var responseGenreDomain: GenresItemDomain

    @Mock
    private lateinit var genreRepository: GenreMovieRepository

    @Mock
    private lateinit var repository: DetailRepository

    @Mock
    private lateinit var movieDao: MoviesDao

    @Mock
    private lateinit var executor: Executor


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailViewModel(repository, genreRepository, movieDao, executor)
    }

    @Test
    fun getDetailMovies() {
        val moviesResponse = response
        runBlocking {
            `when`(repository.getDetailMovie(475430, Constant.API_KEY, Constant.LANG))
        }.thenReturn(Single.just(moviesResponse))
        viewModel.getDetailMovie(475430)

    }

    @Test
    fun getDetailGenre() {
        val genreResponse = responseGenreDomain
        runBlocking {
            `when`(genreRepository.getGenreMovie(475430, Constant.API_KEY, Constant.LANG))
        }.thenReturn(Single.just(listOf(genreResponse)))
        viewModel.getGenreMovie(475430)
    }

}