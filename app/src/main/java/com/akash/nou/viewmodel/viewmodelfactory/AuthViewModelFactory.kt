/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.viewmodel.viewmodelfactory

import RetrofitClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akash.nou.repository.AuthRepository
import com.akash.nou.viewmodel.AuthViewModel

class AuthViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            val repository = AuthRepository(RetrofitClient.getAuthInterfaceService())
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}