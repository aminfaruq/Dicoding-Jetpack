package co.id.movieapps.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowEntity(
    var tvId: String?,
    var title: String?,
    var description: String?,
    var image: String
) : Parcelable