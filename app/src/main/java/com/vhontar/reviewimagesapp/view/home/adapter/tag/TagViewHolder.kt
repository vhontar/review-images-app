package com.vhontar.reviewimagesapp.view.home.adapter.tag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vhontar.reviewimagesapp.databinding.RecyclerviewTagItemBinding

class TagViewHolder private constructor(
    private val binding: RecyclerviewTagItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(tag: String) {
        binding.tag = tag
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): TagViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = RecyclerviewTagItemBinding.inflate(inflater, parent, false)
            return TagViewHolder(binding)
        }
    }
}