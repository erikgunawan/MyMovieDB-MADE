package id.ergun.mymoviedb.data.repository.movie

import id.ergun.mymoviedb.data.remote.ApiService
import id.ergun.mymoviedb.data.remote.getResult
import id.ergun.mymoviedb.data.remote.model.MovieResponse
import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.util.Resource
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class MovieRepositoryImpl @Inject constructor(private val remoteData: ApiService) : MovieRepository {
    override suspend fun getMovies(): Resource<ArrayList<Movie>> {
        return try {
            remoteData.getMovies(page = 1).getResult {
                MovieResponse.mapToDomainModelList(it)
            }
        }
        catch (exception: Exception) {
            Resource.error("Terjadi kesalahan")
        }
    }

    override suspend fun getMovieDetail(id: Int): Resource<Movie> {
        return remoteData.getMovieDetail(id = id.toString()).getResult {
            MovieResponse.mapToDomainModel(it)
        }
    }
}