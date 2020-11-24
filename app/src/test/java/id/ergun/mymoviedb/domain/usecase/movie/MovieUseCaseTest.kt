package id.ergun.mymoviedb.domain.usecase.movie

import id.ergun.mymoviedb.data.FakeErrorResponse
import id.ergun.mymoviedb.data.FakeMovieDB
import id.ergun.mymoviedb.data.local.MovieDB
import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.data.repository.movie.MovieRepositoryImpl
import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieUseCaseTest {

    private var repository: MovieRepository = mock(MovieRepositoryImpl::class.java)

    private lateinit var useCase: MovieUseCase

    private lateinit var localData: MovieDB

    private lateinit var  selectedMovie: Movie

    @Before
    fun setUp() {
        localData = MovieDB()

        useCase = MovieUseCaseImpl(repository)

        selectedMovie = Movie.transform(localData)[0]
    }

    @Test
    fun getMovies() = runBlocking {
        val repoMovies = FakeMovieDB.getMovies(localData)
        `when`(repository.getMovies()).thenReturn(repoMovies)
        val movies = useCase.getMovies()
        verify(repository).getMovies()

        assertNotNull(movies)
        assertEquals(movies.status, id.ergun.mymoviedb.util.Resource.Status.SUCCESS)
        assertEquals(movies.data?.size?.toLong(), repoMovies.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() = runBlocking {
        val repoMovie = FakeMovieDB.getMovieDetail(selectedMovie)
        `when`(repository.getMovieDetail(selectedMovie.id!!)).thenReturn(repoMovie)
        val movie = useCase.getMovieDetail(selectedMovie.id!!)
        verify(repository).getMovieDetail(selectedMovie.id!!)

        assertNotNull(movie)
        assertEquals(movie.status, id.ergun.mymoviedb.util.Resource.Status.SUCCESS)
        assertEquals(movie.data?.id, repoMovie.data?.id)
        assertEquals(movie.data?.posterPath, repoMovie.data?.posterPath)
        assertEquals(movie.data?.overview, repoMovie.data?.overview)
        assertEquals(movie.data?.tagLine, repoMovie.data?.tagLine)
        assertEquals(movie.data?.title, repoMovie.data?.title)
        assertEquals(movie.data?.voteAverage.toString(), repoMovie.data?.voteAverage.toString())
    }

    @Test
    fun getErrorMovies() = runBlocking {
        val repoMovies = FakeErrorResponse.getError<ArrayList<Movie>>()
        `when`(repository.getMovies()).thenReturn(repoMovies)
        val movies = useCase.getMovies()
        verify(repository).getMovies()

        Assert.assertNull(movies.data)
        assertEquals(movies.status, Resource.Status.ERROR)
    }

    @Test
    fun getErrorMovieDetail() = runBlocking {
        val repoMovie = FakeErrorResponse.getError<Movie>()
        `when`(repository.getMovieDetail(selectedMovie.id!!)).thenReturn(repoMovie)
        val movie = useCase.getMovieDetail(selectedMovie.id!!)
        verify(repository).getMovieDetail(selectedMovie.id!!)

        Assert.assertNull(movie.data)
        assertEquals(movie.status, Resource.Status.ERROR)
    }
}