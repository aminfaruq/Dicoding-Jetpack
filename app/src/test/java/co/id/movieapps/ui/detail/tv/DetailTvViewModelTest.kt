package co.id.movieapps.ui.detail.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.id.movieapps.data.entity.domain.movie.GenresItemDomain
import co.id.movieapps.data.entity.domain.tv.ResponseDetailTvDomain
import co.id.movieapps.data.repository.DetailTvRepository
import co.id.movieapps.data.repository.GenreTvRepository
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
class DetailTvViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailTvViewModel

    @Mock
    private lateinit var responseTvShow: ResponseDetailTvDomain

    @Mock
    private lateinit var responseGenreTv: GenresItemDomain

    @Mock
    private lateinit var genreRepository: GenreTvRepository

    @Mock
    private lateinit var tvRepository: DetailTvRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailTvViewModel(tvRepository, genreRepository)
    }

    @Test
    fun getDetailTv() {
        val tvResponse = responseTvShow
        runBlocking {
            `when`(tvRepository.getDetailTvShow(2734, Constant.API_KEY, Constant.LANG))
        }.thenReturn(Single.just(tvResponse))
        viewModel.getDetailTvShow(2734)
    }

    @Test
    fun getDetailGenreTv() {
        val tvGenre = responseGenreTv
        runBlocking {
            `when`(genreRepository.getGenreTv(2734, Constant.API_KEY, Constant.LANG))
        }.thenReturn(Single.just(listOf(tvGenre)))
        viewModel.getGenreTvShow(2734)
    }

}