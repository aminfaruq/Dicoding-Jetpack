package co.id.movieapps.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import co.id.movieapps.R
import co.id.movieapps.ui.base.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url: String) {

    Glide.with(this)
        .load(Constant.LINK_IMAGE + url)
        .apply(
            RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
        )
        .into(this)

}

fun toast(v: Context, message: String) {
    Toast.makeText(
        v,
        message,
        Toast.LENGTH_SHORT
    ).show()
}