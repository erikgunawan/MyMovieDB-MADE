package id.ergun.mymoviedb.ui.viewmodel.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import id.ergun.mymoviedb.data.FakeErrorResponse
import id.ergun.mymoviedb.data.FakeMovieDB
import id.ergun.mymoviedb.data.local.MovieDB
import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.domain.usecase.movie.MovieUseCase
import id.ergun.mymoviedb.domain.usecase.movie.MovieUseCaseImpl
import id.ergun.mymoviedb.ui.datasource.movie.MovieDataSourceFactory
import id.ergun.mymoviedb.ui.view.movie.MovieVR
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
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var useCase: MovieUseCase = mock(MovieUseCaseImpl::class.java)

    private var dataSourceFactory = mock(MovieDataSourceFactory::class.java)

    private lateinit var viewModel: MovieViewModel

    private lateinit var localData: MovieDB

    private lateinit var  selectedMovie: Movie

    @Before
    fun setUp() {
        viewModel = MovieViewModel(useCase, dataSourceFactory)

        localData = MovieDB()

        selectedMovie = Movie.transform(localData)[0]
    }

    @Test
    fun getMovies() = runBlocking {
        val useCaseMovies = FakeMovieDB.getMovies(localData)

        val pagedList = MutableLiveData<PagedList<MovieVR>>()
        pagedList.value = PagedListUtil.mockPagedList(MovieVR.transform(useCaseMovies.data!!))

        `when`(dataSourceFactory.getMovies()).thenReturn(pagedList)
        val movies = LiveDataTestUtil.getValue(viewModel.getMovies())
        verify(dataSourceFactory).getMovies()

        assertNotNull(movies)
        assertEquals(movies.size.toLong(), useCaseMovies.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() = runBlocking {
        val useCaseMovie = FakeMovieDB.getMovieDetail(selectedMovie)
        `when`(useCase.getMovieDetail(selectedMovie.id!!)).thenReturn(useCaseMovie)
        val movie = LiveDataTestUtil.getValue(viewModel.getMovieDetail(selectedMovie.id!!))
        verify(useCase).getMovieDetail(selectedMovie.id!!)

        assertNotNull(movie.data)
        assertEquals(movie.status, Resource.Status.SUCCESS)
        assertEquals(movie.data?.id, useCaseMovie.data?.id)
        assertEquals(movie.data?.posterPath, useCaseMovie.data?.posterPath)
        assertEquals(movie.data?.overview, useCaseMovie.data?.overview)
        assertEquals(movie.data?.tagLine, useCaseMovie.data?.tagLine)
        assertEquals(movie.data?.title, useCaseMovie.data?.title)
        assertEquals(movie.data?.voteAverage.toString(), useCaseMovie.data?.voteAverage.toString())
    }

    @Test
    fun getFavoriteMovie() = runBlocking {
        val useCaseMovie = FakeMovieDB.getMovieDetail(selectedMovie)
        `when`(useCase.getFavoriteMovie(selectedMovie.id!!)).thenReturn(useCaseMovie)
        viewModel.setSelectedMovie(selectedMovie)
        val movie = LiveDataTestUtil.getValue(viewModel.getFavoriteMovieById())
        verify(useCase).getFavoriteMovie(selectedMovie.id!!)

        assertNotNull(movie.data)
        assertEquals(movie.status, Resource.Status.SUCCESS)
        assertEquals(movie.data?.id, useCaseMovie.data?.id)
        assertEquals(movie.data?.posterPath, useCaseMovie.data?.posterPath)
        assertEquals(movie.data?.overview, useCaseMovie.data?.overview)
        assertEquals(movie.data?.tagLine, useCaseMovie.data?.tagLine)
        assertEquals(movie.data?.title, useCaseMovie.data?.title)
        assertEquals(movie.data?.voteAverage.toString(), useCaseMovie.data?.voteAverage.toString())
    }

    @Test
    fun getErrorMovies() = runBlocking {
        val pagedList = MutableLiveData<PagedList<MovieVR>>()

        `when`(dataSourceFactory.getMovies()).thenReturn(pagedList)
        val movies = LiveDataTestUtil.getValue(viewModel.getMovies())
        verify(dataSourceFactory).getMovies()

        assertNull(movies)
    }

    @Test
    fun getErrorMovieDetail() = runBlocking {
        val useCaseMovie = FakeErrorResponse.getError<Movie>()
        `when`(useCase.getMovieDetail(0)).thenReturn(useCaseMovie)
        val movie = LiveDataTestUtil.getValue(viewModel.getMovieDetail(0))
        verify(useCase).getMovieDetail(0)

        assertNull(movie.data)
        assertEquals(movie.status, Resource.Status.ERROR)
    }
}