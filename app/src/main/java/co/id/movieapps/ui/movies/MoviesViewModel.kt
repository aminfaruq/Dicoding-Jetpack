package co.id.movieapps.ui.movies

import androidx.lifecycle.ViewModel
import co.id.movieapps.data.MovieEntity
import co.id.movieapps.utils.DataDummy

class MoviesViewModel : ViewModel() {

    fun getMovies(): List<MovieEntity> = DataDummy.generateDummyMovie()
}
