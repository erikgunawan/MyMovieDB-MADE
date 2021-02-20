package id.ergun.mymoviedb.core.data.repository.tvshow

import id.ergun.mymoviedb.core.domain.model.TvShow
import id.ergun.mymoviedb.core.util.Resource

/**
 * Created by alfacart on 21/10/20.
 */
interface TvShowRepository {
  suspend fun getTvShows(page: Int): Resource<ArrayList<TvShow>>
  suspend fun getTvShowDetail(id: Int): Resource<TvShow>
  suspend fun searchTvShow(query: String, page: Int): Resource<ArrayList<TvShow>>
  suspend fun getFavoriteTvShows(): Resource<ArrayList<TvShow>>
  suspend fun getFavoriteTvShow(id: Int): Resource<TvShow>
  suspend fun addToFavorite(tvShow: TvShow): Long
  suspend fun removeFromFavorite(id: Int): Int
}