package com.pinto.githubuser.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pinto.githubuser.data.models.GithubRepositoryModel
import com.pinto.githubuser.databinding.ItemGithubUserBinding


class UserAdapter() : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    inner class ViewHolder(private val itemBinding: ItemGithubUserBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(position: Int) {
            val item = differ.currentList[position]
            itemBinding.nameTextView.text = "Name: ${item.name}"
            itemBinding.descTextView.text = "Full Name:  ${item.full_name}"
            itemBinding.createdDateTextView.text = "Owner: ${item.owner}"
        }

    }

    private val diffCallback = object : DiffUtil.ItemCallback<GithubRepositoryModel>() {
        override fun areItemsTheSame(
            oldItem: GithubRepositoryModel,
            newItem: GithubRepositoryModel,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GithubRepositoryModel,
            newItem: GithubRepositoryModel,
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGithubUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun submitList(list: List<GithubRepositoryModel>) = differ.submitList(list)

}