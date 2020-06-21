package co.id.movieapps.ui.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.id.movieapps.data.entity.domain.tv.TvShowDomain
import co.id.movieapps.data.repository.TvShowRepository
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
class TvShowsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TvShowsViewModel

    @Mock
    private lateinit var response: TvShowDomain

    @Mock
    private lateinit var repository: TvShowRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TvShowsViewModel(repository)
    }

    @Test
    fun getTvShow() {
        val tvShowResponse = response
        runBlocking {
            `when`(
                repository.getTvShow(Constant.API_KEY, Constant.LANG, Constant.SORT_BY, 1)
            )
        }.thenReturn(Single.just(listOf(response)))
        viewModel.getTvShow(1)
    }
}