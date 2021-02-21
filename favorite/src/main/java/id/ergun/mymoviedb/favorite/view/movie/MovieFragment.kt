package id.ergun.mymoviedb.favorite.view.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.EntryPointAccessors
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.core.util.Const
import id.ergun.mymoviedb.core.util.Resource
import id.ergun.mymoviedb.core.util.eventbus.FavoriteEvent
import id.ergun.mymoviedb.core.util.gone
import id.ergun.mymoviedb.core.util.loadImage
import id.ergun.mymoviedb.core.util.visible
import id.ergun.mymoviedb.core.view.movie.MovieAdapter
import id.ergun.mymoviedb.databinding.MovieFragmentBinding
import id.ergun.mymoviedb.di.FavoriteModuleDependencies
import id.ergun.mymoviedb.favorite.di.DaggerFavoriteFragmentComponent
import id.ergun.mymoviedb.favorite.viewmodel.movie.MovieViewModel
import id.ergun.mymoviedb.favorite.viewmodel.movie.MovieViewModelFactory
import id.ergun.mymoviedb.ui.view.movie.detail.MovieDetailActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject


/**
 * Created by alfacart on 21/10/20.
 */
class MovieFragment : Fragment() {

  companion object {
    private const val ARGUMENT_FAVORITE = "ARGUMENT_FAVORITE"
    private const val ARGUMENT_PAGE_TYPE = "ARGUMENT_PAGE_TYPE"
    fun newInstance(
        pageType: Int,
        favorite: Boolean = false
    ): MovieFragment {
      val fragment = MovieFragment()
      val argument = Bundle()
      argument.putInt(ARGUMENT_PAGE_TYPE, pageType)
      argument.putBoolean(ARGUMENT_FAVORITE, favorite)
      fragment.arguments = argument
      return fragment
    }
  }

  private lateinit var binding: MovieFragmentBinding

  @Inject
  lateinit var factory: MovieViewModelFactory

  private val movieViewModel: MovieViewModel by viewModels {
    factory
  }

  private lateinit var movieAdapter: MovieAdapter

  override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    DaggerFavoriteFragmentComponent.builder()
        .context(requireContext())
        .appDependencies(
            EntryPointAccessors.fromApplication(
                requireContext().applicationContext,
                FavoriteModuleDependencies::class.java
            )
        )
        .build()
        .inject(this)
    binding = MovieFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initEventBus()
    loadArguments()
    initView()
    initAction()
    getMovies()
  }

  private fun initEventBus() {
    if (!EventBus.getDefault().isRegistered(this))
      EventBus.getDefault().register(this)
  }

  private fun loadArguments() {
    if (arguments == null) return

    movieViewModel.pageType = arguments?.getInt(ARGUMENT_PAGE_TYPE, Const.MOVIE_TYPE)
        ?: Const.MOVIE_TYPE
    movieViewModel.setFavorite(arguments?.getBoolean(ARGUMENT_FAVORITE, false) ?: false)
  }

  private fun initView() {
    movieAdapter = MovieAdapter()

    with(binding.rvMovie) {
      layoutManager = LinearLayoutManager(context)
      setHasFixedSize(true)
      adapter = movieAdapter
    }
  }

  private fun initAction() {
    movieAdapter.itemClickListener = { view, movie ->
      val intent = MovieDetailActivity.newIntent(requireContext(), movieViewModel.pageType, movie)

      val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(),
          view, Const.POSTER_TRANSITION_NAME)

      startActivity(intent, options.toBundle())
    }

    binding.viewWarning.btnWarning.setOnClickListener {
      movieViewModel.refresh()
    }
  }

  private fun getMovies() {
    movieViewModel.getMovies().observe(requireActivity()) {
      movieAdapter.submitList(it)
      movieAdapter.notifyDataSetChanged()
    }

    movieViewModel.getState().observe(requireActivity()) {
      when (it.status) {
        Resource.Status.LOADING -> showLoading()
        Resource.Status.SUCCESS -> showData()
        Resource.Status.EMPTY_DATA -> showEmptyData(it.message.toString())
        Resource.Status.ERROR -> showWarning(it.message.toString())
      }
    }
  }

  private fun showLoading() {
    binding.run {
      wrapperContent.gone()
      wrapperWarning.gone()
      progressBar.visible()
    }
  }

  private fun showData() {
    binding.run {
      wrapperContent.visible()
      wrapperWarning.gone()
      progressBar.gone()
    }
  }

  private fun showEmptyData(message: String) {
    binding.run {
      wrapperContent.gone()
      viewWarning.btnWarning.gone()
      wrapperWarning.visible()
      progressBar.gone()

      viewWarning.ivWarning.loadImage(R.drawable.img_empty)
      viewWarning.tvWarning.text = message
    }
  }

  private fun showWarning(message: String) {
    binding.run {
      wrapperContent.gone()
      viewWarning.btnWarning.visible()
      wrapperWarning.visible()
      progressBar.gone()

      viewWarning.ivWarning.loadImage(R.drawable.img_error)
      viewWarning.tvWarning.text = message
    }
  }

  @Subscribe
  fun onReceiveEventBus(event: FavoriteEvent) {
    if (!event.changes) return
    if (!movieViewModel.favoritePage) return

    movieViewModel.refresh()
  }

  override fun onDestroy() {
    if (EventBus.getDefault().isRegistered(this))
      EventBus.getDefault().unregister(this)
    super.onDestroy()
  }

  override fun onDestroyView() {
    binding.rvMovie.adapter = null
    super.onDestroyView()
  }
}