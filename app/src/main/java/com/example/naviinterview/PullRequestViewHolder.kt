package com.example.naviinterview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val createdDate: TextView = itemView.findViewById(R.id.created_date)
    val closedDate: TextView = itemView.findViewById(R.id.closed_date)
    val userName: TextView = itemView.findViewById(R.id.user_name)
    val imageView: ImageView = itemView.findViewById(R.id.image_view)
}