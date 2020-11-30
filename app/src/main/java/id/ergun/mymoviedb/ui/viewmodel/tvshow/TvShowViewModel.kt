package id.ergun.mymoviedb.ui.viewmodel.tvshow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import id.ergun.mymoviedb.domain.model.TvShow
import id.ergun.mymoviedb.domain.usecase.tvshow.TvShowUseCase
import id.ergun.mymoviedb.ui.view.favorite.FavoriteModel
import id.ergun.mymoviedb.util.Resource

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowViewModel @ViewModelInject constructor(private val useCase: TvShowUseCase) : ViewModel() {

    var favoritePage: Boolean = false

    var favorite: MutableLiveData<Boolean> = MutableLiveData()

    var favoriteState: MutableLiveData<FavoriteModel.Type> = MutableLiveData()

    lateinit var tvShow: TvShow

    fun setSelectedTvShow(tvShow: TvShow) {
        this.tvShow = tvShow
    }

    fun getTvShows() = liveData {
        if (favoritePage) emit(useCase.getFavoriteTvShows())
        else emit(useCase.getTvShows())
    }

    fun getTvShowDetail(id: Int): LiveData<Resource<TvShow>> {
        return liveData { emit(useCase.getTvShowDetail(id)) }
    }


    private fun getFavoriteTvShow(id: Int) = liveData { emit(useCase.getFavoriteTvShow(id)) }

    fun getFavoriteTvShowById() = if (tvShow.id != null) getFavoriteTvShow(tvShow.id!!)
    else liveData {  }

    fun addToFavorite(tvShow: TvShow) = liveData {  emit(useCase.addToFavorite(tvShow)) }

    fun removeFromFavorite(id: Int) = liveData { emit(useCase.removeFromFavorite(id)) }
}