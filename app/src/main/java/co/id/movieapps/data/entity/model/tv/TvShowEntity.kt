package co.id.movieapps.data.entity.model.tv

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowEntity(
    @SerializedName("id")
    var tvId: Int?,
    @SerializedName("name")
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
    @SerializedName("first_air_date")
    var release_date: String?,
    @SerializedName("original_name")
    val original_name: String?,
    @SerializedName("backdropPath")
    val backdropPath: String?

) : Parcelable {
    data class ResponseTv(
        @SerializedName("results")
        var result: List<TvShowEntity>
    )
}