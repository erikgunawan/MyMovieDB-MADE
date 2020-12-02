package id.ergun.mymoviedb.ui.viewmodel.tvshow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.ergun.mymoviedb.domain.model.TvShow
import id.ergun.mymoviedb.domain.usecase.tvshow.TvShowUseCase
import id.ergun.mymoviedb.ui.datasource.tvshow.TvShowDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.tvshow.TvShowKeyedDataSource
import id.ergun.mymoviedb.ui.view.favorite.FavoriteModel
import id.ergun.mymoviedb.ui.view.tvshow.TvShowVR
import id.ergun.mymoviedb.util.Resource

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowViewModel @ViewModelInject constructor(
    private val useCase: TvShowUseCase,
    private val dataSourceFactory: TvShowDataSourceFactory) : ViewModel() {

    var favorite: MutableLiveData<Boolean> = MutableLiveData()

    var favoriteState: MutableLiveData<FavoriteModel.Type> = MutableLiveData()

    lateinit var tvShow: TvShow

    fun setSelectedTvShow(tvShow: TvShow) {
        this.tvShow = tvShow
    }

    fun setFavorite(favoritePage: Boolean) {
        dataSourceFactory.favoritePage = favoritePage
    }

    val tvShowList: LiveData<PagedList<TvShowVR>> = LivePagedListBuilder(dataSourceFactory, TvShowDataSourceFactory.pagedListConfig()).build()

    val tvShowState: LiveData<Resource<*>> =
        Transformations.switchMap(
            dataSourceFactory.liveData,
            TvShowKeyedDataSource::state
        )

    fun getTvShowDetail(id: Int): LiveData<Resource<TvShow>> {
        return liveData { emit(useCase.getTvShowDetail(id)) }
    }

    private fun getFavoriteTvShow(id: Int) = liveData { emit(useCase.getFavoriteTvShow(id)) }

    fun getFavoriteTvShowById() = if (tvShow.id != null) getFavoriteTvShow(tvShow.id!!)
    else liveData {  }

    fun addToFavorite(tvShow: TvShow) = liveData {  emit(useCase.addToFavorite(tvShow)) }

    fun removeFromFavorite(id: Int) = liveData { emit(useCase.removeFromFavorite(id)) }
}