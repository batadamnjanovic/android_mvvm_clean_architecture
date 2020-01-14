package rs.hc.android.ui.commitdetails.viewmodel

import androidx.lifecycle.LiveData
import rs.hc.android.ui.base.IBaseViewModel
import rs.hc.android.ui.commitdetails.datamodel.CommitsDataModel

interface ICommitsViewModel : IBaseViewModel {

    val commitsObservable: LiveData<MutableList<CommitsDataModel>>
    fun init(owner: String, repoName: String)
}