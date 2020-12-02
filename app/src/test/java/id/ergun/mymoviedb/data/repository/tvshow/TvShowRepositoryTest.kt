package id.ergun.mymoviedb.data.repository.tvshow

import id.ergun.mymoviedb.data.Const
import id.ergun.mymoviedb.data.FakeTvShowDB
import id.ergun.mymoviedb.data.local.TvShowDB
import id.ergun.mymoviedb.data.local.model.TvShowLocal
import id.ergun.mymoviedb.data.local.room.dao.TvShowDao
import id.ergun.mymoviedb.data.remote.ApiService
import id.ergun.mymoviedb.data.remote.model.TvShowResponse
import id.ergun.mymoviedb.domain.model.TvShow
import id.ergun.mymoviedb.util.Resource
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class TvShowRepositoryTest {

    private var apiService: ApiService = mock(ApiService::class.java)
    private var dao = mock(TvShowDao::class.java)

    private lateinit var repository: TvShowRepository

    private lateinit var localData: TvShowDB

    private lateinit var  selectedTvShow: TvShow

    @Before
    fun setUp() {
        localData = TvShowDB()
        repository = TvShowRepositoryImpl(apiService, dao)

        selectedTvShow = TvShow.transform(localData)[0]
    }

    @Test
    fun getTvShows() =  runBlocking {
        val response = Response.success(FakeTvShowDB.getTvShowsRemote(localData))
        `when`(apiService.getTvShows(page = Const.INITIAL_PAGE)).thenReturn(response)
        val tvShows = repository.getTvShows(Const.INITIAL_PAGE)
        verify(apiService).getTvShows(page = Const.INITIAL_PAGE)

        assertNotNull(tvShows.data)
        assertEquals(tvShows.status, Resource.Status.SUCCESS)
        assertEquals(tvShows.data?.size?.toLong(), response.body()?.results?.size?.toLong())
    }

    @Test
    fun getTvShowDetail() = runBlocking {
        val response = Response.success(FakeTvShowDB.getTvShowDetailRemote(selectedTvShow))
        Mockito.`when`(apiService.getTvShowDetail(selectedTvShow.id.toString())).thenReturn(response)
        val tvShow = repository.getTvShowDetail(selectedTvShow.id!!)
        Mockito.verify(apiService).getTvShowDetail(selectedTvShow.id.toString())

        assertNotNull(tvShow.data)
        assertEquals(tvShow.status, Resource.Status.SUCCESS)
        assertEquals(tvShow.data?.id, response.body()?.id)
        assertEquals(tvShow.data?.posterPath, response.body()?.posterPath)
        assertEquals(tvShow.data?.overview, response.body()?.overview)
        assertEquals(tvShow.data?.tagLine, response.body()?.tagLine)
        assertEquals(tvShow.data?.title, response.body()?.name)
        assertEquals(tvShow.data?.voteAverage.toString(), response.body()?.voteAverage.toString())
    }

    @Test
    fun getFavoriteTvShows() =  runBlocking {
        val response = FakeTvShowDB.getTvShowsLocal(localData)
        Mockito.`when`(dao.getTvShows()).thenReturn(response)
        val movies = repository.getFavoriteTvShows()
        Mockito.verify(dao).getTvShows()

        TestCase.assertNotNull(movies)
        assertEquals(movies.status, Resource.Status.SUCCESS)
        assertEquals(movies.data?.size?.toLong(), response.size.toLong())
    }

    @Test
    fun getFavoriteTvShow() =  runBlocking {
        val response = FakeTvShowDB.getTvShowDetailLocal(selectedTvShow)
        Mockito.`when`(dao.getTvShow(selectedTvShow.id!!)).thenReturn(response)
        val tvShow = repository.getFavoriteTvShow(selectedTvShow.id!!)
        Mockito.verify(dao).getTvShow(selectedTvShow.id!!)

        TestCase.assertNotNull(tvShow)
        assertEquals(tvShow.status, Resource.Status.SUCCESS)
        assertEquals(tvShow.data?.id, response.id)
        assertEquals(tvShow.data?.posterPath, response.posterPath)
        assertEquals(tvShow.data?.overview, response.overview)
        assertEquals(tvShow.data?.tagLine, response.tagLine)
        assertEquals(tvShow.data?.title, response.name)
        assertEquals(tvShow.data?.voteAverage.toString(), response.voteAverage.toString())
    }


    @Test
    fun addToFavorite() = runBlocking {
        `when`(dao.insertTvShow(TvShowLocal.mapFromDomainModel(selectedTvShow))).thenReturn(
            selectedTvShow.id?.toLong()
        )
        val id = repository.addToFavorite(selectedTvShow)
        verify(dao).insertTvShow(TvShowLocal.mapFromDomainModel(selectedTvShow))

        TestCase.assertNotNull(id)
        assertEquals(id, selectedTvShow.id?.toLong())
    }

    @Test
    fun removeFromFavorite() = runBlocking {
        `when`(dao.deleteById(selectedTvShow.id!!)).thenReturn(selectedTvShow.id!!)
        val id = repository.removeFromFavorite(selectedTvShow.id!!)
        Mockito.verify(dao).deleteById(selectedTvShow.id!!)

        TestCase.assertNotNull(id)
        assertEquals(id, selectedTvShow.id!!)
    }

    @Test
    fun getErrorTvShows() = runBlocking {
        val response = Response.error<TvShowResponse>(500, ResponseBody.create(null,""))
        `when`(apiService.getTvShows(page = Const.INITIAL_PAGE)).thenReturn(response)
        val tvShows = repository.getTvShows(Const.INITIAL_PAGE)
        verify(apiService).getTvShows(page = Const.INITIAL_PAGE)

        Assert.assertNull(tvShows.data)
        assertEquals(tvShows.status, Resource.Status.ERROR)
    }

    @Test
    fun getErrorTvShowDetail() = runBlocking {
        val response = Response.error<TvShowResponse.Result>(500, ResponseBody.create(null,""))
        `when`(apiService.getTvShowDetail(selectedTvShow.id.toString())).thenReturn(response)
        val tvShow = repository.getTvShowDetail(selectedTvShow.id!!)
        verify(apiService).getTvShowDetail(selectedTvShow.id.toString())

        Assert.assertNull(tvShow.data)
        assertEquals(tvShow.status, Resource.Status.ERROR)
    }
}