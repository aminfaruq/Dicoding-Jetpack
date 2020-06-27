package co.id.movieapps.ui.detail.tv

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.movieapps.R
import co.id.movieapps.data.entity.domain.tv.ResponseDetailTvDomain
import co.id.movieapps.ui.detail.movie.GenreListItemView
import co.id.movieapps.utils.ui.loadImage
import co.id.movieapps.utils.ui.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_detail_tv.*
import org.koin.android.ext.android.inject

class DetailTvActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_TV = "tv"
    }

    private val viewModel: DetailTvViewModel by inject()
    private val adapterGenre = GroupAdapter<ViewHolder>()
    var dataTvShow: ResponseDetailTvDomain? = null
    var tvId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv)
        val extras = intent.extras
        tvId = extras?.getInt(EXTRA_TV)
        viewModel.detailGenreTvState.observe(this, genreObserver)
        viewModel.detailTvShowState.observe(this, startObserver)
        favTvShowDetail.setOnClickListener(this)
        imageBack.setOnClickListener(this)
        tvId?.let { viewModel.getDetailTvShow(it) }
        tvId?.let { viewModel.getGenreTvShow(it) }
        tvId?.let { viewModel.checkFavTvShow(it) }
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
    private val startObserver = Observer<DetailTvShowState> { detailTvShowState ->
        when (detailTvShowState) {

            is LoadingTvShowState -> {
                progressBarHolderLoadingCL.visibility = View.VISIBLE
                imageView.visibility = View.GONE
                textView5.visibility = View.GONE
            }

            is DetailTvShowDataLoaded -> {
                progressBarHolderLoadingCL.visibility = View.GONE
                dataTvShow = detailTvShowState.detailTvShowDomain
                val dataTvShow = detailTvShowState.detailTvShowDomain
                dataTvShow.posterPath?.let { img_tvShow.loadImage(it) }
                tv_title_tv.text = dataTvShow.originalName
                tv_language_tv.text = "Language ${dataTvShow.originalLanguage}"
                tv_popularity_tv.text = dataTvShow.popularity.toString()
                tv_tagline_tv.visibility = View.GONE
                tv_status.text = dataTvShow.status
                tv_desc_detail_tv.text = dataTvShow.overview
                title = dataTvShow.originalName
                imageView.visibility = View.VISIBLE
                textView5.visibility = View.VISIBLE

            }

            is DataTvNotFoundState -> {
                toast(this, "Tidak ada data saat ini")
            }

            is FavTvShowSave -> {
                favTvShowDetail.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_bookmark
                )
                Toast.makeText(this, "Berhasil di tambahkan", Toast.LENGTH_SHORT).show()
                progressBarHolderLoadingCL.visibility = View.GONE
            }

            is RemoveTvShowFav -> {
                favTvShowDetail.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_bookmark_border
                )
                Toast.makeText(this, "Berhasil di hapus", Toast.LENGTH_SHORT).show()
                progressBarHolderLoadingCL.visibility = View.GONE
            }

            is FavTvShowDataFound -> {
                detailTvShowState.detailTvShowDomain.map {
                    if (it.id == tvId) {
                        favTvShowDetail.background = ContextCompat.getDrawable(
                            this,
                            R.drawable.ic_bookmark
                        )
                    }
                }
            }

            is ErrorState -> {
                progressBarHolderLoadingCL.visibility = View.GONE
                imageView.visibility = View.GONE
                textView5.visibility = View.GONE
            }


        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.favTvShowDetail -> {
                if (favTvShowDetail.background.constantState == ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_bookmark_border
                    )?.constantState
                ) {
                    dataTvShow?.let { viewModel.saveFavTvShow(it) }
                } else {
                    dataTvShow?.id?.let { viewModel.removeTvShowFav(it) }
                }
            }

            R.id.imageBack -> {
                this.onBackPressed()
            }
        }
    }
}
