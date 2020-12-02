package id.ergun.mymoviedb.ui.viewmodel.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import id.ergun.mymoviedb.data.FakeErrorResponse
import id.ergun.mymoviedb.data.FakeTvShowDB
import id.ergun.mymoviedb.data.local.TvShowDB
import id.ergun.mymoviedb.domain.model.TvShow
import id.ergun.mymoviedb.domain.usecase.tvshow.TvShowUseCase
import id.ergun.mymoviedb.domain.usecase.tvshow.TvShowUseCaseImpl
import id.ergun.mymoviedb.ui.datasource.tvshow.TvShowDataSourceFactory
import id.ergun.mymoviedb.ui.view.tvshow.TvShowVR
import id.ergun.mymoviedb.util.LiveDataTestUtil
import id.ergun.mymoviedb.util.PagedListUtil
import id.ergun.mymoviedb.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class TvShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var useCase: TvShowUseCase = mock(TvShowUseCaseImpl::class.java)

    private var dataSourceFactory = mock(TvShowDataSourceFactory::class.java)

    private lateinit var viewModel: TvShowViewModel

    private lateinit var localData: TvShowDB

    private lateinit var  selectedTvShow: TvShow

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(useCase, dataSourceFactory)

        localData = TvShowDB()

        selectedTvShow = TvShow.transform(localData)[0]
    }

    @Test
    fun getTvShows() = runBlocking {
        val useCaseTvShows = FakeTvShowDB.getTvShows(localData)

        val pagedList = MutableLiveData<PagedList<TvShowVR>>()
        pagedList.value = PagedListUtil.mockPagedList(TvShowVR.transform(useCaseTvShows.data!!))

        `when`(dataSourceFactory.getTvShows()).thenReturn(pagedList)
        val tvShows = LiveDataTestUtil.getValue(viewModel.getTvShows())
        verify(dataSourceFactory).getTvShows()

        assertNotNull(tvShows)
        assertEquals(tvShows.size.toLong(), useCaseTvShows.data?.size?.toLong())
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
    fun getFavoriteTvShow() = runBlocking {
        val useCaseTvShow = FakeTvShowDB.getTvShowDetail(selectedTvShow)
        `when`(useCase.getFavoriteTvShow(selectedTvShow.id!!)).thenReturn(useCaseTvShow)
        viewModel.setSelectedTvShow(selectedTvShow)
        val tvShow = LiveDataTestUtil.getValue(viewModel.getFavoriteTvShowById())
        verify(useCase).getFavoriteTvShow(selectedTvShow.id!!)

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
        val pagedList = MutableLiveData<PagedList<TvShowVR>>()

        `when`(dataSourceFactory.getTvShows()).thenReturn(pagedList)
        val tvShows = LiveDataTestUtil.getValue(viewModel.getTvShows())
        verify(dataSourceFactory).getTvShows()

        assertNull(tvShows)
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