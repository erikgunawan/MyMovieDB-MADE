package id.ergun.mymoviedb.ui.view.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.ergun.mymoviedb.databinding.MovieFragmentBinding
import id.ergun.mymoviedb.ui.viewmodel.movie.MovieViewModel
import id.ergun.mymoviedb.util.Resource
import id.ergun.mymoviedb.util.autoCleared
import id.ergun.mymoviedb.util.gone
import id.ergun.mymoviedb.util.visible

/**
 * Created by alfacart on 21/10/20.
 */
@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var binding: MovieFragmentBinding by autoCleared()

    private val movieViewModel by viewModels<MovieViewModel>()

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity==null) return

        initView()
        initAction()
        getMovies()
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
        binding.viewWarning.btnWarning.setOnClickListener {
            getMovies()
        }
    }

    private fun getMovies() {
        showLoading()
        movieViewModel.getMovies().observe(requireActivity(), {
            if (it.status == Resource.Status.SUCCESS) {
                showData()
                movieAdapter.setMovies(it.data?.let { it1 -> MovieVR.transform(it1) })
                movieAdapter.notifyDataSetChanged()
                return@observe
            }

            if(it.status == Resource.Status.ERROR) {
                binding.viewWarning.tvWarning.text = it.message
            }
            showWarning()
        })

    }

    private fun showLoading() {
        binding.wrapperContent.gone()
        binding.wrapperWarning.gone()
        binding.progressBar.visible()
    }

    private fun showData() {
        binding.wrapperContent.visible()
        binding.wrapperWarning.gone()
        binding.progressBar.gone()
    }

    private fun showWarning() {
        binding.wrapperContent.gone()
        binding.wrapperWarning.visible()
        binding.progressBar.gone()
    }
}
