package co.id.movieapps.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.movieapps.R
import co.id.movieapps.data.entity.domain.movie.MovieDomain
import co.id.movieapps.ui.base.LoadMoreItemView
import co.id.movieapps.ui.detail.movie.DetailActivity
import co.id.movieapps.utils.PaginationScrollListener
import co.id.movieapps.utils.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.movies_fragment.*
import org.koin.android.ext.android.inject

class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by inject()
    private val adapterMovie = GroupAdapter<ViewHolder>()
    private var page = 1
    private var isLoadMore = false
    private var isLastPage = false
    private var loadMoreItemView = LoadMoreItemView()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBarHolderLoadingCL.visibility = View.VISIBLE

        viewModel.movieState.observe(this, startObserver)
        viewModel.getMovies(page)
        setupList()

    }

    private fun setupList() {
        val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_movies.apply {
            layoutManager = linearLayout
            adapter = adapterMovie
        }

        rv_movies.addOnScrollListener(object : PaginationScrollListener(linearLayout) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoadMore
            }

            override fun loadMoreItems() {
                isLoadMore = true
                page++
                viewModel.getMovies(page)
            }
        })
    }

    private val startObserver = Observer<MovieState> { movieState ->
        when (movieState) {
            is LoadingState -> {
                if (isLoadMore) {
                    adapterMovie.add(loadMoreItemView)
                }
            }

            is MovieDataLoadedState -> {
                progressBarHolderLoadingCL.visibility = View.GONE
                if (isLoadMore) {
                    adapterMovie.remove(loadMoreItemView)
                    isLoadMore = false
                }

                if (page == 1) {
                    adapterMovie.clear()
                }

                movieState.movieDomain.map {
                    adapterMovie.add(MoviesListItem(it, object : MoviesListItem.OnItemClick {
                        override fun onClick(item: MovieDomain) {
                            val intent = Intent(context, DetailActivity::class.java)
                            intent.putExtra(DetailActivity.EXTRA_MOVIE, item.id)
                            startActivity(intent)
                        }
                    }))
                }
            }

            is DataNotFoundState -> {
                adapterMovie.clear()
            }

            is LastPageState -> {
                if (isLoadMore) {
                    adapterMovie.remove(loadMoreItemView)
                    isLoadMore = false
                    if (!isLastPage) {
                        toast(context!!, "Last Page")
                        isLastPage = true
                    }
                }
            }

            is ErrorState -> {
                progressBarHolderLoadingCL.visibility = View.GONE
            }

        }
    }


}
