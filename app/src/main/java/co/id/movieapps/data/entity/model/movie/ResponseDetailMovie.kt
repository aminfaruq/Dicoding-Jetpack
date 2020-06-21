package co.id.movieapps.data.entity.model.movie

import com.google.gson.annotations.SerializedName

data class ResponseDetailMovie(

    @field:SerializedName("original_language")
    val originalLanguage: String?,

    @field:SerializedName("title")
    val title: String?,

    @field:SerializedName("backdrop_path")
    val backdropPath: String?,

    @field:SerializedName("popularity")
    val popularity: Double?,

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("vote_count")
    val voteCount: Int?,

    @field:SerializedName("overview")
    val overview: String?,

    @field:SerializedName("original_title")
    val originalTitle: String?,

    @field:SerializedName("poster_path")
    val posterPath: String?,

    @field:SerializedName("release_date")
    val releaseDate: String?,

    @field:SerializedName("vote_average")
    val voteAverage: Double?,

    @field:SerializedName("tagline")
    val tagline: String?,

    @field:SerializedName("homepage")
    val homepage: String?,

    @field:SerializedName("status")
    val status: String?
)

data class ResponseGenreMovie(
    @field:SerializedName("genres")
    val genres: List<GenresItemEntity>?
)

data class GenresItemEntity(

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("id")
    val id: Int?
)

