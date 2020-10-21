package id.ergun.mymoviedb.domain.usecase.tvshow

import id.ergun.mymoviedb.data.repository.tvshow.TvShowRepository
import id.ergun.mymoviedb.domain.model.TvShow
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowUseCaseImpl @Inject constructor(private val repository: TvShowRepository) : TvShowUseCase {
    override fun getTvShows(): ArrayList<TvShow> {
        return repository.getTvShows()
    }
}