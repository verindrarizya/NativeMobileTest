package com.verindrarizya.nativemobiletest.ui.listpost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verindrarizya.nativemobiletest.util.Resource
import com.verindrarizya.nativemobiletest.domain.entity.Post
import com.verindrarizya.nativemobiletest.domain.usecase.ListPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPostViewModel @Inject constructor(private val listPostUseCase: ListPostUseCase): ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean>
        get() = _isError

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            _isError.value = false
            _isLoading.value = true

            val posts = listPostUseCase.invoke()
            when (posts) {
                is Resource.Success -> {
                    _isLoading.value = false
                    _posts.value = posts.data
                }

                is Resource.Error -> {
                    _isLoading.value = false
                    _isError.value = true
                }
            }
        }
    }

}