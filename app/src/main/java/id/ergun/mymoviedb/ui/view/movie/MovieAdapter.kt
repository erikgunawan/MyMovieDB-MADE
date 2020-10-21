package id.ergun.mymoviedb.ui.view.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.ui.view.movie.detail.MovieDetailActivity
import kotlinx.android.synthetic.main.movie_items.view.*

/**
 * Created by alfacart on 21/10/20.
 */
class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<MovieVR>()

    fun setMovies(movies: List<MovieVR>?) {
        if (movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_items, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val course = listMovies[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listMovies.size


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(course: MovieVR) {
            with(itemView) {
                tv_title.text = course.title
                tv_description.text = course.overview
                tv_date.text = course.overview
                setOnClickListener {
                    val intent = MovieDetailActivity.newIntent(context, MovieVR.toModel(course))
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load(course.image)
//                    .apply(
//                        RequestOptions.placeholderOf(R.drawable.ic_loading)
//                            .error(R.drawable.ic_error))
                    .into(iv_poster)
            }
        }
    }
}