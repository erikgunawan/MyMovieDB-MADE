package id.ergun.mymoviedb.ui.viewmodel.tvshow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.PagedList
import id.ergun.mymoviedb.core.domain.model.Movie
import id.ergun.mymoviedb.core.domain.usecase.tvshow.TvShowUseCase
import id.ergun.mymoviedb.core.util.FavoriteModel
import id.ergun.mymoviedb.core.util.Resource
import id.ergun.mymoviedb.core.view.movie.MovieVR
import id.ergun.mymoviedb.ui.datasource.tvshow.TvShowDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.tvshow.TvShowKeyedDataSource

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowViewModel @ViewModelInject constructor(
    private val useCase: TvShowUseCase,
    private val dataSourceFactory: TvShowDataSourceFactory
) : ViewModel() {

  var favorite: MutableLiveData<Boolean> = MutableLiveData()

  var favoritePage: Boolean = false

  var favoriteState: MutableLiveData<FavoriteModel.Type> = MutableLiveData()

  lateinit var tvShow: Movie

  fun setSelectedTvShow(tvShow: Movie) {
    this.tvShow = tvShow
  }

  fun setFavorite(favoritePage: Boolean) {
    this.favoritePage = favoritePage
    dataSourceFactory.favoritePage = favoritePage
  }

  fun getTvShows(): LiveData<PagedList<MovieVR>> = dataSourceFactory.getTvShows()

  val tvShowState: LiveData<Resource<*>> =
      Transformations.switchMap(
          dataSourceFactory.liveData,
          TvShowKeyedDataSource::state
      )

  fun refresh() {
    dataSourceFactory.liveData.value?.invalidate()
  }

  fun getTvShowDetail(id: Int): LiveData<Resource<Movie>> {
    return liveData { emit(useCase.getTvShowDetail(id)) }
  }

  private fun getFavoriteTvShow(id: Int) = liveData { emit(useCase.getFavoriteTvShow(id)) }

  fun getFavoriteTvShowById(): LiveData<Resource<Movie>> =
      if (tvShow.id != null) getFavoriteTvShow(tvShow.id!!)
      else liveData { }

  fun addToFavorite(tvShow: Movie): LiveData<Long> =
      liveData { emit(useCase.addToFavorite(tvShow)) }

  fun removeFromFavorite(id: Int): LiveData<Int> =
      liveData { emit(useCase.removeFromFavorite(id)) }
}