package co.id.movieapps.ui.tvShow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.movieapps.R
import co.id.movieapps.data.TvShowEntity
import co.id.movieapps.ui.detail.DetailActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.tv_shows_fragment.*

class TvShowsFragment : Fragment() {

    companion object {
        fun newInstance() = TvShowsFragment()
    }

    private lateinit var viewModel: TvShowsViewModel
    private val adapterTvShow = GroupAdapter<ViewHolder>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tv_shows_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[TvShowsViewModel::class.java]

        val tvShow = viewModel.getTvShow()
        tvShow.map {
            adapterTvShow.add(TvShowsListItem(tvShow, object : TvShowsListItem.OnItemClick {
                override fun onClick(item: TvShowEntity) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TV, item.tvId)
                    startActivity(intent)
                }
            }))
        }

        rv_tvShow.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterTvShow

        }
    }

}
