package com.vhontar.reviewimagesapp.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.vhontar.reviewimagesapp.databinding.FragmentHitDetailsBinding
import com.vhontar.reviewimagesapp.view.common.MarginItemDecoration
import com.vhontar.reviewimagesapp.view.common.base.BaseFragment
import com.vhontar.reviewimagesapp.view.home.adapter.tag.TagAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HitDetailsFragment: BaseFragment() {
    private var _binding: FragmentHitDetailsBinding? = null
    private val binding: FragmentHitDetailsBinding
        get() = _binding!!
    private val viewModel: HitDetailsViewModel by viewModels()

    private val adapter = TagAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHitDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        binding.apply {
            val decoration = MarginItemDecoration(
                rightSpace = 20,
                rightSpaceWithoutLast = true
            )
            rvTags.adapter = adapter
            rvTags.addItemDecoration(decoration)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner) {
            adapter.submitList(it.transformTagsToList())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun refresh() {
        viewModel.loadHit()
    }
}