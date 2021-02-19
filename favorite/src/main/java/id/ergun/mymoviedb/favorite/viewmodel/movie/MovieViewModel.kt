package id.ergun.mymoviedb.favorite.viewmodel.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.ergun.mymoviedb.core.util.Resource
import id.ergun.mymoviedb.core.view.movie.MovieVR
import id.ergun.mymoviedb.ui.datasource.movie.MovieDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.movie.MovieKeyedDataSource

/**
 * Created by alfacart on 21/10/20.
 */
class MovieViewModel(
    private val dataSourceFactory: MovieDataSourceFactory
) : ViewModel() {

  var favorite: MutableLiveData<Boolean> = MutableLiveData()

  var favoritePage: Boolean = false

  fun setFavorite(favoritePage: Boolean) {
    this.favoritePage = favoritePage
    dataSourceFactory.favoritePage = favoritePage
  }

  fun getMovies(): LiveData<PagedList<MovieVR>> = dataSourceFactory.getMovies()

  val movieState: LiveData<Resource<*>> =
      Transformations.switchMap(
          dataSourceFactory.liveData,
          MovieKeyedDataSource::state
      )

  fun refresh() {
    dataSourceFactory.liveData.value?.invalidate()
  }
}