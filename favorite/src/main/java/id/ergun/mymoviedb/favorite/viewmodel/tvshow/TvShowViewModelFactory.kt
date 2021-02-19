package id.ergun.mymoviedb.favorite.viewmodel.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ergun.mymoviedb.ui.datasource.tvshow.TvShowDataSourceFactory
import javax.inject.Inject

/**
 * Created by alfacart on 17/01/21.
 */
class TvShowViewModelFactory @Inject constructor(
    private val dataSourceFactory: TvShowDataSourceFactory
) :
    ViewModelProvider.NewInstanceFactory() {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T =
      when {
        modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
          TvShowViewModel(dataSourceFactory) as T
        }
        else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
      }
}