package com.vhontar.reviewimagesapp.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.vhontar.reviewimagesapp.R
import com.vhontar.reviewimagesapp.databinding.FragmentHomeBinding
import com.vhontar.reviewimagesapp.view.common.MarginItemDecoration
import com.vhontar.reviewimagesapp.view.home.adapter.hit.HitAdapter
import com.vhontar.reviewimagesapp.view.home.adapter.hit.HitLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private var adapter: HitAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        binding.apply {
            val decoration = MarginItemDecoration(
                bottomSpace = resources.getDimension(R.dimen.hit_margin).toInt()
            )

            adapter = HitAdapter(onItemClicked = {})

            val loadStateAdapter = HitLoadStateAdapter { adapter?.retry() }
            adapter?.addLoadStateListener {
                viewModel.setLoading(it.source.refresh is LoadState.Loading)
            }

            rvHits.adapter = adapter?.withLoadStateFooter(loadStateAdapter)
            rvHits.addItemDecoration(decoration)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.hits.collectLatest {
                adapter?.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}