package com.verindrarizya.nativemobiletest.ui.listpost

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.verindrarizya.nativemobiletest.R
import com.verindrarizya.nativemobiletest.databinding.ActivityListPostBinding
import com.verindrarizya.nativemobiletest.domain.entity.Post
import com.verindrarizya.nativemobiletest.ui.detailpost.DetailPostActivity
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

        initConfigActionBar()
        initSwipeRefresh()
        initConfigRv()
        initObservers()
    }

    private fun initSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getPosts()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initObservers() {
        viewModel.posts.observe(this) { posts: List<Post> ->
            initAdapter(posts)
        }

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
                binding.rvPosts.setGone()
                binding.tvErrorStatement.setVisible()
            } else {
                binding.tvErrorStatement.setGone()
            }
        }
    }

    private fun initAdapter(posts: List<Post>) {
        val postAdapter = PostAdapter { post: Post ->
            val intent = Intent(this, DetailPostActivity::class.java)
            intent.putExtra(DetailPostActivity.POST_EXTRA, post)
            startActivity(intent)
        }

        postAdapter.submitList(posts)
        binding.rvPosts.adapter = postAdapter
    }

    private fun initConfigRv() {
        with(binding.rvPosts) {
            clipToPadding = false
            layoutManager = LinearLayoutManager(this@ListPostActivity)
            setHasFixedSize(true)
        }
    }

    private fun initConfigActionBar() {
        supportActionBar?.title = getString(R.string.list_post_appbar_title)
    }
}