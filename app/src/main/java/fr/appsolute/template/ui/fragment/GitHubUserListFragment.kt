package fr.appsolute.template.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import fr.appsolute.template.R
import fr.appsolute.template.data.model.GitHubUser
import fr.appsolute.template.ui.activity.MainActivity
import fr.appsolute.template.ui.adapter.GitHubUserAdapter
import fr.appsolute.template.ui.viewmodel.GitHubUserViewModel
import fr.appsolute.template.ui.widget.holder.OnGitHubUserClickListener
import kotlinx.android.synthetic.main.fragment_githubuser_list.view.*
import java.lang.IllegalStateException

class GitHubUserListFragment: Fragment(), OnGitHubUserClickListener {

    private lateinit var gitHubUserViewModel: GitHubUserViewModel
    private lateinit var gitHubUserAdapter: GitHubUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            gitHubUserViewModel = ViewModelProvider(this, GitHubUserViewModel).get()
        } ?: throw IllegalStateException("Invalid Activty")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_githubuser_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.supportActionBar?.apply {
            this.setTitle(R.string.app_name)
            this.setDisplayHomeAsUpEnabled(false)
        }
        // We need to inject the OnCharacterClickListener in the constructor of the adapter
        gitHubUserAdapter = GitHubUserAdapter(this)
        view.githubuser_list_recycler_view.apply {
            adapter = gitHubUserAdapter
        }
        gitHubUserViewModel.getAllGitHubUser {
            gitHubUserAdapter.submitList(it)
        }
    }

    // Implementation of OnUserClickListener
    override fun invoke(view: View, gitHubUser: GitHubUser) {
        //
    }
}