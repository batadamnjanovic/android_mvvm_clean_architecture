package rs.hc.android.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Base for all view models.
 */
abstract class BaseViewModel : ViewModel(), IBaseViewModel {

    override val progressBarVisibilityObservable: MutableLiveData<Boolean> = MutableLiveData()
    override val errorObservable: MutableLiveData<String> = MutableLiveData()

    fun showProgressBar(){
        progressBarVisibilityObservable.value = true
    }

    fun hideProgressBar(){
        progressBarVisibilityObservable.value = false
    }
}