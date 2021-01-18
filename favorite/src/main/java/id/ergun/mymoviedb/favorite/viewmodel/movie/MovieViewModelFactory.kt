package id.ergun.mymoviedb.favorite.viewmodel.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ergun.mymoviedb.ui.datasource.movie.MovieDataSourceFactory
import javax.inject.Inject

/**
 * Created by alfacart on 17/01/21.
 */
class MovieViewModelFactory @Inject constructor(
    private val dataSourceFactory: MovieDataSourceFactory
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(dataSourceFactory) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}