package com.example.naviinterview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.entity.PullRequest
import javax.inject.Inject

class PullRequestListAdapter @Inject constructor(): RecyclerView.Adapter<PullRequestViewHolder>() {
    var pullRequestList: List<PullRequest>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return PullRequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val pullRequest = pullRequestList?.get(position)
        pullRequest?.let {
            holder.createdDate.text = it.createdAt
            holder.closedDate.text = it.closedAt
            holder.userName.text = it.user.login
            holder.title.text = it.title
            Glide.with(holder.imageView.context)
                    .load(it.user.avatarUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.imageView);
        }
    }

    override fun getItemCount(): Int {
        return pullRequestList?.size ?: 0
    }
}