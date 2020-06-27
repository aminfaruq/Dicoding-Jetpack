package co.id.movieapps.ui.favorite.tvShow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.movieapps.R
import co.id.movieapps.data.entity.domain.tv.ResponseDetailTvDomain
import co.id.movieapps.ui.detail.tv.DetailTvActivity
import co.id.movieapps.utils.room.Status
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fav_movie_fragment.progressBarHolderLoadingCL
import kotlinx.android.synthetic.main.fav_tv_show_fragment.*
import org.koin.android.ext.android.inject

class FavTvShowFragment : Fragment() {

    companion object {
        fun newInstance() = FavTvShowFragment()
    }

    private val viewModel: FavTvShowViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fav_tv_show_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val tvShowAdapter = context?.let {
            FavTvShowAdapter(it, object : FavTvShowAdapter.OnItemClick {
                override fun onClick(item: ResponseDetailTvDomain) {
                    val intent = Intent(context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_TV, item.id)
                    startActivity(intent)
                }
            })
        }

        viewModel.getTvShowPage.observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                when (response.status) {
                    Status.LOADING -> {
                        progressBarHolderLoadingCL.visibility = View.VISIBLE

                    }

                    Status.SUCCESS -> {
                        progressBarHolderLoadingCL.visibility = View.GONE
                        tvShowAdapter?.submitList(response.data)
                        tvShowAdapter?.notifyDataSetChanged()
                    }

                    Status.ERROR -> {
                        progressBarHolderLoadingCL.visibility = View.GONE
                        showSnackbarMessage("Tidak ada data")

                    }
                }
            }
        })

        rv_fav_tv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = tvShowAdapter
            setEmptyView(imageEmptyTv, textViewEmptyTv)
        }

    }

    override fun onResume() {
        super.onResume()
        rv_fav_tv.setEmptyView(imageEmptyTv, textViewEmptyTv)
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(rv_fav_tv, message, Snackbar.LENGTH_SHORT).show()
    }

}
