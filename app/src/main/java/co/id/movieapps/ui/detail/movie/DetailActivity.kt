package co.id.movieapps.ui.detail.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.movieapps.R
import co.id.movieapps.data.entity.domain.movie.ResponseDetailMovieDomain
import co.id.movieapps.utils.ui.loadImage
import co.id.movieapps.utils.ui.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject


class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_MOVIE = "movie"
    }

    private val viewModel: DetailViewModel by inject()
    private val adapterGenre = GroupAdapter<ViewHolder>()
    var dataMovie: ResponseDetailMovieDomain? = null
    var movieId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        actionBar?.hide()
        val extras = intent.extras
        movieId = extras?.getInt(EXTRA_MOVIE)
        viewModel.detailMovieState.observe(this, startObserver)
        viewModel.detailGenreMovieState.observe(this, genreObserver)
        favMovieDetail.setOnClickListener(this)
        imageView2.setOnClickListener(this)
        movieId?.let { viewModel.checkFavMovie(it) }
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
                dataMovie = detailMovieState.detailMovieDomain
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

            is FavMovieSave -> {
                favMovieDetail.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_bookmark
                )
                Toast.makeText(this, "Berhasil di tambahkan", Toast.LENGTH_SHORT).show()
                progressBarHolderLoadingCL.visibility = View.GONE

            }

            is RemoveMovieFav -> {
                favMovieDetail.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_bookmark_border
                )
                Toast.makeText(this, "Berhasil di hapus", Toast.LENGTH_SHORT).show()
                progressBarHolderLoadingCL.visibility = View.GONE
            }

            is DataNotFoundState -> {
                toast(this, "Tidak ada data saat ini")
            }

            is ErrorState -> {
                progressBarHolderLoadingCL.visibility = View.GONE
                imageView.visibility = View.GONE
                textView5.visibility = View.GONE
            }

            is FavMovieDataFound -> {
                detailMovieState.detailMovieDomain.map {
                    if (it.id == movieId) {
                        favMovieDetail.background = ContextCompat.getDrawable(
                            this,
                            R.drawable.ic_bookmark
                        )
                    }
                }
            }


        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.favMovieDetail -> {

                if (favMovieDetail.background.constantState == ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_bookmark_border
                    )?.constantState
                ) {
                    dataMovie?.let { viewModel.saveFavMovieSave(it) }
                } else {
                    dataMovie?.id?.let { viewModel.removeMovieFav(it) }
                }
            }

            R.id.imageView2 -> {
                this.onBackPressed()
            }

        }
    }

}
