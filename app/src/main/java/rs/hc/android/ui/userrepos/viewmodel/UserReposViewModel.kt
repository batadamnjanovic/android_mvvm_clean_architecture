package rs.hc.android.ui.userrepos.viewmodel

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn
import rs.hc.android.domain.UserDetailsUseCase
import rs.hc.android.domain.model.user.UserRepo
import rs.hc.android.mapper.DataModelMapper
import rs.hc.android.ui.base.BaseViewModel
import rs.hc.android.ui.userrepos.datamodel.UserRepoDataModel
import kotlin.coroutines.CoroutineContext

class UserReposViewModel(
    private val userReposUseCase: UserDetailsUseCase,
    private val mapper: DataModelMapper
) : BaseViewModel(), IUserReposViewModel, CoroutineScope, AnkoLogger {

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Default

    override val userReposDataModelObservable = MutableLiveData<MutableList<UserRepoDataModel>>()
    override val repoClickObservable = MutableLiveData<Pair<String, String>>()

    private var reposLocal: MutableList<UserRepo> = mutableListOf()
    private var username = ""

    override fun init(username: String) {
        this.username = username
        if (username.isEmpty()) return

        showProgressBar()

        launch {
            val reposResult = userReposUseCase.getUserRepos(username)

            withContext(Dispatchers.Main) {
                reposResult.onSuccess { repos ->
                    repos?.let {
                        reposLocal = it.toMutableList()
                        val reposDataModel = mapper.mapToUserReposDataModel(it)
                        userReposDataModelObservable.value = reposDataModel
                    }

                    progressBarVisibilityObservable.value = false
                }.onFailure {
                    warn { it.localizedMessage }
                    errorObservable.value = it.localizedMessage
                    progressBarVisibilityObservable.value = false
                }
            }
        }
    }

    override fun onRepoClick(repoDataModel: UserRepoDataModel) {
        val repo = reposLocal.find { it.id == repoDataModel.id }

        repo?.let {
            repoClickObservable.value = Pair(it.owner.login, it.name)
        }
    }
}
