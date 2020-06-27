package co.id.movieapps.ui.tvShow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.movieapps.R
import co.id.movieapps.data.entity.domain.tv.TvShowDomain
import co.id.movieapps.ui.base.LoadMoreItemView
import co.id.movieapps.ui.detail.tv.DetailTvActivity
import co.id.movieapps.utils.room.PaginationScrollListener
import co.id.movieapps.utils.ui.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import org.koin.android.ext.android.inject

class TvShowsFragment : Fragment() {

    private val viewModel: TvShowsViewModel by inject()
    private val adapterTvShow = GroupAdapter<ViewHolder>()
    private var page = 1
    private var isLoadMore = false
    private var isLastPage = false
    private var loadMoreItemView = LoadMoreItemView()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tv_shows_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBarHolderLoadingCL.visibility = View.VISIBLE

        viewModel.tvState.observe(this, startObserver)
        viewModel.getTvShow(page)
        setupRV()

    }

    private fun setupRV() {
        val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_tvShow.apply {
            layoutManager = linearLayout
            adapter = adapterTvShow
        }

        rv_tvShow.addOnScrollListener(object : PaginationScrollListener(linearLayout) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoadMore
            }

            override fun loadMoreItems() {
                isLoadMore = true
                page++

                viewModel.getTvShow(page)
            }
        })
    }

    private val startObserver = Observer<TvShowState> { tvShowState ->
        when (tvShowState) {
            is LoadingState -> {
                if (isLoadMore) {
                    adapterTvShow.add(loadMoreItemView)
                }
            }

            is TvShowDataLoadedState -> {
                progressBarHolderLoadingCL.visibility = View.GONE
                if (isLoadMore) {
                    adapterTvShow.remove(loadMoreItemView)
                    isLoadMore = false
                }

                if (page == 1) {
                    adapterTvShow.clear()
                }

                tvShowState.tvShowDomain.map {
                    adapterTvShow.add(TvShowsListItem(it, object : TvShowsListItem.OnItemClick {
                        override fun onClick(item: TvShowDomain) {
                            val intent = Intent(context, DetailTvActivity::class.java)
                            intent.putExtra(DetailTvActivity.EXTRA_TV, item.id)
                            startActivity(intent)
                        }
                    }))
                }
            }

            is DataNotFoundState -> {
                adapterTvShow.clear()
            }

            is LastPageState -> {
                if (isLoadMore) {
                    adapterTvShow.remove(loadMoreItemView)
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
