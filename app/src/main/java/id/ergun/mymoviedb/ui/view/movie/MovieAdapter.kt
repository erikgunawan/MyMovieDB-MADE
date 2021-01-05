package id.ergun.mymoviedb.ui.view.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.ergun.mymoviedb.BuildConfig
import id.ergun.mymoviedb.databinding.MovieItemsBinding
import id.ergun.mymoviedb.ui.view.movie.detail.MovieDetailActivity
import id.ergun.mymoviedb.util.loadImage

/**
 * Created by alfacart on 21/10/20.
 */
class MovieAdapter : PagedListAdapter<MovieVR, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieVR>() {
            override fun areItemsTheSame(oldItem: MovieVR, newItem: MovieVR): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieVR, newItem: MovieVR): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            MovieItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class MovieViewHolder(val binding: MovieItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieVR) {
            with(itemView) {
                binding.tvTitle.text = movie.title
                binding.viewRating.tvRating.text = movie.voteAverage.toString()
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