package id.ergun.mymoviedb.ui.viewmodel.movie

import id.ergun.mymoviedb.data.local.MovieDB
import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.data.repository.movie.MovieRepositoryImpl
import id.ergun.mymoviedb.domain.model.Movie
import id.ergun.mymoviedb.domain.usecase.movie.MovieUseCase
import id.ergun.mymoviedb.domain.usecase.movie.MovieUseCaseImpl
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private lateinit var useCase: MovieUseCase
    private lateinit var repository: MovieRepository
    private lateinit var localData: MovieDB

    private lateinit var dummyMovie: id.ergun.mymoviedb.data.local.model.Movie
    private lateinit var  selectedMovie: Movie

    @Before
    fun setUp() {
        localData = MovieDB()
        repository = MovieRepositoryImpl(localData)
        useCase = MovieUseCaseImpl(repository)
        viewModel = MovieViewModel(useCase)

        dummyMovie = localData.getMovies()[0]
        selectedMovie = Movie.transform(localData)[0]
    }

    @Test
    fun getMovies() {
        val movies = viewModel.getMovies()
        Assert.assertNotNull(movies)
        assertEquals(12, movies.size)
    }

    @Test
    fun getSelectedMovie() {
        viewModel.setSelectedMovie(selectedMovie)
        Assert.assertNotNull(selectedMovie)
        assertEquals(dummyMovie.id, selectedMovie.id)
        assertEquals(dummyMovie.image, selectedMovie.image)
        assertEquals(dummyMovie.overview, selectedMovie.overview)
        assertEquals(dummyMovie.tagline, selectedMovie.tagline)
        assertEquals(dummyMovie.title, selectedMovie.title)
        assertEquals(dummyMovie.voteAverage.toString(), selectedMovie.voteAverage.toString())
    }
}