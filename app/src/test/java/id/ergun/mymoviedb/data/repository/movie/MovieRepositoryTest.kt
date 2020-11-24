package id.ergun.mymoviedb.data.repository.movie

import id.ergun.mymoviedb.data.FakeMovieDB
import id.ergun.mymoviedb.data.local.MovieDB
import id.ergun.mymoviedb.data.remote.ApiService
import id.ergun.mymoviedb.data.remote.model.MovieResponse
import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.util.Resource
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    private var apiService: ApiService = mock(ApiService::class.java)

    private lateinit var repository: MovieRepository

    private lateinit var localData: MovieDB

    private lateinit var  selectedMovie: Movie

    @Before
    fun setUp() {
        localData = MovieDB()
        repository = MovieRepositoryImpl(apiService)

        selectedMovie = Movie.transform(localData)[0]
    }

    @Test
    fun getMovies() =  runBlocking {
        val response = Response.success(FakeMovieDB.getMoviesRemote(localData))
        `when`(apiService.getMovies(page = 1)).thenReturn(response)
        val movies = repository.getMovies()
        verify(apiService).getMovies(page = 1)

        assertNotNull(movies)
        assertEquals(movies.status, id.ergun.mymoviedb.util.Resource.Status.SUCCESS)
        assertEquals(movies.data?.size?.toLong(), response.body()?.results?.size?.toLong())
    }

    @Test
    fun getMovieDetail() = runBlocking {
        val response = Response.success(FakeMovieDB.getMovieDetailRemote(selectedMovie))
        `when`(apiService.getMovieDetail(selectedMovie.id.toString())).thenReturn(response)
        val movie = repository.getMovieDetail(selectedMovie.id!!)
        verify(apiService).getMovieDetail(selectedMovie.id.toString())

        assertNotNull(movie)
        assertEquals(movie.status, id.ergun.mymoviedb.util.Resource.Status.SUCCESS)
        assertEquals(movie.data?.id, response.body()?.id)
        assertEquals(movie.data?.posterPath, response.body()?.posterPath)
        assertEquals(movie.data?.overview, response.body()?.overview)
        assertEquals(movie.data?.tagLine, response.body()?.tagLine)
        assertEquals(movie.data?.title, response.body()?.title)
        assertEquals(movie.data?.voteAverage.toString(), response.body()?.voteAverage.toString())
    }

    @Test
    fun getErrorMovies() =  runBlocking {
        val response = Response.error<MovieResponse>(500, ResponseBody.create(null,""))
        `when`(apiService.getMovies(page = 1)).thenReturn(response)
        val movies = repository.getMovies()
        verify(apiService).getMovies(page = 1)

        Assert.assertNull(movies.data)
        assertEquals(movies.status, Resource.Status.ERROR)
    }

    @Test
    fun getErrorMovieDetail() = runBlocking {
        val response = Response.error<MovieResponse.Result>(500, ResponseBody.create(null,""))
        `when`(apiService.getMovieDetail(selectedMovie.id.toString())).thenReturn(response)
        val movie = repository.getMovieDetail(selectedMovie.id!!)
        verify(apiService).getMovieDetail(selectedMovie.id.toString())

        Assert.assertNull(movie.data)
        assertEquals(movie.status, Resource.Status.ERROR)
    }
}