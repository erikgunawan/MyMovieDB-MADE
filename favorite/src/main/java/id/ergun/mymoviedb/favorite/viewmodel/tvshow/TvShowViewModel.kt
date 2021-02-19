package id.ergun.mymoviedb.favorite.viewmodel.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.ergun.mymoviedb.core.view.tvshow.TvShowVR
import id.ergun.mymoviedb.ui.datasource.tvshow.TvShowDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.tvshow.TvShowKeyedDataSource

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowViewModel(
    private val dataSourceFactory: TvShowDataSourceFactory
) : ViewModel() {

  var favorite: MutableLiveData<Boolean> = MutableLiveData()

  var favoritePage: Boolean = false

  fun setFavorite(favoritePage: Boolean) {
    this.favoritePage = favoritePage
    dataSourceFactory.favoritePage = favoritePage
  }

  fun getTvShows(): LiveData<PagedList<TvShowVR>> = dataSourceFactory.getTvShows()

  val tvShowState: LiveData<id.ergun.mymoviedb.core.util.Resource<*>> =
      Transformations.switchMap(
          dataSourceFactory.liveData,
          TvShowKeyedDataSource::state
      )

  fun refresh() {
    dataSourceFactory.liveData.value?.invalidate()
  }
}