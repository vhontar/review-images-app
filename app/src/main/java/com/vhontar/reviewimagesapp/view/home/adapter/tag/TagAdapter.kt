package com.vhontar.reviewimagesapp.view.home.adapter.tag

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class TagAdapter: ListAdapter<String, TagViewHolder>(TagDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder = TagViewHolder.from(parent)
    override fun onBindViewHolder(holder: TagViewHolder, position: Int) = holder.bind(getItem(position))
}

class TagDiffUtilCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}

