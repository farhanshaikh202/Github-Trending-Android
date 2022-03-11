package com.farhanapps.githubtrending.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.farhanapps.githubtrending.R
import com.farhanapps.githubtrending.data.Constants
import com.farhanapps.githubtrending.data.model.RepoModel
import com.farhanapps.githubtrending.databinding.ReposFragmentBinding
import com.farhanapps.githubtrending.ui.adapter.ReposRvAdapter
import com.farhanapps.githubtrending.ui.viewmodel.ReposViewModel
import com.farhanapps.githubtrending.utils.Status.*
import com.farhanapps.githubtrending.utils.extensions.snackbar
import com.farhanapps.githubtrending.utils.interfaces.OnItemClickListener

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
        viewModel = ViewModelProvider(requireActivity())[ReposViewModel::class.java]
        binding.reposeRv.adapter = reposRvAdapter
        reposRvAdapter.setViewModel(viewModel)
        reposRvAdapter.setOnItemClickListener(object : OnItemClickListener<RepoModel> {
            override fun onItemClick(position: Int, item: RepoModel) {
                viewModel.selectOrRemoveRepo(item)
                sendEvent(requireContext())
                reposRvAdapter.notifyItemChanged(position)
            }
        })
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

    private fun sendEvent(context: Context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(Intent(Constants.EVENT_SELECTION))
    }

}