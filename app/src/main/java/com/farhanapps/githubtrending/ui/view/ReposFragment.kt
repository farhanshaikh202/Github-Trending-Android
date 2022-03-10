package com.farhanapps.githubtrending.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.farhanapps.githubtrending.R
import com.farhanapps.githubtrending.databinding.ReposFragmentBinding
import com.farhanapps.githubtrending.ui.adapter.ReposRvAdapter
import com.farhanapps.githubtrending.ui.viewmodel.ReposViewModel
import com.farhanapps.githubtrending.utils.Status.*
import com.farhanapps.githubtrending.utils.extensions.snackbar

class ReposFragment : Fragment(R.layout.repos_fragment) {

    private lateinit var viewModel: ReposViewModel

    private var _binding: ReposFragmentBinding? = null
    private val binding get() = _binding!!
    private val reposRvAdapter = ReposRvAdapter()

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
        binding.reposeRv.adapter = reposRvAdapter
        binding.reposSwipeLayout.setOnRefreshListener {
            viewModel.loadTrendingRepos()
        }

        viewModel.getRepoList().observe(viewLifecycleOwner) {
            when (it.status) {
                SUCCESS -> {
                    binding.reposSwipeLayout.isRefreshing = false
                    if (it.data != null)
                        reposRvAdapter.setData(it.data)
                    else
                        binding.reposSwipeLayout.snackbar("No data")

                }
                ERROR -> {
                    binding.reposSwipeLayout.snackbar(it.message.toString())
                    binding.reposSwipeLayout.isRefreshing = false
                }
                LOADING -> binding.reposSwipeLayout.isRefreshing = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}