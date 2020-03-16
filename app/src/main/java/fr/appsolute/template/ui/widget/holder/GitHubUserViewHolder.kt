package fr.appsolute.template.ui.widget.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.appsolute.template.R
import fr.appsolute.template.data.model.GitHubUser
import kotlinx.android.synthetic.main.holder_githubuser.view.*

/**
 * SAM (Single Abstract Method) to listen a click.
 *
 * This callback contains the view clicked, and the character attached to the view
 */
typealias OnGitHubUserClickListener = (view: View, gitHubUser: GitHubUser) -> Unit

class GitHubUserViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: GitHubUser, onClick: OnGitHubUserClickListener) {
        itemView.apply {
            this.setOnClickListener { onClick(it, model) }
            this.holder_githubuser_full_name.text = model.login
            this.holder_githubuser_location.text = model.url
            Glide.with(this)
                .load(model.avatar_url)
                .into(this.holder_githubuser_avatar)
        }
    }

    companion object {
        /**
         * Create a new Instance of [GitHubUserViewHolder]
         */
        fun create(parent: ViewGroup): GitHubUserViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.holder_githubuser,
                parent,
                false
            )
            return GitHubUserViewHolder(view)
        }
    }

}