/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.viewmodelfactory

import RetrofitClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akash.nou.repository.TicketRepository
import com.akash.nou.viewmodel.TicketViewModel

class TicketViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TicketViewModel::class.java)) {
            val repository = TicketRepository(RetrofitClient.getTicketInterfaceService())
            return TicketViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}