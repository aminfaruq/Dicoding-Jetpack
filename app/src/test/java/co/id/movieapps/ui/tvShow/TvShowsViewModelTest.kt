package co.id.movieapps.ui.tvShow

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowsViewModelTest {

    /**
     * Melalukan pengujian terhadap viewModel apakah espektasi datanya
     * sesuai dengan yang di inginkan*/

    private lateinit var viewModel: TvShowsViewModel

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel()
    }

    @Test
    fun getTvShow() {
        val tvEntity = viewModel.getTvShow()
        assertNotNull(tvEntity)
        assertEquals(10, tvEntity.size)
    }
}