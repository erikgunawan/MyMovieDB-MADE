package id.ergun.mymoviedb.core.view.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.ergun.mymoviedb.core.BuildConfig
import id.ergun.mymoviedb.core.databinding.MovieItemsBinding
import id.ergun.mymoviedb.core.domain.model.Movie
import id.ergun.mymoviedb.core.util.loadImage

/**
 * Created by alfacart on 21/10/20.
 */
class MovieAdapter : PagedListAdapter<MovieVR, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

  var itemClickListener: ((movie: Movie) -> Unit)? = null

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

  inner class MovieViewHolder(val binding: MovieItemsBinding) :
      RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieVR) {
      binding.tvTitle.text = movie.title
      binding.viewRating.tvRating.text = movie.voteAverage.toString()
      binding.tvOverview.text = movie.overview
      binding.ivPoster.loadImage(BuildConfig.IMAGE_URL + movie.posterPath)
      itemView.setOnClickListener {
        itemClickListener?.invoke(MovieVR.toModel(movie))
      }
    }
  }
}