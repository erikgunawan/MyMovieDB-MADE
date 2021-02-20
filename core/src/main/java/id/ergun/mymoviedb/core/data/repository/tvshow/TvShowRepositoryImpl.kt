package id.ergun.mymoviedb.core.data.repository.tvshow

import com.google.gson.Gson
import id.ergun.mymoviedb.core.data.local.model.TvShowLocal
import id.ergun.mymoviedb.core.data.local.room.dao.TvShowDao
import id.ergun.mymoviedb.core.data.remote.ApiService
import id.ergun.mymoviedb.core.data.remote.getResult
import id.ergun.mymoviedb.core.data.remote.model.TvShowResponse
import id.ergun.mymoviedb.core.domain.model.TvShow
import id.ergun.mymoviedb.core.util.Resource
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowRepositoryImpl @Inject constructor(
    private val remoteData: ApiService,
    private val localData: TvShowDao
) : TvShowRepository {
  override suspend fun getTvShows(page: Int): Resource<ArrayList<TvShow>> {
    return try {
      remoteData.getTvShows(page = page).getResult {
        TvShowResponse.mapToDomainModelList(it)
      }
    } catch (exception: Exception) {
      Resource.error("Terjadi kesalahan")
    }
  }

  override suspend fun getTvShowDetail(id: Int): Resource<TvShow> {
    return try {
      remoteData.getTvShowDetail(id = id.toString()).getResult {
        TvShowResponse.mapToDomainModel(it)
      }
    } catch (exception: Exception) {
      Timber.e(exception)
      Resource.error("Terjadi kesalahan1")
    }
  }

  override suspend fun searchTvShow(query: String, page: Int): Resource<ArrayList<TvShow>> {
    return try {
      remoteData.searchTvShow(query = query, page = page).getResult {
        TvShowResponse.mapToDomainModelList(it)
      }
    } catch (exception: Exception) {
      Timber.e(exception)
      Resource.error("Terjadi kesalahan1")
    }
  }

  override suspend fun getFavoriteTvShows(): Resource<ArrayList<TvShow>> {
    return try {
      return Resource.success(
          TvShowLocal.mapToDomainModelList(localData.getTvShows())
      )
    } catch (exception: Exception) {
      Timber.e(exception.toString())
      Resource.error("Terjadi kesalahan")
    }
  }

  override suspend fun getFavoriteTvShow(id: Int): Resource<TvShow> {
    val tvShow = localData.getTvShow(id)
    Timber.e(Gson().toJson(tvShow))
    return if (tvShow != null) {
      Resource.success(TvShowLocal.mapToDomainModel(tvShow))
    } else {
      Resource.error("Data tidak ditemukan")
    }
  }

  override suspend fun addToFavorite(tvShow: TvShow): Long {
    return localData.insertTvShow(TvShowLocal.mapFromDomainModel(tvShow))
  }

  override suspend fun removeFromFavorite(id: Int): Int {
    return localData.deleteById(id)
  }
}