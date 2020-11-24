package id.ergun.mymoviedb.domain.usecase.tvshow

import id.ergun.mymoviedb.util.Resource
import id.ergun.mymoviedb.domain.model.TvShow

/**
 * Created by alfacart on 21/10/20.
 */
interface TvShowUseCase {
    suspend fun getTvShows(): Resource<ArrayList<TvShow>>
    suspend fun getTvShowDetail(id: Int): Resource<TvShow>
}