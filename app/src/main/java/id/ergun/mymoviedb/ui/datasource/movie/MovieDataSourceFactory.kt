package id.ergun.mymoviedb.ui.datasource.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.ergun.mymoviedb.core.domain.usecase.movie.MovieUseCase
import id.ergun.mymoviedb.core.view.movie.MovieVR
import javax.inject.Inject

/**
 * Created by alfacart on 30/11/20.
 */
class MovieDataSourceFactory @Inject constructor(
    private val useCase: MovieUseCase
) : DataSource.Factory<Int, MovieVR>() {

    val liveData = MutableLiveData<MovieKeyedDataSource>()

    var favoritePage: Boolean = false

    override fun create(): DataSource<Int, MovieVR> {
        val source = MovieKeyedDataSource(useCase)
        source.favoritePage = favoritePage
        liveData.postValue(source)
        return source
    }

    fun getMovies(): LiveData<PagedList<MovieVR>> = LivePagedListBuilder(this, pagedListConfig()).build()

    companion object {
        private const val PAGE_SIZE = 10

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}