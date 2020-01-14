package rs.hc.android.ui.userdetails.viewmodel

import androidx.lifecycle.LiveData
import rs.hc.android.ui.base.IBaseViewModel
import rs.hc.android.ui.userdetails.datamodel.UserDetailsDataModel

interface IUserDetailsViewModel : IBaseViewModel{

    var username: String
    val userDetailsDataModelObservable: LiveData<UserDetailsDataModel>
    val reposButtonObservable: LiveData<String>
    fun onReposButtonClick()
}