package rs.hc.android.ui.base

import androidx.lifecycle.LiveData

interface IBaseViewModel {

    val progressBarVisibilityObservable: LiveData<Boolean>
    val errorObservable: LiveData<String>
}