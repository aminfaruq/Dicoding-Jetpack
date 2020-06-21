package co.id.movieapps.ui.detail.tv

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.movieapps.R
import co.id.movieapps.ui.detail.movie.GenreListItemView
import co.id.movieapps.utils.loadImage
import co.id.movieapps.utils.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_detail_tv.*
import org.koin.android.ext.android.inject

class DetailTvActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV = "tv"
    }

    private val viewModel: DetailTvViewModel by inject()
    private val adapterGenre = GroupAdapter<ViewHolder>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv)
        val extras = intent.extras
        val tvId = extras?.getInt(EXTRA_TV)
        viewModel.detailGenreTvState.observe(this, genreObserver)
        viewModel.detailTvShowState.observe(this, startObserver)
        tvId?.let { viewModel.getDetailTvShow(it) }
        tvId?.let { viewModel.getGenreTvShow(it) }
        setUpRV()
    }

    private fun setUpRV() {
        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_genre_tv.apply {
            layoutManager = linearLayout
            adapter = adapterGenre
        }

    }

    private val genreObserver = Observer<GenreTvState> { genreMovieState ->
        when (genreMovieState) {

            is GenreListTvLoaded -> {
                genreMovieState.detailGenreDomain.map {
                    adapterGenre.add(
                        GenreListItemView(
                            it
                        )
                    )
                }
            }

            is DataGenreTvNotFoundState -> {
                toast(this, "Tidak ada genre")
            }

            is ErrorGenreTvState -> {

            }

        }

    }


    @SuppressLint("SetTextI18n")
    private val startObserver = Observer<DetailTvShowState> { detailMovieState ->
        when (detailMovieState) {

            is LoadingTvShowState -> {
                progressBarHolderLoadingCL.visibility = View.VISIBLE
                imageView.visibility = View.GONE
                textView5.visibility = View.GONE
            }

            is DetailTvShowDataLoaded -> {
                progressBarHolderLoadingCL.visibility = View.GONE

                val dataMovie = detailMovieState.detailTvShowDomain
                dataMovie.posterPath?.let { img_tvShow.loadImage(it) }
                tv_title_tv.text = dataMovie.originalName
                tv_language_tv.text = "Language ${dataMovie.originalLanguage}"
                tv_popularity_tv.text = dataMovie.popularity.toString()
                tv_tagline_tv.visibility = View.GONE
                tv_status.text = dataMovie.status
                tv_desc_detail_tv.text = dataMovie.overview
                title = dataMovie.originalName
                imageView.visibility = View.VISIBLE
                textView5.visibility = View.VISIBLE

            }

            is DataTvNotFoundState -> {
                toast(this, "Tidak ada data saat ini")
            }

            is co.id.movieapps.ui.detail.tv.ErrorState -> {
                progressBarHolderLoadingCL.visibility = View.GONE
                imageView.visibility = View.GONE
                textView5.visibility = View.GONE
            }


        }
    }
}
