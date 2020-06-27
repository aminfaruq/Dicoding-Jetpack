package co.id.movieapps.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import co.id.movieapps.R
import co.id.movieapps.ui.movies.MoviesFragment
import co.id.movieapps.ui.tvShow.TvShowsFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tvShow)
    }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> MoviesFragment()
        1 -> TvShowsFragment()
        else -> Fragment()
    }

    override fun getPageTitle(position: Int): CharSequence? =
        mContext.resources.getString(TAB_TITLES[position])


    override fun getCount(): Int = 2
}