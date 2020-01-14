package rs.hc.android.ui.commitdetails.viewmodel

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn
import rs.hc.android.domain.RepoUseCase
import rs.hc.android.mapper.DataModelMapper
import rs.hc.android.ui.base.BaseViewModel
import rs.hc.android.ui.commitdetails.datamodel.CommitsDataModel
import kotlin.coroutines.CoroutineContext

class CommitsViewModel(
    private val repoUseCase: RepoUseCase,
    private val mapper: DataModelMapper
) : BaseViewModel(), ICommitsViewModel, CoroutineScope, AnkoLogger {

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Default

    override val commitsObservable = MutableLiveData<MutableList<CommitsDataModel>>()

    override fun init(owner: String, repoName: String) {
        progressBarVisibilityObservable.value = true

        launch {
            val commitsResult = repoUseCase.getCommits(owner, repoName)

            withContext(Dispatchers.Main) {
                commitsResult.onSuccess { commits ->
                    commits?.let {
                        val commitsDataModel = mapper.mapToCommitsDataModel(it)
                        commitsObservable.value = commitsDataModel
                    }
                    progressBarVisibilityObservable.value = false
                }.onFailure {
                    warn { it.localizedMessage }
                    progressBarVisibilityObservable.value = false
                    errorObservable.value = it.localizedMessage
                }
            }
        }
    }

}
