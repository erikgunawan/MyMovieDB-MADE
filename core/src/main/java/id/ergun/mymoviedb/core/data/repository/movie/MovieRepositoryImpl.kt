package id.ergun.mymoviedb.core.data.repository.movie

import com.google.gson.Gson
import id.ergun.mymoviedb.core.data.local.model.MovieLocal
import id.ergun.mymoviedb.core.data.local.room.dao.MovieDao
import id.ergun.mymoviedb.core.data.remote.ApiService
import id.ergun.mymoviedb.core.data.remote.getResult
import id.ergun.mymoviedb.core.data.remote.model.MovieResponse
import id.ergun.mymoviedb.core.domain.model.Movie
import id.ergun.mymoviedb.core.util.Resource
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class MovieRepositoryImpl @Inject constructor(
    private val remoteData: ApiService,
    private val localData: MovieDao
) : MovieRepository {
  override suspend fun getMovies(page: Int): Resource<ArrayList<Movie>> {
    return try {
      remoteData.getMovies(page = page).getResult {
        MovieResponse.mapToDomainModelList(it)
      }
    } catch (exception: Exception) {
      Resource.error("Terjadi kesalahan")
    }
  }

  override suspend fun getMovieDetail(id: Int): Resource<Movie> {
    return try {
      remoteData.getMovieDetail(id = id.toString()).getResult {
        MovieResponse.mapToDomainModel(it)
      }
    } catch (exception: Exception) {
      Resource.error("Terjadi kesalahan")
    }
  }

  override suspend fun searchMovie(query: String, page: Int): Resource<ArrayList<Movie>> {
    return try {
      remoteData.searchMovie(query = query, page = page).getResult {
        MovieResponse.mapToDomainModelList(it)
      }
    } catch (exception: Exception) {
      Timber.e(exception)
      Resource.error("Terjadi kesalahan")
    }
  }

  override suspend fun getFavoriteMovies(): Resource<ArrayList<Movie>> {
    return try {
      return Resource.success(
          MovieLocal.mapToDomainModelList(localData.getMovies())
      )
    } catch (exception: Exception) {
      Timber.e(exception.toString())
      Resource.error("Terjadi kesalahan")
    }
  }

  override suspend fun getFavoriteMovie(id: Int): Resource<Movie> {
    val movie = localData.getMovie(id)
    Timber.e(Gson().toJson(movie))
    return if (movie != null) {
      Resource.success(MovieLocal.mapToDomainModel(movie))
    } else {
      Resource.error("Data tidak ditemukan")
    }
  }

  override suspend fun addToFavorite(movie: Movie): Long {
    return localData.insertMovie(MovieLocal.mapFromDomainModel(movie))
  }

  override suspend fun removeFromFavorite(id: Int): Int {
    return localData.deleteById(id)
  }
}