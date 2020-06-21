package co.id.movieapps.data.entity.domain.movie

data class MovieDomain(
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val popularity: Double? = null,
    val voteAverage: Double? = null,
    val id: Int? = null
)