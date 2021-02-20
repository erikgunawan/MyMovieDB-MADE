package id.ergun.mymoviedb.ui.datasource.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.ergun.mymoviedb.core.domain.usecase.tvshow.TvShowUseCase
import id.ergun.mymoviedb.core.view.tvshow.TvShowVR
import javax.inject.Inject

/**
 * Created by root on 20/02/21.
 */
class TvShowSearchDataSourceFactory @Inject constructor(
    private val useCase: TvShowUseCase
) : DataSource.Factory<Int, TvShowVR>() {

  val liveData: MutableLiveData<TvShowSearchKeyedDataSource> = MutableLiveData<TvShowSearchKeyedDataSource>()

  private var query = ""
  var favoritePage: Boolean = false

  override fun create(): DataSource<Int, TvShowVR> {
    val source = TvShowSearchKeyedDataSource(useCase)
    source.favoritePage = favoritePage
    source.query = query
    liveData.postValue(source)
    return source
  }

  fun searchTvShow(query: String): LiveData<PagedList<TvShowVR>> {
    this.query = query
    return LivePagedListBuilder(this, pagedListConfig()).build()
  }

  companion object {
    private const val PAGE_SIZE = 10

    fun pagedListConfig(): PagedList.Config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setPageSize(PAGE_SIZE)
        .setEnablePlaceholders(true)
        .build()
  }
}