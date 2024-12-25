package com.akash.nou.repository

import com.akash.nou.dto.AllLookUpDto
import com.akash.nou.dto.TicketLookUpDto
import com.akash.nou.model.AuthRequest
import com.akash.nou.model.AuthResponse
import com.akash.nou.model.Ticket
import com.akash.nou.service.EncryptedSharedPrefService
import com.akash.nou.service.SharedPrefService
import com.akash.nou.util.ApiState

interface Repository {
    suspend fun login(
        authReq: AuthRequest,
        sharedPrefService: SharedPrefService,
        encryptedSharedPrefService: EncryptedSharedPrefService
    ): ApiState<AuthResponse>

    suspend fun verifyOtp(
        authReq: AuthRequest,
        sharedPrefService: SharedPrefService,
        encryptedSharedPrefService: EncryptedSharedPrefService
    ): ApiState<AuthResponse>

    suspend fun allLookUp(
        allLookUpDto: AllLookUpDto,
        sharedPrefService: SharedPrefService,
        encryptedSharedPrefService: EncryptedSharedPrefService
    ): ApiState<Ticket>

    suspend fun searchTicket(
        ticketLookUpDto: TicketLookUpDto,
        sharedPrefService: SharedPrefService,
        encryptedSharedPrefService: EncryptedSharedPrefService
    ): ApiState<Ticket>
}