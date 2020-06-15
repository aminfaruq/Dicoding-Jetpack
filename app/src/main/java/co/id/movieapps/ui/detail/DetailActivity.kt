package co.id.movieapps.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import co.id.movieapps.R
import co.id.movieapps.data.MovieEntity
import co.id.movieapps.data.TvShowEntity
import co.id.movieapps.utils.DataDummy
import co.id.movieapps.utils.loadImage
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "movie"
        const val EXTRA_TV = "tv"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val viewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[DetailViewModel::class.java]

        val extras = intent.extras
        val movieId = extras?.getString(EXTRA_MOVIE)
        val tvId = extras?.getString(EXTRA_TV)

        if (movieId != null) {
            viewModel.setSelectedMovie(movieId)
            for (movieE in DataDummy.generateDummyMovie()) {
                if (movieE.movieId == movieId) {
                    populateMovie(viewModel.getMovie())
                }
            }
        } else if (tvId != null) {
            viewModel.setSelectedTv(tvId)
            for (tvE in DataDummy.generateDummyTvShow()) {
                if (tvE.tvId == tvId) {
                    populateTv(viewModel.getTv())
                }
            }
        }

    }

    private fun populateMovie(movieEntity: MovieEntity) {
        tv_title.text = movieEntity.title
        tv_desc_detail.text = movieEntity.description
        img_movie.loadImage(movieEntity.image)
    }

    private fun populateTv(tvShowEntity: TvShowEntity) {
        tv_title.text = tvShowEntity.title
        tv_desc_detail.text = tvShowEntity.description
        img_movie.loadImage(tvShowEntity.image)
    }
}
