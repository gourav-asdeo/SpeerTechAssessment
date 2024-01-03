package com.app.speertechassessment.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.speertechassessment.R
import com.app.speertechassessment.databinding.FragmentSearchBinding
import com.app.speertechassessment.network.ApiManager


class SearchFragment : Fragment(R.layout.fragment_search) {

    private val searchViewModel : SearchViewModel by viewModels { SearchViewModelFactory(ApiManager.getApiService()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSearchBinding.bind(view).apply {
            searchButton.setOnClickListener {
                if (searchInput.text.trim().isEmpty()){
                    return@setOnClickListener
                }
                notFound.isVisible = false
                searchViewModel.getUser(searchInput.text.trim().toString())
            }
        }

        searchViewModel.user.observe(viewLifecycleOwner){
            if (it!=null){
                binding.clearData()
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(it))
            }else if (binding.searchInput.text.isNotBlank()){
                binding.notFound.isVisible = true
            }
        }
    }

    private fun FragmentSearchBinding.clearData() {
        searchInput.text.clear()
        searchViewModel.clearUser()
    }

}