package rs.hc.android.ui.userrepos.viewmodel

import androidx.lifecycle.LiveData
import rs.hc.android.ui.base.IBaseViewModel
import rs.hc.android.ui.userrepos.datamodel.UserRepoDataModel

interface IUserReposViewModel : IBaseViewModel {

    val userReposDataModelObservable: LiveData<MutableList<UserRepoDataModel>>
    val repoClickObservable: LiveData<Pair<String, String>>
    fun init(username: String)
    fun onRepoClick(repoDataModel: UserRepoDataModel)
}