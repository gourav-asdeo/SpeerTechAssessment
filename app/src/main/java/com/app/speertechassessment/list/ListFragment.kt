package com.app.speertechassessment.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.speertechassessment.R
import com.app.speertechassessment.databinding.FragmentListBinding
import com.app.speertechassessment.databinding.FragmentSearchBinding
import com.app.speertechassessment.network.ApiManager

class ListFragment : Fragment(R.layout.fragment_list) {

    val args: ListFragmentArgs by navArgs()
    private val listViewModel: ListViewModel by viewModels { ListViewModelFactory(ApiManager.getApiService()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (listViewModel.userList.value?.isEmpty() == true){
            if (args.followers) {
                listViewModel.getFollowers(args.userName)
            } else {
                listViewModel.getFollowing(args.userName)
            }
        }

        val adapter = ListAdapter{
            listViewModel.getUser(it)
        }

        FragmentListBinding.bind(view).apply {
            userListView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            userListView.adapter = adapter

        }

        listViewModel.user.observe(viewLifecycleOwner){
            if (it!=null){
                listViewModel.clearUser()
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(it)
                findNavController().navigate(action)
            }
        }

        listViewModel.userList.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                adapter.submitList(it)
            }
        }
    }

}