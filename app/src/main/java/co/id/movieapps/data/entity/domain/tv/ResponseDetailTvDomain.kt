package co.id.movieapps.data.entity.domain.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tb_detail_tv")
data class ResponseDetailTvDomain(
    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String?,

    @ColumnInfo(name = "numberOfEpisodes")
    val numberOfEpisodes: Int?,

    @ColumnInfo(name = "type")
    val type: String?,

    @ColumnInfo(name = "backdropPath")
    val backdropPath: String?,

    @ColumnInfo(name = "popularity")
    val popularity: Double?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "numberOfSeasons")
    val numberOfSeasons: Int?,

    @ColumnInfo(name = "voteCount")
    val voteCount: Int?,

    @ColumnInfo(name = "firstAirDate")
    val firstAirDate: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "posterPath")
    val posterPath: String?,

    @ColumnInfo(name = "originalName")
    val originalName: String?,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "inProduction")
    val inProduction: Boolean?,

    @ColumnInfo(name = "lastAirDate")
    val lastAirDate: String?,

    @ColumnInfo(name = "homepage")
    val homepage: String?,

    @ColumnInfo(name = "status")
    val status: String?
)

data class LastEpisodeToAirTv(

    @field:SerializedName("production_code")
    val productionCode: String?,

    @field:SerializedName("air_date")
    val airDate: String?,

    @field:SerializedName("overview")
    val overview: String?,

    @field:SerializedName("episode_number")
    val episodeNumber: Int?,

    @field:SerializedName("show_id")
    val showId: Int?,

    @field:SerializedName("vote_average")
    val voteAverage: Int?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("season_number")
    val seasonNumber: Int?,

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("still_path")
    val stillPath: String?,

    @field:SerializedName("vote_count")
    val voteCount: Int?
)

data class ProductionCompaniesItemTv(

    @field:SerializedName("logo_path")
    val logoPath: Any?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("origin_country")
    val originCountry: String?
)

data class SeasonsItemTv(

    @field:SerializedName("air_date")
    val airDate: Any?,

    @field:SerializedName("overview")
    val overview: String?,

    @field:SerializedName("episode_count")
    val episodeCount: Int?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("season_number")
    val seasonNumber: Int?,

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("poster_path")
    val posterPath: Any?
)

data class CreatedByItemTv(

    @field:SerializedName("gender")
    val gender: Int?,

    @field:SerializedName("credit_id")
    val creditId: String?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("profile_path")
    val profilePath: Any?,

    @field:SerializedName("id")
    val id: Int?
)

data class GenresItemTv(

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("id")
    val id: Int?
)

data class NetworksItemTv(

    @field:SerializedName("logo_path")
    val logoPath: String?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("origin_country")
    val originCountry: String?
)
