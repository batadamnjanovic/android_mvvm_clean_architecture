package rs.hc.android.ui.commitdetails.view

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view__commit_layout_item.view.*
import rs.hc.android.R
import rs.hc.android.extension.inflate
import rs.hc.android.helper.DateHelper
import rs.hc.android.ui.commitdetails.datamodel.CommitsDataModel

class CommitsAdapter(
    recyclerView: RecyclerView,
    private val messages: MutableList<CommitsDataModel>,
    private val onRepoClick: (CommitsDataModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        val layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RepoViewHolder(
            parent.inflate(R.layout.recycler_view__commit_layout_item),
            onRepoClick
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RepoViewHolder).setData(messages[position], position)
    }

    override fun getItemViewType(position: Int) = 0

    override fun getItemCount() = messages.size

    class RepoViewHolder(itemView: View, private val onRepoClick: (CommitsDataModel) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        fun setData(commitDataModel: CommitsDataModel, position: Int) {

            // Commit message
            itemView.textView_commitMessage.text = itemView.context.getString(R.string.message, commitDataModel.message)

            // Author
            itemView.textView_authorName.text = itemView.context.getString(R.string.author, commitDataModel.authorName)
            itemView.textView_authorEmail.text = itemView.context.getString(R.string.email, commitDataModel.authorEmail)
            val authorDatePretty = DateHelper.getReadableDate(commitDataModel.authorDate)
            itemView.textView_authorDate.text = itemView.context.getString(R.string.created, authorDatePretty)

            // Committer
            itemView.textView_committerName.text = itemView.context.getString(R.string.committer, commitDataModel.committerName)
            itemView.textView_committerEmail.text = itemView.context.getString(R.string.email, commitDataModel.committerEmail)
            val committerDatePretty = DateHelper.getReadableDate(commitDataModel.committerDate)
            itemView.textView_committerDate.text = itemView.context.getString(R.string.created, committerDatePretty)

            itemView.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    if (position % 2 == 0) R.color.white else R.color.grey
                )
            )
        }
    }

}
