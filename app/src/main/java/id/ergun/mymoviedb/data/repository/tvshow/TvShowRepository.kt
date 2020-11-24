package id.ergun.mymoviedb.data.repository.tvshow

import id.ergun.mymoviedb.domain.model.TvShow
import id.ergun.mymoviedb.util.Resource

/**
 * Created by alfacart on 21/10/20.
 */
interface TvShowRepository {
    suspend fun getTvShows(): Resource<ArrayList<TvShow>>
    suspend fun getTvShowDetail(id: Int): Resource<TvShow>
}