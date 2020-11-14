package id.ergun.mymoviedb.ui.viewmodel.tvshow

import id.ergun.mymoviedb.data.local.TvShowDB
import id.ergun.mymoviedb.data.repository.tvshow.TvShowRepository
import id.ergun.mymoviedb.data.repository.tvshow.TvShowRepositoryImpl
import id.ergun.mymoviedb.domain.usecase.tvshow.TvShowUseCase
import id.ergun.mymoviedb.domain.usecase.tvshow.TvShowUseCaseImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    private lateinit var useCase: TvShowUseCase
    private lateinit var repository: TvShowRepository
    private lateinit var localData: TvShowDB

    @Before
    fun setUp() {
        localData = TvShowDB()
        repository = TvShowRepositoryImpl(localData)
        useCase = TvShowUseCaseImpl(repository)
        viewModel = TvShowViewModel(useCase)
    }

    @Test
    fun getTvShows() {
        val tvShows = viewModel.getTvShows()
        assertEquals(10, tvShows.size)
    }
}