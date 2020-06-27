package co.id.movieapps.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.id.movieapps.R
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        title = "Favorite"
        val sectionsPagerAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        view_pager_favorite.adapter = sectionsPagerAdapter
        tabs_favorite.setupWithViewPager(view_pager_favorite)

        supportActionBar?.elevation = 0f
    }
}
