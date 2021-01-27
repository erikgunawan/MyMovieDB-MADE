package id.ergun.mymoviedb.ui.datasource.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.ergun.mymoviedb.core.domain.usecase.tvshow.TvShowUseCase
import id.ergun.mymoviedb.core.view.tvshow.TvShowVR
import javax.inject.Inject

/**
 * Created by alfacart on 01/12/20.
 */
class TvShowDataSourceFactory @Inject constructor(
    private val useCase: TvShowUseCase
) : DataSource.Factory<Int, TvShowVR>() {

    val liveData: MutableLiveData<TvShowKeyedDataSource> = MutableLiveData<TvShowKeyedDataSource>()

    var favoritePage: Boolean = false

    override fun create(): DataSource<Int, TvShowVR> {
        val source = TvShowKeyedDataSource(useCase)
        source.favoritePage = favoritePage
        liveData.postValue(source)
        return source
    }

    fun getTvShows(): LiveData<PagedList<TvShowVR>> =
        LivePagedListBuilder(this, pagedListConfig()).build()

    companion object {
        private const val PAGE_SIZE = 10

        fun pagedListConfig(): PagedList.Config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}