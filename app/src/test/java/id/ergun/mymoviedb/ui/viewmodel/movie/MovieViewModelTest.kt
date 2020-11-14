package id.ergun.mymoviedb.ui.viewmodel.movie

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import id.ergun.mymoviedb.data.local.MovieDB
import id.ergun.mymoviedb.data.local.TvShowDB
import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.data.repository.movie.MovieRepositoryImpl
import id.ergun.mymoviedb.data.repository.tvshow.TvShowRepository
import id.ergun.mymoviedb.data.repository.tvshow.TvShowRepositoryImpl
import id.ergun.mymoviedb.domain.usecase.movie.MovieUseCase
import id.ergun.mymoviedb.domain.usecase.movie.MovieUseCaseImpl
import id.ergun.mymoviedb.domain.usecase.tvshow.TvShowUseCase
import id.ergun.mymoviedb.domain.usecase.tvshow.TvShowUseCaseImpl
import id.ergun.mymoviedb.ui.viewmodel.tvshow.TvShowViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

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