package fr.appsolute.template.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import fr.appsolute.template.data.model.GitHubUser
import fr.appsolute.template.ui.widget.holder.GitHubUserViewHolder
import fr.appsolute.template.ui.widget.holder.OnGitHubUserClickListener

class GitHubUserAdapter(
    private val onGitHubUserClickListener: OnGitHubUserClickListener
) : ListAdapter<GitHubUser, GitHubUserViewHolder>(Companion) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubUserViewHolder {
        return GitHubUserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GitHubUserViewHolder, position: Int) {
        getItem(position)?.run { holder.bind(this, onGitHubUserClickListener) }
    }

    companion object : DiffUtil.ItemCallback<GitHubUser>() {
        override fun areItemsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
            return oldItem == newItem
        }
    }
}