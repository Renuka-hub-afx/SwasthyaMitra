package com.example.swasthyamitra

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.swasthyamitra.data.Goal
import com.example.swasthyamitra.data.UserRepository
import com.example.swasthyamitra.data.User
import kotlinx.coroutines.launch
import kotlin.jvm.java

class UserViewModel(private val repo: UserRepository) : ViewModel() {

    // --- Existing Functions ---
    fun getUserByEmail(email: String): LiveData<User?> = repo.getUserByEmail(email)

    fun insertUser(user: User, onComplete: (Long) -> Unit = {}) {
        viewModelScope.launch {
            val id = repo.insertUser(user)
            onComplete(id)
        }
    }

    // --- NEW: Update Physical Stats ---
    fun updateUserPhysicalStats(uid: Long, h: Float, w: Float, g: String, b: Float) {
        viewModelScope.launch {
            repo.updateUserPhysicalStats(uid, h, w, g, b)
        }
    }

    // --- NEW: Insert Goal ---
    fun insertGoal(goal: Goal) {
        viewModelScope.launch {
            repo.insertGoal(goal)
        }
    }

    // --- Factory ---
    class UserViewModelFactory(private val repo: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(repo) as T
            }
            throw kotlin.IllegalArgumentException("Unknown ViewModel class")
        }
    }
}