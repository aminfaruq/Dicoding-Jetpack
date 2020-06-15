package co.id.movieapps.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    var movieId: String?,
    var title: String?,
    var description: String?,
    var image: String
) : Parcelable