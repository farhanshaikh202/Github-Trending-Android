package com.farhanapps.githubtrending.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.farhanapps.githubtrending.R
import com.farhanapps.githubtrending.databinding.MainFragmentBinding
import com.farhanapps.githubtrending.ui.viewmodel.ReposViewModel
import com.farhanapps.githubtrending.utils.Status

class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var viewModel: ReposViewModel

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ReposViewModel::class.java]
        binding.mainTabLayout.getTabAt(2)?.select()
        binding.mainSearchEt.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            if (text.isNullOrBlank()) {
                viewModel.clearFilter()
                viewModel.refreshData()
                return@addTextChangedListener
            }
            viewModel.filter(text.toString())
        })
        viewModel.getRepoList().observe(this) {
            if (it.status == Status.SUCCESS) {
                binding.mainSearchTil.isEnabled = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}