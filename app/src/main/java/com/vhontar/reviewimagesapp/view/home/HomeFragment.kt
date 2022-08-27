package com.vhontar.reviewimagesapp.view.home

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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

            adapter = HitAdapter(onItemClicked = {
                val bundle = bundleOf(
                    "hitId" to it
                )
                findNavController().navigate(R.id.action_homeFragment_to_hitDetailsFragment, bundle)
            })

            val loadStateAdapter = HitLoadStateAdapter { adapter?.retry() }
            adapter?.addLoadStateListener {
                viewModel.setLoading(it.source.refresh is LoadState.Loading)
            }
            rvHits.adapter = adapter?.withLoadStateFooter(loadStateAdapter)

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

            tvSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    if (p0.isNullOrEmpty())
                        return true

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