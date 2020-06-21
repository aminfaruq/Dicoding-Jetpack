package co.id.movieapps.data.entity.domain.tv

import com.google.gson.annotations.SerializedName

data class ResponseDetailTvDomain(
    val originalLanguage: String? = null,
    val numberOfEpisodes: Int? = null,
    val type: String? = null,
    val backdropPath: String? = null,
    val popularity: Double? = null,
    val id: Int? = null,
    val numberOfSeasons: Int? = null,
    val voteCount: Int? = null,
    val firstAirDate: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val originalName: String? = null,
    val voteAverage: Double? = null,
    val name: String? = null,
    val inProduction: Boolean? = null,
    val lastAirDate: String? = null,
    val homepage: String? = null,
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
