package id.ergun.mymoviedb.domain.usecase.tvshow

import id.ergun.mymoviedb.data.repository.tvshow.TvShowRepository
import id.ergun.mymoviedb.domain.model.TvShow
import id.ergun.mymoviedb.util.Resource
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowUseCaseImpl @Inject constructor(private val repository: TvShowRepository) : TvShowUseCase {

    override suspend fun getTvShows(): Resource<ArrayList<TvShow>> {
        return repository.getTvShows()
    }

    override suspend fun getTvShowDetail(id: Int): Resource<TvShow> {
        return repository.getTvShowDetail(id)
    }
}