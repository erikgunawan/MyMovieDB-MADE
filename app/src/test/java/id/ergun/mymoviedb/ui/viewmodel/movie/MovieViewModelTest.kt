package id.ergun.mymoviedb.ui.viewmodel.movie

import id.ergun.mymoviedb.data.local.MovieDB
import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.data.repository.movie.MovieRepositoryImpl
import id.ergun.mymoviedb.domain.usecase.movie.MovieUseCase
import id.ergun.mymoviedb.domain.usecase.movie.MovieUseCaseImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private lateinit var useCase: MovieUseCase
    private lateinit var repository: MovieRepository
    private lateinit var localData: MovieDB

    @Before
    fun setUp() {
        localData = MovieDB()
        repository = MovieRepositoryImpl(localData)
        useCase = MovieUseCaseImpl(repository)
        viewModel = MovieViewModel(useCase)
    }

    @Test
    fun getMovies() {
        val movies = viewModel.getMovies()
        assertEquals(12, movies.size)
    }
}