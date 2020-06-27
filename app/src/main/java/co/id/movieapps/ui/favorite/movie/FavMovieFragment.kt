package co.id.movieapps.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.movieapps.R
import co.id.movieapps.data.entity.domain.movie.ResponseDetailMovieDomain
import co.id.movieapps.ui.detail.movie.DetailActivity
import co.id.movieapps.utils.room.Status
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fav_movie_fragment.*
import org.koin.android.ext.android.inject

class FavMovieFragment : Fragment() {

    companion object {
        fun newInstance() = FavMovieFragment()
    }

    private val viewModel: FavMovieViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fav_movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movieAdapter = context?.let {
            FavMovieAdapter(it, object : FavMovieAdapter.OnItemClick {
                override fun onClick(item: ResponseDetailMovieDomain) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, item.id)
                    startActivity(intent)
                }
            })
        }
        viewModel.getMoviePage.observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                when (response.status) {
                    Status.LOADING -> {
                        progressBarHolderLoadingCL.visibility = View.VISIBLE
                    }

                    Status.SUCCESS -> {
                        progressBarHolderLoadingCL.visibility = View.GONE
                        movieAdapter?.submitList(response.data)
                        movieAdapter?.notifyDataSetChanged()
                    }

                    Status.ERROR -> {
                        progressBarHolderLoadingCL.visibility = View.GONE
                        showSnackbarMessage("Tidak ada data")
                    }
                }
            }
        })

        rv_fav_movie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = movieAdapter
            setEmptyView(imageEmptyMovie, textViewEmptyMovie)
        }

    }

    override fun onResume() {
        super.onResume()
        rv_fav_movie.setEmptyView(imageEmptyMovie, textViewEmptyMovie)
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(rv_fav_movie, message, Snackbar.LENGTH_SHORT).show()
    }

}
