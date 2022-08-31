package com.vhontar.reviewimagesapp.view.home

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
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
                it.source.refresh
                it.source.append
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

            etSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
                override fun onEditorAction(p0: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        val value = p0?.text
                        if (value.isNullOrEmpty())
                            return false

                        binding.rvHits.smoothScrollToPosition(0)
                        viewModel.generateHitsRequestModel(value.toString())
                        adapter?.refresh()
                        return true
                    }

                    return false
                }
            })

            ivClose.setOnClickListener { viewModel.clear() }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.fetchHits().collect {
                adapter?.submitData(it)
            }
        }
    }

    override fun refresh() {
        adapter?.refresh()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}