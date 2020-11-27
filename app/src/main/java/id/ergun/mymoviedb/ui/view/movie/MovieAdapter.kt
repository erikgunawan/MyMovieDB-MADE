package id.ergun.mymoviedb.ui.view.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ergun.mymoviedb.BuildConfig
import id.ergun.mymoviedb.databinding.MovieItemsBinding
import id.ergun.mymoviedb.ui.view.movie.detail.MovieDetailActivity
import id.ergun.mymoviedb.util.loadImage

/**
 * Created by alfacart on 21/10/20.
 */
class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<MovieVR>()

    fun setMovies(movies: List<MovieVR>?) {
        if (movies.isNullOrEmpty()) return
        listMovies.clear()
        listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size


    class MovieViewHolder(private val binding: MovieItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieVR) {
            with(itemView) {
                binding.tvTitle.text = movie.title
                binding.tvRating.text = movie.voteAverage.toString()
                binding.tvTagLine.text = movie.tagLine
                setOnClickListener {
                    val intent = MovieDetailActivity.newIntent(context, MovieVR.toModel(movie))
                    context.startActivity(intent)
                }
                binding.ivPoster.loadImage(BuildConfig.IMAGE_URL + movie.posterPath)
            }
        }
    }
}