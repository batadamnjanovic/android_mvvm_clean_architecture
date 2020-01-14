package rs.hc.android.ui.userdetails.viewmodel

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn
import rs.hc.android.domain.UserDetailsUseCase
import rs.hc.android.mapper.DataModelMapper
import rs.hc.android.ui.base.BaseViewModel
import rs.hc.android.ui.userdetails.datamodel.UserDetailsDataModel
import kotlin.coroutines.CoroutineContext

class UserDetailsViewModel(
    private val userDetailsUseCase: UserDetailsUseCase,
    private val dataModelMapper: DataModelMapper
) : BaseViewModel(), IUserDetailsViewModel, CoroutineScope, AnkoLogger {

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Default

    override val userDetailsDataModelObservable = MutableLiveData<UserDetailsDataModel>()
    override val reposButtonObservable = MutableLiveData<String>()

    //TODO
    override var username: String = "octocat"

    init {
        progressBarVisibilityObservable.value = true

        launch {
            val userDetails = userDetailsUseCase.getUserDetails(username)

            withContext(Dispatchers.Main) {
                userDetails.onSuccess { userDetails ->
                    // Show UI
                    userDetails?.let {
                        val userDetailsDataModel = dataModelMapper.mapToUserDetailsDataModel(it)
                        userDetailsDataModelObservable.value = userDetailsDataModel
                    }
                    progressBarVisibilityObservable.value = false
                }.onFailure {
                    warn { it.message }
                    errorObservable.value = it.localizedMessage
                    progressBarVisibilityObservable.value = false
                }
            }
        }
    }

    override fun onReposButtonClick() {
        reposButtonObservable.value = username
    }
}
