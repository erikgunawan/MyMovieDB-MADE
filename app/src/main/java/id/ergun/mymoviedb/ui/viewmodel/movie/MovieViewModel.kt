package id.ergun.mymoviedb.ui.viewmodel.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.domain.usecase.movie.MovieUseCase
import id.ergun.mymoviedb.ui.datasource.movie.MovieDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.movie.MovieKeyedDataSource
import id.ergun.mymoviedb.ui.view.favorite.FavoriteModel
import id.ergun.mymoviedb.ui.view.movie.MovieVR
import id.ergun.mymoviedb.util.Resource

/**
 * Created by alfacart on 21/10/20.
 */
class MovieViewModel @ViewModelInject constructor(
    private val useCase: MovieUseCase,
    private val dataSourceFactory: MovieDataSourceFactory
) : ViewModel() {

    var favorite: MutableLiveData<Boolean> = MutableLiveData()

    var favoriteState: MutableLiveData<FavoriteModel.Type> = MutableLiveData()

    lateinit var movie: Movie

    fun setFavorite(favoritePage: Boolean) {
        dataSourceFactory.favoritePage = favoritePage
    }

    fun setSelectedMovie(movie: Movie) {
        this.movie = movie
    }

    val movieList: LiveData<PagedList<MovieVR>> = LivePagedListBuilder(dataSourceFactory, MovieDataSourceFactory.pagedListConfig()).build()

    val movieState: LiveData<Resource<*>> =
        Transformations.switchMap(
            dataSourceFactory.liveData,
            MovieKeyedDataSource::state
        )

    fun refresh() {
        dataSourceFactory.liveData.value?.invalidate()
    }

    fun getMovieDetail(id: Int): LiveData<Resource<Movie>> {
        return liveData { emit(useCase.getMovieDetail(id)) }
    }

    private fun getFavoriteMovie(id: Int) = liveData { emit(useCase.getFavoriteMovie(id)) }

    fun getFavoriteMovieById() = if (movie.id != null) getFavoriteMovie(movie.id!!)
    else liveData {  }

    fun addToFavorite(movie: Movie) = liveData {  emit(useCase.addToFavorite(movie)) }

    fun removeFromFavorite(id: Int) = liveData { emit(useCase.removeFromFavorite(id)) }
}