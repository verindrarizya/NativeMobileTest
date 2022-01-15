package com.verindrarizya.nativemobiletest.ui.detailpost

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.verindrarizya.nativemobiletest.R
import com.verindrarizya.nativemobiletest.databinding.ActivityDetailPostBinding
import com.verindrarizya.nativemobiletest.domain.entity.Comment
import com.verindrarizya.nativemobiletest.domain.entity.Post
import com.verindrarizya.nativemobiletest.util.view.setGone
import com.verindrarizya.nativemobiletest.util.view.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPostActivity : AppCompatActivity() {

    private val binding: ActivityDetailPostBinding by lazy {
        ActivityDetailPostBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailPostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val postData = intent.getParcelableExtra<Post>(POST_EXTRA) as Post
        viewModel.getComments(postData.id)

        initConfigActionBar()
        populatePostView(postData)
        initConfigRv()
        initSwipeRefresh(postData.id)
        initObservers()
        initRefreshButtonAction(postData.id)
    }

    private fun initSwipeRefresh(postId: Int) {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getComments(postId)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initObservers() {
        viewModel.isLoading.observe(this) {
            if (it == true) {
                binding.errorView.setGone()
                binding.rvComments.setGone()
                binding.progressBar.setVisible()
            } else {
                binding.progressBar.setGone()
                binding.rvComments.setVisible()
            }
        }

        viewModel.isError.observe(this) {
            if (it == true) {
                binding.rvComments.setGone()
                binding.errorView.setVisible()
            } else {
                binding.errorView.setGone()
            }
        }

        viewModel.comments.observe(this) { comments: List<Comment> ->
            initAdapter(comments)
            Log.d("TestDetailComment", "comments:\n $comments")
        }
    }

    private fun initAdapter(comments: List<Comment>) {
        val commentAdapter = CommentAdapter {}
        commentAdapter.submitList(comments)
        binding.rvComments.adapter = commentAdapter
    }

    private fun initConfigRv() {
        with(binding.rvComments) {
            clipToPadding = false
            layoutManager = LinearLayoutManager(this@DetailPostActivity)
            setHasFixedSize(false)
        }
    }

    private fun initRefreshButtonAction(postId: Int) {
        binding.btnRefresh.setOnClickListener {
            viewModel.getComments(postId)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initConfigActionBar() {
        supportActionBar?.apply {
            title = getString(R.string.detail_post_appbar_title)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_ios_new_24)
        }
    }

    private fun populatePostView(post: Post) {
        with(binding) {
            postTitle.text = post.title
            postUsername.text = post.user?.username
            postCompany.text = post.user?.companyName
            postBody.text = post.body
        }
    }

    companion object {
        const val POST_EXTRA = "post_extra"
    }
}