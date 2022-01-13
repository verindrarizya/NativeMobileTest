package com.verindrarizya.nativemobiletest.di

import com.verindrarizya.nativemobiletest.data.PostRepository
import com.verindrarizya.nativemobiletest.data.UserRepository
import com.verindrarizya.nativemobiletest.domain.repository.IPostRepository
import com.verindrarizya.nativemobiletest.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPostRepository(postRepository: PostRepository): IPostRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepository: UserRepository): IUserRepository

}