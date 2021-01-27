package id.ergun.mymoviedb.ui.viewmodel.tvshow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagedList
import id.ergun.mymoviedb.core.domain.model.TvShow
import id.ergun.mymoviedb.core.domain.usecase.tvshow.TvShowUseCase
import id.ergun.mymoviedb.core.util.FavoriteModel
import id.ergun.mymoviedb.core.util.Resource
import id.ergun.mymoviedb.core.view.tvshow.TvShowVR
import id.ergun.mymoviedb.ui.datasource.tvshow.TvShowDataSourceFactory
import id.ergun.mymoviedb.ui.datasource.tvshow.TvShowKeyedDataSource

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowViewModel @ViewModelInject constructor(
    private val useCase: TvShowUseCase,
    private val dataSourceFactory: TvShowDataSourceFactory
) : ViewModel() {

    var favorite: MutableLiveData<Boolean> = MutableLiveData()

    var favoritePage: Boolean = false

    var favoriteState: MutableLiveData<FavoriteModel.Type> = MutableLiveData()

    lateinit var tvShow: TvShow

    fun setSelectedTvShow(tvShow: TvShow) {
        this.tvShow = tvShow
    }

    fun setFavorite(favoritePage: Boolean) {
        this.favoritePage = favoritePage
        dataSourceFactory.favoritePage = favoritePage
    }

    fun getTvShows(): LiveData<PagedList<TvShowVR>> = dataSourceFactory.getTvShows()

    val tvShowState: LiveData<Resource<*>> =
        Transformations.switchMap(
            dataSourceFactory.liveData,
            TvShowKeyedDataSource::state
        )

    fun refresh() {
        dataSourceFactory.liveData.value?.invalidate()
    }

    fun getTvShowDetail(id: Int): LiveData<Resource<TvShow>> {
        return liveData { emit(useCase.getTvShowDetail(id)) }
    }

    private fun getFavoriteTvShow(id: Int) = liveData { emit(useCase.getFavoriteTvShow(id)) }

    fun getFavoriteTvShowById(): LiveData<Resource<TvShow>> =
        if (tvShow.id != null) getFavoriteTvShow(tvShow.id!!)
        else liveData { }

    fun addToFavorite(tvShow: TvShow): LiveData<Long> =
        liveData { emit(useCase.addToFavorite(tvShow)) }

    fun removeFromFavorite(id: Int): LiveData<Int> =
        liveData { emit(useCase.removeFromFavorite(id)) }
}