package com.verindrarizya.nativemobiletest.ui.detailpost

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verindrarizya.nativemobiletest.domain.entity.Comment
import com.verindrarizya.nativemobiletest.domain.usecase.PostCommentUseCase
import com.verindrarizya.nativemobiletest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(private val postCommentUseCase: PostCommentUseCase): ViewModel()  {

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean>
        get() = _isError

    fun getComments(postId: Int) {
        Log.d("TestViewModel", "getComments: $postId")
        viewModelScope.launch {
            _isError.value = false
            _isLoading.value = true

            val comments = postCommentUseCase.invoke(postId)
            when (comments) {
                is Resource.Error -> {
                    _isLoading.value = false
                    _isError.value = false
                }
                is Resource.Success -> {
                    _isLoading.value = false
                    _comments.value = comments.data
                }
            }
        }
    }
}