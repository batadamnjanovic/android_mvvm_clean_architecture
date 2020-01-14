package rs.hc.android.ui.userrepos.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity__user_repos.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.hc.android.R
import rs.hc.android.ui.base.BaseActivity
import rs.hc.android.ui.commitdetails.view.CommitsActivity
import rs.hc.android.ui.userrepos.datamodel.UserRepoDataModel
import rs.hc.android.ui.userrepos.viewmodel.IUserReposViewModel
import rs.hc.android.ui.userrepos.viewmodel.UserReposViewModel

class UserReposActivity : BaseActivity() {

    private val userDetailsViewModel: IUserReposViewModel by viewModel<UserReposViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__user_repos)

        intent.extras?.let {
            userDetailsViewModel.init(it.getString(EXTRAS_USERNAME, ""))
        }

        userDetailsViewModel.userReposDataModelObservable.observe(this, Observer {
            showRepos(it)
        })

        userDetailsViewModel.repoClickObservable.observe(this, Observer {
            CommitsActivity.start(this, it.first, it.second)
        })
    }

    private fun showRepos(repos: MutableList<UserRepoDataModel>) {
        ReposAdapter(recyclerView_repos, repos) {
            userDetailsViewModel.onRepoClick(it)
        }
    }

    override fun getViewModel(): IUserReposViewModel {
        return userDetailsViewModel
    }

    companion object {
        private const val EXTRAS_USERNAME = "UserReposActivity::username"

        fun start(context: Context, username: String) {
            val intent = Intent(context, UserReposActivity::class.java)
            intent.putExtra(EXTRAS_USERNAME, username)
            context.startActivity(intent)
        }
    }
}
