package co.id.movieapps.data.entity.model.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    @SerializedName("id")
    var movieId: Int?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("overview")
    var description: String?,
    @SerializedName("poster_path")
    var image: String?,
    @SerializedName("popularity")
    var popularity: Double?,
    @SerializedName("vote_average")
    var voteAverage: Double?,
    @SerializedName("original_language")
    var originalLanguage: String?,
    @SerializedName("release_date")
    var release_date: String?,
    @SerializedName("backdropPath")
    var backdropPath: String?

) : Parcelable {
    data class ResponseMovie(
        @SerializedName("results")
        var results: List<MovieEntity>
    )
}