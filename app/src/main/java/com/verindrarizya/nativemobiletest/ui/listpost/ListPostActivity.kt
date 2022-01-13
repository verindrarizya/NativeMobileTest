package com.verindrarizya.nativemobiletest.ui.listpost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.verindrarizya.nativemobiletest.databinding.ActivityListPostBinding
import com.verindrarizya.nativemobiletest.domain.entity.Post
import com.verindrarizya.nativemobiletest.util.view.setGone
import com.verindrarizya.nativemobiletest.util.view.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListPostActivity : AppCompatActivity() {

    private val binding: ActivityListPostBinding by lazy {
        ActivityListPostBinding.inflate(layoutInflater)
    }

    private val viewModel: ListPostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initObservers()
    }

    private fun initObservers() {
        // posts
        viewModel.posts.observe(this) { posts: List<Post> ->
            initAdapter(posts)
        }

        // loading state
        viewModel.isLoading.observe(this) {
            if (it == true) {
                binding.rvPosts.setGone()
                binding.tvErrorStatement.setGone()
                binding.progressBar.setVisible()
            } else {
                binding.progressBar.setGone()
                binding.rvPosts.setVisible()
            }
        }

        viewModel.isError.observe(this) {
            if (it == true) {
                binding.tvErrorStatement.setVisible()
            } else {
                binding.tvErrorStatement.setGone()
            }
        }
    }

    private fun initAdapter(posts: List<Post>) {
        val postAdapter = PostAdapter { post: Post ->
            Log.d("PostClicked", "id: ${post.id}")
        }

        postAdapter.submitList(posts)

        with(binding.rvPosts) {
            layoutManager = LinearLayoutManager(this@ListPostActivity)
            setHasFixedSize(true)
            adapter = postAdapter
        }
    }
}