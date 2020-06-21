package co.id.movieapps.data.entity.domain.movie

data class ResponseDetailMovieDomain(
    val originalLanguage: String?,
    val title: String?,
    val backdropPath: String?,
    val popularity: Double?,
    val id: Int?,
    val voteCount: Int?,
    val overview: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: Double?,
    val tagline: String?,
    val homepage: String?,
    val status: String?
)


data class ResponseGenreDomain(
    val genres: List<GenresItemDomain>?

)

data class GenresItemDomain(
    val name: String?,
    val id: Int?
)


