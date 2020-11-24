package id.ergun.mymoviedb.data.repository.tvshow

import id.ergun.mymoviedb.data.FakeTvShowDB
import id.ergun.mymoviedb.data.local.TvShowDB
import id.ergun.mymoviedb.data.remote.ApiService
import id.ergun.mymoviedb.data.remote.model.TvShowResponse
import id.ergun.mymoviedb.domain.model.TvShow
import id.ergun.mymoviedb.util.Resource
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class TvShowRepositoryTest {

    private var apiService: ApiService = mock(ApiService::class.java)

    private lateinit var repository: TvShowRepository

    private lateinit var localData: TvShowDB

    private lateinit var  selectedTvShow: TvShow

    @Before
    fun setUp() {
        localData = TvShowDB()
        repository = TvShowRepositoryImpl(apiService)

        selectedTvShow = TvShow.transform(localData)[0]
    }

    @Test
    fun getTvShows() =  runBlocking {
        val response = Response.success(FakeTvShowDB.getTvShowsRemote(localData))
        Mockito.`when`(apiService.getTvShows(page = 1)).thenReturn(response)
        val tvShows = repository.getTvShows()
        Mockito.verify(apiService).getTvShows(page = 1)

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
    fun getErrorTvShows() = runBlocking {
        val response = Response.error<TvShowResponse>(500, ResponseBody.create(null,""))
        Mockito.`when`(apiService.getTvShows(page = 1)).thenReturn(response)
        val tvShows = repository.getTvShows()
        Mockito.verify(apiService).getTvShows(page = 1)

        Assert.assertNull(tvShows.data)
        Assert.assertEquals(tvShows.status, Resource.Status.ERROR)
    }

    @Test
    fun getErrorTvShowDetail() = runBlocking {
        val response = Response.error<TvShowResponse.Result>(500, ResponseBody.create(null,""))
        Mockito.`when`(apiService.getTvShowDetail(selectedTvShow.id.toString())).thenReturn(response)
        val tvShow = repository.getTvShowDetail(selectedTvShow.id!!)
        Mockito.verify(apiService).getTvShowDetail(selectedTvShow.id.toString())

        Assert.assertNull(tvShow.data)
        Assert.assertEquals(tvShow.status, Resource.Status.ERROR)
    }
}