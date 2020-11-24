package id.ergun.mymoviedb.data.repository.tvshow

import id.ergun.mymoviedb.data.remote.ApiService
import id.ergun.mymoviedb.data.remote.getResult
import id.ergun.mymoviedb.data.remote.model.TvShowResponse
import id.ergun.mymoviedb.domain.model.TvShow
import id.ergun.mymoviedb.util.Resource
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowRepositoryImpl @Inject constructor(private val remoteData: ApiService) : TvShowRepository {
    override suspend fun getTvShows(): Resource<ArrayList<TvShow>> {
        return try {
            remoteData.getTvShows(page = 1).getResult {
                TvShowResponse.mapToDomainModelList(it)
            }
        }
        catch (exception: Exception) {
            Resource.error("Terjadi kesalahan")
        }
    }
    override suspend fun getTvShowDetail(id: Int): Resource<TvShow> {
        return remoteData.getTvShowDetail(id = id.toString()).getResult {
            TvShowResponse.mapToDomainModel(it)
        }
    }
}