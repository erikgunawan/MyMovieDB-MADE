package id.ergun.mymoviedb.data.repository.tvshow

import id.ergun.mymoviedb.data.local.TvShowDB
import id.ergun.mymoviedb.domain.model.TvShow
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowRepositoryImpl @Inject constructor(private val localData: TvShowDB) : TvShowRepository {
    override fun getTvShows(): ArrayList<TvShow> {
        return TvShow.transform(localData)
    }
}