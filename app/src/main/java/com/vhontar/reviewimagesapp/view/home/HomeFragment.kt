package com.vhontar.reviewimagesapp.view.home

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.vhontar.reviewimagesapp.R
import com.vhontar.reviewimagesapp.databinding.FragmentHomeBinding
import com.vhontar.reviewimagesapp.view.common.MarginItemDecoration
import com.vhontar.reviewimagesapp.view.common.base.BaseFragment
import com.vhontar.reviewimagesapp.view.home.adapter.hit.HitAdapter
import com.vhontar.reviewimagesapp.view.home.adapter.hit.HitLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment: BaseFragment() {
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

            adapter = HitAdapter(onItemClicked = {
                val action = HomeFragmentDirections.actionHomeFragmentToHitDetailsFragment(it.id)
                findNavController().navigate(action)
            })

            val loadStateAdapter = HitLoadStateAdapter { adapter?.retry() }
            adapter?.addLoadStateListener {
                viewModel.setLoading(it.source.refresh is LoadState.Loading)
            }
            rvHits.itemAnimator = null // remove bluming effect / animation
            rvHits.adapter = adapter?.withLoadStateFooter(loadStateAdapter)
            rvHits.scrollToPosition(0)

            if (resources.configuration.orientation == ORIENTATION_LANDSCAPE) {
                rvHits.layoutManager = GridLayoutManager(requireContext(), 2)
                val decoration = MarginItemDecoration(
                    bottomSpace = resources.getDimension(R.dimen.hit_margin).toInt(),
                    leftSpace = resources.getDimension(R.dimen.hit_margin).toInt(),
                    leftSpaceOnlyOdd = true
                )

                rvHits.addItemDecoration(decoration)
            } else {
                rvHits.layoutManager = LinearLayoutManager(requireContext())
                val decoration = MarginItemDecoration(
                    bottomSpace = resources.getDimension(R.dimen.hit_margin).toInt()
                )

                rvHits.addItemDecoration(decoration)
            }

            tvSearchView.setOnClickListener { tvSearchView.requestFocus() }
            tvSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    if (p0.isNullOrEmpty())
                        return true

                    binding.rvHits.smoothScrollToPosition(0)
                    viewModel.generateHitsRequestModel(p0)
                    adapter?.refresh()
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return true
                }
            })
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // unfortunately search view doesn't have `query` as data binding property
        viewModel.lastQuery.observe(viewLifecycleOwner) {
            binding.tvSearchView.apply {
                setQuery(it, false)
                isIconified = false
                clearFocus()
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.fetchHits().collectLatest {
                adapter?.submitData(it)
            }
        }
    }

    override fun retry() {
        adapter?.retry()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}