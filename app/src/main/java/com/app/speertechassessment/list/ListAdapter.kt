package com.app.speertechassessment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.speertechassessment.databinding.ListItemBinding
import com.app.speertechassessment.models.User
import com.bumptech.glide.Glide

class ListAdapter(val goToUserDetail:(id:String)->Unit): ListAdapter<User, com.app.speertechassessment.list.ListAdapter.ListViewHolder>(object :
    DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem==newItem
    }

}) {

    class ListViewHolder(val binding:ListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.apply {
            Glide.with(this.root.context).load(data.avatar).into(avatar)
            handle.text = data.username
        }
        holder.binding.root.setOnClickListener {
            goToUserDetail(data.username)
        }

    }
}