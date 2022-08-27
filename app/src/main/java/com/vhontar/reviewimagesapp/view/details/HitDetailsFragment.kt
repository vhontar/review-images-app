package com.vhontar.reviewimagesapp.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vhontar.reviewimagesapp.databinding.FragmentHitDetailsBinding

class HitDetailsFragment: Fragment() {
    private var _binding: FragmentHitDetailsBinding? = null
    private val binding: FragmentHitDetailsBinding
        get() = _binding!!
    private val viewModel: HitDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHitDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}