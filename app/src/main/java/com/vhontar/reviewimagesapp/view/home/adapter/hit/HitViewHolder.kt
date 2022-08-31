package com.vhontar.reviewimagesapp.view.home.adapter.hit

import android.provider.Settings.System.getConfiguration
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.databinding.RecyclerviewHitItemBinding
import com.vhontar.reviewimagesapp.view.common.MarginItemDecoration
import com.vhontar.reviewimagesapp.view.home.adapter.tag.TagAdapter

class HitViewHolder private constructor(
    private val binding: RecyclerviewHitItemBinding,
    onItemClicked: (HitModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var localModel: HitModel? = null
    private val adapter: TagAdapter = TagAdapter()
    private val decoration = MarginItemDecoration(
        rightSpace = 20,
        rightSpaceWithoutLast = true
    )

    init {
        binding.clRoot.setOnClickListener {
            localModel?.let { onItemClicked.invoke(it) }
        }

//        binding.rvTags.adapter = adapter
//        binding.rvTags.addItemDecoration(decoration)
    }

    fun bind(model: HitModel?) {
        localModel = model
        binding.model = model

        model?.transformTagsToList()
//        if (!tags.isNullOrEmpty()) {
//            adapter.submitList(tags)
//        }

        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, onItemClicked: (HitModel) -> Unit): HitViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = RecyclerviewHitItemBinding.inflate(inflater, parent, false)
            return HitViewHolder(binding, onItemClicked)
        }
    }
}