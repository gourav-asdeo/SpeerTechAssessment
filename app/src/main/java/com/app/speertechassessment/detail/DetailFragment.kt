package com.app.speertechassessment.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.speertechassessment.R
import com.app.speertechassessment.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide

class DetailFragment : Fragment(R.layout.fragment_detail) {

    val args : DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentDetailBinding.bind(view).apply {
            Glide.with(this@DetailFragment).load(args.user.avatar).into(avatar)
            name.text = args.user.name ?: ""
            handle.text = args.user.username
            desc.text = args.user.description ?: ""
            followers.text = "${args.user.follower} Followers"
            following.text = "${args.user.following} Following"

            if (args.user.name.isNullOrEmpty()) name.isVisible = false
            if (args.user.description.isNullOrEmpty()) desc.isVisible = false

            followers.setOnClickListener {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListFragment(args.user.username,true))
            }

            following.setOnClickListener {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListFragment(args.user.username,false))
            }
        }
    }

}