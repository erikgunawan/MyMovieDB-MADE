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
import id.ergun.mymoviedb.util.autoCleared

/**
 * Created by alfacart on 21/10/20.
 */
@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var binding: MovieFragmentBinding by autoCleared()

    private val movieViewModel by viewModels<MovieViewModel>()

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

        val movieAdapter = MovieAdapter()
        movieAdapter.setMovies(MovieVR.transform(movieViewModel.getMovies()))

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}
