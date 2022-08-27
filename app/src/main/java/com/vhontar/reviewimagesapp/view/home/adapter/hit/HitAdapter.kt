package com.vhontar.reviewimagesapp.view.home.adapter.hit

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.vhontar.reviewimagesapp.business.domain.models.HitModel

class HitAdapter(private val onItemClicked: (HitModel) -> Unit): PagingDataAdapter<HitModel, HitViewHolder>(
    HitDiffUtilCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitViewHolder =
        HitViewHolder.from(parent, onItemClicked)
    override fun onBindViewHolder(holder: HitViewHolder, position: Int) = holder.bind(getItem(position))
}

class HitDiffUtilCallback : DiffUtil.ItemCallback<HitModel>() {
    override fun areItemsTheSame(oldItem: HitModel, newItem: HitModel): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: HitModel, newItem: HitModel): Boolean = oldItem == newItem
}