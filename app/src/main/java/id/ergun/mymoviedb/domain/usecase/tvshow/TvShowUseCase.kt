package id.ergun.mymoviedb.domain.usecase.tvshow

import id.ergun.mymoviedb.domain.model.TvShow
import id.ergun.mymoviedb.util.Resource

/**
 * Created by alfacart on 21/10/20.
 */
interface TvShowUseCase {
    suspend fun getTvShows(page: Int): Resource<ArrayList<TvShow>>
    suspend fun getTvShowDetail(id: Int): Resource<TvShow>
    suspend fun getFavoriteTvShows(): Resource<ArrayList<TvShow>>
    suspend fun getFavoriteTvShow(id: Int): Resource<TvShow>
    suspend fun addToFavorite(tvShow: TvShow): Long
    suspend fun removeFromFavorite(id: Int)
}