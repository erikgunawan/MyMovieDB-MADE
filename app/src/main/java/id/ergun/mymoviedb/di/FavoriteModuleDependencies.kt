package id.ergun.mymoviedb.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ergun.mymoviedb.core.domain.usecase.movie.MovieUseCase
import id.ergun.mymoviedb.core.domain.usecase.tvshow.TvShowUseCase

/**
 * Created by alfacart on 17/01/21.
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

  fun movieUseCase(): MovieUseCase
  fun tvShowUseCase(): TvShowUseCase
}