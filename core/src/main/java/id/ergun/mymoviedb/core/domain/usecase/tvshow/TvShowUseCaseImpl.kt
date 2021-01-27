package id.ergun.mymoviedb.core.domain.usecase.tvshow

import id.ergun.mymoviedb.core.data.repository.tvshow.TvShowRepository
import id.ergun.mymoviedb.core.domain.model.TvShow
import id.ergun.mymoviedb.core.util.Resource
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowUseCaseImpl @Inject constructor(private val repository: TvShowRepository) :
    TvShowUseCase {

    override suspend fun getTvShows(page: Int): Resource<ArrayList<TvShow>> {
        return repository.getTvShows(page)
    }

    override suspend fun getTvShowDetail(id: Int): Resource<TvShow> {
        return repository.getTvShowDetail(id)
    }

    override suspend fun getFavoriteTvShows(): Resource<ArrayList<TvShow>> {
        return repository.getFavoriteTvShows()
    }

    override suspend fun getFavoriteTvShow(id: Int): Resource<TvShow> {
        return repository.getFavoriteTvShow(id)
    }

    override suspend fun addToFavorite(tvShow: TvShow): Long {
        return repository.addToFavorite(tvShow)
    }

    override suspend fun removeFromFavorite(id: Int): Int {
        return repository.removeFromFavorite(id)
    }
}