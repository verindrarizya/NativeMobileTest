package com.verindrarizya.nativemobiletest.ui.listpost

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.verindrarizya.nativemobiletest.databinding.PostItemBinding
import com.verindrarizya.nativemobiletest.domain.entity.Post

class PostAdapter(private val onItemClicked: (Post) -> Unit): ListAdapter<Post, PostAdapter.PostViewHolder>(DIFF_CALlBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postItemBinding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(postItemBinding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostViewHolder(private val binding: PostItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.apply {
                postTitle.text = post.title
                postUsername.text = post.user?.username
                postCompany.text = post.user?.companyName
                postBody.text = post.body
            }

            itemView.setOnClickListener { onItemClicked(post) }
        }
    }

    companion object {
        private val DIFF_CALlBACK = object: DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }
        }
    }
}