package id.ergun.mymoviedb.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ergun.mymoviedb.favorite.viewmodel.movie.MovieViewModel
import id.ergun.mymoviedb.favorite.viewmodel.tvshow.TvShowViewModel
import id.ergun.mymoviedb.ui.datasource.movie.MovieDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.tvshow.TvShowDataSourceFactory
import javax.inject.Inject

class ViewModelFactory<V, U> @Inject constructor(
    private val movieDataSourceFactory: V,
    private val tvShowDataSourceFactory: U
) :
    ViewModelProvider.NewInstanceFactory() {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T =
      when {
        modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
          MovieViewModel(movieDataSourceFactory as MovieDataSourceFactory, tvShowDataSourceFactory as TvShowDataSourceFactory) as T
        }
        modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
          TvShowViewModel(tvShowDataSourceFactory as TvShowDataSourceFactory) as T
        }
        else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
      }
}