package id.ergun.mymoviedb.core.domain.usecase.tvshow

import id.ergun.mymoviedb.core.domain.model.Movie
import id.ergun.mymoviedb.core.util.Resource

/**
 * Created by alfacart on 21/10/20.
 */
interface TvShowUseCase {
  suspend fun getTvShows(page: Int): Resource<ArrayList<Movie>>
  suspend fun getTvShowDetail(id: Int): Resource<Movie>
  suspend fun searchTvShow(query: String, page: Int): Resource<ArrayList<Movie>>
  suspend fun getFavoriteTvShows(): Resource<ArrayList<Movie>>
  suspend fun getFavoriteTvShow(id: Int): Resource<Movie>
  suspend fun addToFavorite(tvShow: Movie): Long
  suspend fun removeFromFavorite(id: Int): Int
}