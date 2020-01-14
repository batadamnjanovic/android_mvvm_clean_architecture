package rs.hc.android.ui.commitdetails.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity__commit_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.hc.android.R
import rs.hc.android.extension.doSomethign
import rs.hc.android.ui.base.BaseActivity
import rs.hc.android.ui.commitdetails.datamodel.CommitsDataModel
import rs.hc.android.ui.commitdetails.viewmodel.CommitsViewModel
import rs.hc.android.ui.commitdetails.viewmodel.ICommitsViewModel

class CommitsActivity : BaseActivity() {

    private val commitsViewModel: ICommitsViewModel by viewModel<CommitsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__commit_details)

        intent.extras?.let {
            val owner = it.getString(EXTRAS_OWNER, "")
            val repoName = it.getString(EXTRAS_REPO_NAME, "")
            commitsViewModel.init(owner, repoName)
        }

        commitsViewModel.commitsObservable.observe(this, Observer {
            showCommits(it)
        })

        doSomethign()
    }

    private fun showCommits(commits: MutableList<CommitsDataModel>) {
        CommitsAdapter(recyclerView_commits, commits) {
            // TODO click listener
        }
    }

    override fun getViewModel(): ICommitsViewModel {
        return commitsViewModel
    }

    companion object {
        private const val EXTRAS_OWNER = "CommitsActivity::owner"
        private const val EXTRAS_REPO_NAME = "CommitsActivity::repo_name"

        fun start(context: Context, owner: String, repoName: String) {
            val intent = Intent(context, CommitsActivity::class.java)
            intent.putExtra(EXTRAS_OWNER, owner)
            intent.putExtra(EXTRAS_REPO_NAME, repoName)
            context.startActivity(intent)
        }
    }
}
