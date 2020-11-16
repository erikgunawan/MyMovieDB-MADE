package id.ergun.mymoviedb.ui.viewmodel.tvshow

import id.ergun.mymoviedb.data.local.TvShowDB
import id.ergun.mymoviedb.data.repository.tvshow.TvShowRepository
import id.ergun.mymoviedb.data.repository.tvshow.TvShowRepositoryImpl
import id.ergun.mymoviedb.domain.model.TvShow
import id.ergun.mymoviedb.domain.usecase.tvshow.TvShowUseCase
import id.ergun.mymoviedb.domain.usecase.tvshow.TvShowUseCaseImpl
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    private lateinit var useCase: TvShowUseCase
    private lateinit var repository: TvShowRepository
    private lateinit var localData: TvShowDB

    private lateinit var dummyTvShow: id.ergun.mymoviedb.data.local.model.TvShow
    private lateinit var  selectedTvShow: TvShow

    @Before
    fun setUp() {
        localData = TvShowDB()
        repository = TvShowRepositoryImpl(localData)
        useCase = TvShowUseCaseImpl(repository)
        viewModel = TvShowViewModel(useCase)

        dummyTvShow = localData.getTvShows()[0]
        selectedTvShow = TvShow.transform(localData)[0]
    }

    @Test
    fun getTvShows() {
        val tvShows = viewModel.getTvShows()
        assertEquals(10, tvShows.size)
    }

    @Test
    fun getSelectedTvShow() {
        viewModel.setSelectedTvShow(selectedTvShow)
        Assert.assertNotNull(selectedTvShow)
        assertEquals(dummyTvShow.id, selectedTvShow.id)
        assertEquals(dummyTvShow.image, selectedTvShow.image)
        assertEquals(dummyTvShow.overview, selectedTvShow.overview)
        assertEquals(dummyTvShow.tagline, selectedTvShow.tagline)
        assertEquals(dummyTvShow.name, selectedTvShow.title)
        assertEquals(dummyTvShow.voteAverage.toString(), selectedTvShow.voteAverage.toString())
    }
}