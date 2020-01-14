package rs.hc.android.ui.userrepos.view

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view__repo_layout_item.view.*
import rs.hc.android.R
import rs.hc.android.extension.inflate
import rs.hc.android.ui.userrepos.datamodel.UserRepoDataModel

class ReposAdapter(
    recyclerView: RecyclerView,
    private val messages: MutableList<UserRepoDataModel>,
    private val onRepoClick: (UserRepoDataModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        val layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RepoViewHolder(parent.inflate(R.layout.recycler_view__repo_layout_item), onRepoClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RepoViewHolder).setData(messages[position], position)
    }

    override fun getItemViewType(position: Int) = 0

    override fun getItemCount() = messages.size

    class RepoViewHolder(itemView: View, private val onRepoClick: (UserRepoDataModel) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        fun setData(userRepoDataModel: UserRepoDataModel, position: Int) {
            itemView.textView_name.text = itemView.context.getString(R.string.repo, userRepoDataModel.name)
            itemView.textView_openIssuesCount.text = itemView.context.getString(
                R.string.open_issues,
                userRepoDataModel.openIssuesCount.toString()
            )

            itemView.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    if (position % 2 == 0) R.color.white else R.color.grey
                )
            )

            itemView.setOnClickListener {
                onRepoClick(userRepoDataModel)
            }
        }
    }

}
