package co.id.movieapps.ui.detail.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.movieapps.R
import co.id.movieapps.utils.loadImage
import co.id.movieapps.utils.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject


class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "movie"
    }

    private val viewModel: DetailViewModel by inject()
    private val adapterGenre = GroupAdapter<ViewHolder>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val extras = intent.extras
        val movieId = extras?.getInt(EXTRA_MOVIE)
        viewModel.detailMovieState.observe(this, startObserver)
        viewModel.detailGenreMovieState.observe(this, genreObserver)
        movieId?.let { viewModel.getGenreMovie(it) }
        movieId?.let { viewModel.getDetailMovie(it) }
        setUpRV()

    }

    private fun setUpRV() {
        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_genre.apply {
            layoutManager = linearLayout
            adapter = adapterGenre
        }

    }

    private val genreObserver = Observer<GenreMovieState> { genreMovieState ->
        when (genreMovieState) {

            is GenreListLoaded -> {
                genreMovieState.detailGenreDomain.map {
                    adapterGenre.add(
                        GenreListItemView(
                            it
                        )
                    )
                }
            }

            is DataGenreNotFoundState -> {
                toast(this, "Tidak ada genre")
            }

            is ErrorGenreState -> {

            }

        }

    }

    @SuppressLint("SetTextI18n")
    private val startObserver = Observer<DetailMovieState> { detailMovieState ->
        when (detailMovieState) {

            is LoadingState -> {
                progressBarHolderLoadingCL.visibility = View.VISIBLE
                imageView.visibility = View.GONE
                textView5.visibility = View.GONE
            }

            is DetailMovieDataLoaded -> {
                progressBarHolderLoadingCL.visibility = View.GONE

                val dataMovie = detailMovieState.detailMovieDomain
                dataMovie.posterPath?.let { img_movie.loadImage(it) }
                tv_title.text = dataMovie.originalTitle
                tv_language.text = "Language ${dataMovie.originalLanguage}"
                tv_popularity.text = dataMovie.popularity.toString()
                tv_tagline.text = "\u0022${dataMovie.tagline}\u0022"
                tv_status.text = dataMovie.status
                tv_desc_detail.text = dataMovie.overview
                title = dataMovie.originalTitle
                imageView.visibility = View.VISIBLE
                textView5.visibility = View.VISIBLE

            }

            is DataNotFoundState -> {
                toast(this, "Tidak ada data saat ini")
            }

            is ErrorState -> {
                progressBarHolderLoadingCL.visibility = View.GONE
                imageView.visibility = View.GONE
                textView5.visibility = View.GONE
            }


        }
    }

}
