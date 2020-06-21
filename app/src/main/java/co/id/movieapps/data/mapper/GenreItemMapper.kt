package co.id.movieapps.data.mapper

import co.id.movieapps.data.entity.domain.movie.GenresItemDomain
import co.id.movieapps.data.entity.model.movie.GenresItemEntity

class GenreItemMapper : BaseMapper<GenresItemEntity, GenresItemDomain> {
    override fun mapToDomain(model: GenresItemEntity): GenresItemDomain {
        return GenresItemDomain(
            model.name,
            model.id
        )
    }

    override fun mapToModel(domain: GenresItemDomain): GenresItemEntity {
        return GenresItemEntity(
            domain.name,
            domain.id
        )
    }

}