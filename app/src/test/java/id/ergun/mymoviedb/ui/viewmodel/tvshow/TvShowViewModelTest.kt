package id.ergun.mymoviedb.ui.viewmodel.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.ergun.mymoviedb.data.FakeErrorResponse
import id.ergun.mymoviedb.data.FakeTvShowDB
import id.ergun.mymoviedb.data.local.TvShowDB
import id.ergun.mymoviedb.domain.model.TvShow
import id.ergun.mymoviedb.domain.usecase.tvshow.TvShowUseCase
import id.ergun.mymoviedb.domain.usecase.tvshow.TvShowUseCaseImpl
import id.ergun.mymoviedb.util.LiveDataTestUtil
import id.ergun.mymoviedb.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var useCase: TvShowUseCase = mock(TvShowUseCaseImpl::class.java)

    private lateinit var viewModel: TvShowViewModel

    private lateinit var localData: TvShowDB

    private lateinit var  selectedTvShow: TvShow

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(useCase)

        localData = TvShowDB()

        selectedTvShow = TvShow.transform(localData)[0]
    }

    @Test
    fun getTvShows() = runBlocking {
        val useCaseTvShows = FakeTvShowDB.getTvShows(localData)
        `when`(useCase.getTvShows()).thenReturn(useCaseTvShows)
        val tvShows = LiveDataTestUtil.getValue(viewModel.getTvShows())
        verify(useCase).getTvShows()

        assertNotNull(tvShows.data)
        assertEquals(tvShows.status, Resource.Status.SUCCESS)
        assertEquals(tvShows.data?.size?.toLong(), useCaseTvShows.data?.size?.toLong())
    }

    @Test
    fun getTvShowDetail() = runBlocking {
        val useCaseTvShow = FakeTvShowDB.getTvShowDetail(selectedTvShow)
        `when`(useCase.getTvShowDetail(selectedTvShow.id!!)).thenReturn(useCaseTvShow)
        val tvShow = LiveDataTestUtil.getValue(viewModel.getTvShowDetail(selectedTvShow.id!!))
        verify(useCase).getTvShowDetail(selectedTvShow.id!!)

        assertNotNull(tvShow.data)
        assertEquals(tvShow.status, Resource.Status.SUCCESS)
        assertEquals(tvShow.data?.id, useCaseTvShow.data?.id)
        assertEquals(tvShow.data?.posterPath, useCaseTvShow.data?.posterPath)
        assertEquals(tvShow.data?.overview, useCaseTvShow.data?.overview)
        assertEquals(tvShow.data?.tagLine, useCaseTvShow.data?.tagLine)
        assertEquals(tvShow.data?.title, useCaseTvShow.data?.title)
        assertEquals(
            tvShow.data?.voteAverage.toString(),
            useCaseTvShow.data?.voteAverage.toString()
        )
    }

    @Test
    fun getErrorTvShows() = runBlocking {
        val useCaseTvShows = FakeErrorResponse.getError<ArrayList<TvShow>>()
        `when`(useCase.getTvShows()).thenReturn(useCaseTvShows)
        val tvShows = LiveDataTestUtil.getValue(viewModel.getTvShows())
        verify(useCase).getTvShows()

        assertNull(tvShows.data)
        assertEquals(tvShows.status, Resource.Status.ERROR)
    }

    @Test
    fun getErrorTvShowDetail() = runBlocking {
        val useCaseTvShow = FakeErrorResponse.getError<TvShow>()
        `when`(useCase.getTvShowDetail(selectedTvShow.id!!)).thenReturn(useCaseTvShow)
        val tvShow = LiveDataTestUtil.getValue(viewModel.getTvShowDetail(selectedTvShow.id!!))
        verify(useCase).getTvShowDetail(selectedTvShow.id!!)

        assertNull(tvShow.data)
        assertEquals(tvShow.status, Resource.Status.ERROR)
    }
}