package id.ergun.mymoviedb.core.data.remote

import id.ergun.mymoviedb.core.BuildConfig.TMDB_API_KEY
import id.ergun.mymoviedb.core.data.Const.API_KEY
import id.ergun.mymoviedb.core.data.remote.model.MovieResponse
import id.ergun.mymoviedb.core.data.remote.model.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by alfacart on 20/11/20.
 */
interface ApiService {

  @GET("3/discover/movie")
  suspend fun getMovies(
      @Query(API_KEY) apiKey: String = TMDB_API_KEY,
      @Query("page") page: Int?
  ): Response<MovieResponse>

  @GET("3/movie/{id}")
  suspend fun getMovieDetail(
      @Path("id") id: String,
      @Query(API_KEY) apiKey: String = TMDB_API_KEY
  ): Response<MovieResponse.Result>

  @GET("3/search/movie")
  suspend fun searchMovie(
      @Query(API_KEY) apiKey: String = TMDB_API_KEY,
      @Query("query") query: String?,
      @Query("page") page: Int?
  ): Response<MovieResponse>

  @GET("3/discover/tv")
  suspend fun getTvShows(
      @Query(API_KEY) apiKey: String = TMDB_API_KEY,
      @Query("page") page: Int = 1
  ): Response<TvShowResponse>

  @GET("3/tv/{id}")
  suspend fun getTvShowDetail(
      @Path("id") id: String,
      @Query(API_KEY) apiKey: String = TMDB_API_KEY
  ): Response<TvShowResponse.Result>

  @GET("3/search/tv")
  suspend fun searchTvShow(
      @Query(API_KEY) apiKey: String = TMDB_API_KEY,
      @Query("query") query: String?,
      @Query("page") page: Int?
  ): Response<TvShowResponse>
}