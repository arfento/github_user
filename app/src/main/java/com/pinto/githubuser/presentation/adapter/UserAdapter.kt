package com.pinto.githubuser.presentation.adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pinto.githubuser.R
import com.pinto.githubuser.data.models.GithubRepositoryModel
import com.pinto.githubuser.databinding.ItemGithubUserBinding
import com.pinto.githubuser.presentation.screen.detail.DetailActivity


class UserAdapter(private val context: Context) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    inner class ViewHolder(private val itemBinding: ItemGithubUserBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(position: Int) {
            val item = differ.currentList[position]
            itemBinding.nameTextView.text = "Name: ${item.name}"
            itemBinding.descTextView.text = "Description:  ${item.description}"
            itemBinding.createdDateTextView.text = "Owner: ${item.owner}"
            Glide.with(context)
                .load(item.owner.avatar_url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(itemBinding.thubmImage)

            itemBinding.root.setOnClickListener{
                val intent = Intent(context, DetailActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("ITEM_ID", item.id)
                bundle.putString("ITEM_NAME", item.name)
                bundle.putString("ITEM_FULLNAME", item.full_name)
                bundle.putString("ITEM_DESCRIPTION", item.description)
                bundle.putString("ITEM_URL", item.url)
                bundle.putString("ITEM_OWNER", item.owner.toString())
                bundle.putString("ITEM_IMAGE_URL", item.owner.avatar_url)
                intent.putExtras(bundle)
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
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