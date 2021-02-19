package id.ergun.mymoviedb.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ergun.mymoviedb.favorite.viewmodel.movie.MovieViewModel
import id.ergun.mymoviedb.favorite.viewmodel.tvshow.TvShowViewModel
import id.ergun.mymoviedb.ui.datasource.movie.MovieDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.tvshow.TvShowDataSourceFactory
import javax.inject.Inject

class ViewModelFactory<V> @Inject constructor(
    private val dataSourceFactory: V
) :
    ViewModelProvider.NewInstanceFactory() {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T =
      when {
        modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
          MovieViewModel(dataSourceFactory as MovieDataSourceFactory) as T
        }
        modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
          TvShowViewModel(dataSourceFactory as TvShowDataSourceFactory) as T
        }
        else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
      }
}