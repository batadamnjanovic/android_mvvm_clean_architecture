package rs.hc.android.ui.userdetails.view

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity__user_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.hc.android.R
import rs.hc.android.extension.loadImage
import rs.hc.android.ui.base.BaseActivity
import rs.hc.android.ui.userdetails.datamodel.UserDetailsDataModel
import rs.hc.android.ui.userdetails.viewmodel.IUserDetailsViewModel
import rs.hc.android.ui.userdetails.viewmodel.UserDetailsViewModel
import rs.hc.android.ui.userrepos.view.UserReposActivity

class UserDetailsActivity : BaseActivity() {

    private val userDetailsViewModel: IUserDetailsViewModel by viewModel<UserDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__user_details)

        userDetailsViewModel.userDetailsDataModelObservable.observe(this, Observer {
            showUserDetails(it)
        })

        userDetailsViewModel.reposButtonObservable.observe(this, Observer {
            UserReposActivity.start(this, userDetailsViewModel.username)
        })
    }

    private fun showUserDetails(userDetailsDataModel: UserDetailsDataModel) {
        imageView_avatar.loadImage(userDetailsDataModel.avatar)

        textView_name.text = getString(R.string.user, userDetailsDataModel.name)
        textView_company.text = getString(R.string.company, userDetailsDataModel.company)

        button_repos.setOnClickListener {
            userDetailsViewModel.onReposButtonClick()
        }
    }

    override fun getViewModel(): IUserDetailsViewModel {
        return userDetailsViewModel
    }
}
