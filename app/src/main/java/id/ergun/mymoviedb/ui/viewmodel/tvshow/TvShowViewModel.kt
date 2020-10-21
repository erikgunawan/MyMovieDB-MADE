package id.ergun.mymoviedb.ui.viewmodel.tvshow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import id.ergun.mymoviedb.domain.usecase.tvshow.TvShowUseCase

/**
 * Created by alfacart on 21/10/20.
 */
class TvShowViewModel @ViewModelInject constructor(private val useCase: TvShowUseCase) : ViewModel() {
    fun getTvShows() = useCase.getTvShows()
}