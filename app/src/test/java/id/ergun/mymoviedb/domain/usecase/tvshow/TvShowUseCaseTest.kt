package id.ergun.mymoviedb.domain.usecase.tvshow

import id.ergun.mymoviedb.data.Const
import id.ergun.mymoviedb.data.FakeErrorResponse
import id.ergun.mymoviedb.data.FakeTvShowDB
import id.ergun.mymoviedb.data.local.TvShowDB
import id.ergun.mymoviedb.data.repository.tvshow.TvShowRepository
import id.ergun.mymoviedb.data.repository.tvshow.TvShowRepositoryImpl
import id.ergun.mymoviedb.domain.model.TvShow
import id.ergun.mymoviedb.util.Resource
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TvShowUseCaseTest {

    private var repository: TvShowRepository = Mockito.mock(TvShowRepositoryImpl::class.java)

    private lateinit var useCase: TvShowUseCase

    private lateinit var localData: TvShowDB

    private lateinit var  selectedTvShow: TvShow

    @Before
    fun setUp() {
        localData = TvShowDB()

        useCase = TvShowUseCaseImpl(repository)

        selectedTvShow = TvShow.transform(localData)[0]
    }

    @Test
    fun getTvShows() = runBlocking {
        val repoTvShows = FakeTvShowDB.getTvShows(localData)
        Mockito.`when`(repository.getTvShows(Const.INITIAL_PAGE)).thenReturn(repoTvShows)
        val tvShows = useCase.getTvShows(Const.INITIAL_PAGE)
        Mockito.verify(repository).getTvShows(Const.INITIAL_PAGE)

        Assert.assertNotNull(tvShows.data)
        Assert.assertEquals(tvShows.status, Resource.Status.SUCCESS)
        Assert.assertEquals(tvShows.data?.size?.toLong(), repoTvShows.data?.size?.toLong())
    }

    @Test
    fun getTvShowDetail() = runBlocking {
        val repoTvShow = FakeTvShowDB.getTvShowDetail(selectedTvShow)
        Mockito.`when`(repository.getTvShowDetail(selectedTvShow.id!!)).thenReturn(repoTvShow)
        val tvShow = useCase.getTvShowDetail(selectedTvShow.id!!)
        Mockito.verify(repository).getTvShowDetail(selectedTvShow.id!!)

        Assert.assertNotNull(tvShow.data)
        Assert.assertEquals(tvShow.status, Resource.Status.SUCCESS)
        Assert.assertEquals(tvShow.data?.id, repoTvShow.data?.id)
        Assert.assertEquals(tvShow.data?.posterPath, repoTvShow.data?.posterPath)
        Assert.assertEquals(tvShow.data?.overview, repoTvShow.data?.overview)
        Assert.assertEquals(tvShow.data?.tagLine, repoTvShow.data?.tagLine)
        Assert.assertEquals(tvShow.data?.title, repoTvShow.data?.title)
        Assert.assertEquals(tvShow.data?.voteAverage.toString(), repoTvShow.data?.voteAverage.toString())
    }

    @Test
    fun getFavoriteTvShows() = runBlocking {
        val repoTvShows = FakeTvShowDB.getTvShows(localData)
        Mockito.`when`(repository.getFavoriteTvShows()).thenReturn(repoTvShows)
        val tvShows = useCase.getFavoriteTvShows()
        Mockito.verify(repository).getFavoriteTvShows()

        Assert.assertNotNull(tvShows.data)
        Assert.assertEquals(tvShows.status, Resource.Status.SUCCESS)
        Assert.assertEquals(tvShows.data?.size?.toLong(), repoTvShows.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShow() = runBlocking {
        val repoTvShow = FakeTvShowDB.getTvShowDetail(selectedTvShow)
        Mockito.`when`(repository.getFavoriteTvShow(selectedTvShow.id!!)).thenReturn(repoTvShow)
        val tvShow = useCase.getFavoriteTvShow(selectedTvShow.id!!)
        Mockito.verify(repository).getFavoriteTvShow(selectedTvShow.id!!)

        Assert.assertNotNull(tvShow.data)
        Assert.assertEquals(tvShow.status, Resource.Status.SUCCESS)
        Assert.assertEquals(tvShow.data?.id, repoTvShow.data?.id)
        Assert.assertEquals(tvShow.data?.posterPath, repoTvShow.data?.posterPath)
        Assert.assertEquals(tvShow.data?.overview, repoTvShow.data?.overview)
        Assert.assertEquals(tvShow.data?.tagLine, repoTvShow.data?.tagLine)
        Assert.assertEquals(tvShow.data?.title, repoTvShow.data?.title)
        Assert.assertEquals(tvShow.data?.voteAverage.toString(), repoTvShow.data?.voteAverage.toString())
    }

    @Test
    fun addToFavorite() = runBlocking {
        Mockito.`when`(repository.addToFavorite(selectedTvShow)).thenReturn(
            selectedTvShow.id?.toLong()
        )
        val id = useCase.addToFavorite(selectedTvShow)
        Mockito.verify(repository).addToFavorite(selectedTvShow)

        TestCase.assertNotNull(id)
        Assert.assertEquals(id, selectedTvShow.id?.toLong())
    }

    @Test
    fun removeFromFavorite() = runBlocking {
        Mockito.`when`(repository.removeFromFavorite(selectedTvShow.id!!)).thenReturn(selectedTvShow.id!!)
        val id = useCase.removeFromFavorite(selectedTvShow.id!!)
        Mockito.verify(repository).removeFromFavorite(selectedTvShow.id!!)

        TestCase.assertNotNull(id)
        Assert.assertEquals(id, selectedTvShow.id!!)
    }
    
    @Test
    fun getErrorTvShows() = runBlocking {
        val repoTvShows = FakeErrorResponse.getError<ArrayList<TvShow>>()
        Mockito.`when`(repository.getTvShows(Const.INITIAL_PAGE)).thenReturn(repoTvShows)
        val tvShows = useCase.getTvShows(Const.INITIAL_PAGE)
        Mockito.verify(repository).getTvShows(Const.INITIAL_PAGE)

        Assert.assertNull(tvShows.data)
        Assert.assertEquals(tvShows.status, Resource.Status.ERROR)
    }

    @Test
    fun getErrorTvShowDetail() = runBlocking {
        val repoTvShow = FakeErrorResponse.getError<TvShow>()
        Mockito.`when`(repository.getTvShowDetail(selectedTvShow.id!!)).thenReturn(repoTvShow)
        val tvShow = useCase.getTvShowDetail(selectedTvShow.id!!)
        Mockito.verify(repository).getTvShowDetail(selectedTvShow.id!!)

        Assert.assertNull(tvShow.data)
        Assert.assertEquals(tvShow.status, Resource.Status.ERROR)
    }
}