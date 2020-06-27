package co.id.movieapps.data.entity.domain.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_detail_movie")
data class ResponseDetailMovieDomain(
    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "backdropPath")
    val backdropPath: String?,

    @ColumnInfo(name = "popularity")
    val popularity: Double?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "voteCount")
    val voteCount: Int?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "originalTitle")
    val originalTitle: String?,

    @ColumnInfo(name = "posterPath")
    val posterPath: String?,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String?,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double?,

    @ColumnInfo(name = "tagline")
    val tagline: String?,

    @ColumnInfo(name = "homepage")
    val homepage: String?,

    @ColumnInfo(name = "status")
    val status: String?
)


data class ResponseGenreDomain(
    val genres: List<GenresItemDomain>?

)

data class GenresItemDomain(
    val name: String?,
    val id: Int?
)


