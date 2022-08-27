package com.vhontar.reviewimagesapp.view.home.adapter.hit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.databinding.RecyclerviewHitItemBinding
import com.vhontar.reviewimagesapp.view.home.adapter.tag.TagAdapter

class HitViewHolder private constructor(
    private val binding: RecyclerviewHitItemBinding,
    onItemClicked: (HitModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var localModel: HitModel? = null
    private val adapter: TagAdapter = TagAdapter()

    init {
        binding.root.setOnClickListener {
            localModel?.let { onItemClicked.invoke(it) }
        }

        val layoutManager = FlexboxLayoutManager(binding.root.context)
        layoutManager.flexDirection = FlexDirection.COLUMN
        layoutManager.justifyContent = JustifyContent.FLEX_END
        binding.rvTags.layoutManager = layoutManager
        binding.rvTags.adapter = adapter
    }

    fun bind(model: HitModel?) {
        localModel = model
        binding.model = model

        val tags = model?.transformTagsToList()
        if (tags.isNullOrEmpty()) {
            adapter.submitList(tags)
        }

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