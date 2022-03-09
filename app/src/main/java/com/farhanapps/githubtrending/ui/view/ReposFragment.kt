package com.farhanapps.githubtrending.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.farhanapps.githubtrending.R
import com.farhanapps.githubtrending.databinding.ReposFragmentBinding
import com.farhanapps.githubtrending.ui.viewmodel.ReposViewModel

class ReposFragment : Fragment(R.layout.repos_fragment) {

    private lateinit var viewModel: ReposViewModel

    private var _binding: ReposFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ReposFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ReposViewModel::class.java]

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}