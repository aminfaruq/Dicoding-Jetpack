package co.id.movieapps.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.id.movieapps.data.entity.domain.movie.MovieDomain
import co.id.movieapps.data.repository.MovieRepository
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

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MoviesViewModel

    @Mock
    private lateinit var response: MovieDomain

    @Mock
    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun getMovies() {
        val moviesResponse = response
        runBlocking {
            `when`(
                repository.getMovies(Constant.API_KEY, Constant.LANG, Constant.SORT_BY, 1)
            )
        }.thenReturn(Single.just(listOf(moviesResponse)))
        viewModel.getMovies(1)
    }

}