package co.id.movieapps.data.entity.model.tv

import com.google.gson.annotations.SerializedName

data class ResponseDetailTv(

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("number_of_episodes")
    val numberOfEpisodes: Int? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("number_of_seasons")
    val numberOfSeasons: Int? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("original_name")
    val originalName: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("in_production")
    val inProduction: Boolean? = null,

    @field:SerializedName("last_air_date")
    val lastAirDate: String? = null,

    @field:SerializedName("homepage")
    val homepage: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class LastEpisodeToAirTv(

    @field:SerializedName("production_code")
    val productionCode: String? = null,

    @field:SerializedName("air_date")
    val airDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("episode_number")
    val episodeNumber: Int? = null,

    @field:SerializedName("show_id")
    val showId: Int? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("season_number")
    val seasonNumber: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("still_path")
    val stillPath: String? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null
)

data class ProductionCompaniesItemTv(

    @field:SerializedName("logo_path")
    val logoPath: Any? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("origin_country")
    val originCountry: String? = null
)

data class SeasonsItemTv(

    @field:SerializedName("air_date")
    val airDate: Any? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("episode_count")
    val episodeCount: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("season_number")
    val seasonNumber: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("poster_path")
    val posterPath: Any? = null
)

data class CreatedByItemTv(

    @field:SerializedName("gender")
    val gender: Int? = null,

    @field:SerializedName("credit_id")
    val creditId: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("profile_path")
    val profilePath: Any? = null,

    @field:SerializedName("id")
    val id: Int? = null
)

data class GenresItemTv(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)

data class NetworksItemTv(

    @field:SerializedName("logo_path")
    val logoPath: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("origin_country")
    val originCountry: String? = null
)
