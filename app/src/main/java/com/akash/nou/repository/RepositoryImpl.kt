package com.akash.nou.repository

import com.akash.nou.dto.AllLookUpDto
import com.akash.nou.dto.TicketLookUpDto
import com.akash.nou.model.AuthRequest
import com.akash.nou.model.AuthResponse
import com.akash.nou.model.Ticket
import com.akash.nou.network.RetrofitProvider
import com.akash.nou.service.EncryptedSharedPrefService
import com.akash.nou.service.SharedPrefService
import com.akash.nou.util.ApiState
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {
    override suspend fun login(
        authReq: AuthRequest,
        sharedPrefService: SharedPrefService,
        encryptedSharedPrefService: EncryptedSharedPrefService,
    ): ApiState<AuthResponse> = try {
        ApiState.Success(
            RetrofitProvider.getApiService(
                sharedPrefService,
                encryptedSharedPrefService
            ).login(authReq)
        )
    } catch (e: Exception) {
        ApiState.Error(errorMsg = e.message.toString())
    }

    override suspend fun verifyOtp(
        authReq: AuthRequest,
        sharedPrefService: SharedPrefService,
        encryptedSharedPrefService: EncryptedSharedPrefService,
    ): ApiState<AuthResponse> = try {
        ApiState.Success(
            RetrofitProvider.getApiService(
                sharedPrefService,
                encryptedSharedPrefService
            ).verifyOtp(authReq)
        )
    } catch (e: Exception) {
        ApiState.Error(errorMsg = e.message.toString())
    }

    override suspend fun allLookUp(
        allLookUpDto: AllLookUpDto,
        sharedPrefService: SharedPrefService,
        encryptedSharedPrefService: EncryptedSharedPrefService,
    ):
            ApiState<Ticket> = try {
        ApiState.Success(
            RetrofitProvider.getApiService(sharedPrefService, encryptedSharedPrefService).allLookUp(
                pageNo = allLookUpDto.pageNo,
                perPage = allLookUpDto.perPage,
                type = allLookUpDto.type.toString(),
                vendorId = allLookUpDto.vendorId.toString(),
                orderValue = allLookUpDto.orderValue,
            )
        )
    } catch (e: Exception) {
        ApiState.Error(errorMsg = e.message.toString())
    }

    override suspend fun searchTicket(
        ticketLookUpDto: TicketLookUpDto,
        sharedPrefService: SharedPrefService,
        encryptedSharedPrefService: EncryptedSharedPrefService,
    ): ApiState<Ticket> = try {
        ApiState.Success(
            RetrofitProvider.getApiService(sharedPrefService, encryptedSharedPrefService)
                .searchTicket(
//                    accessToken = sharedPrefService.getString(Constant.SP_ACCESS_TOKEN_KEY),
//                    refreshToken = encryptedSharedPrefService.getString(Constant.ESP_REFRESH_TOKEN_KEY),
                    pageNo = ticketLookUpDto.pageNo,
                    seatCategory = ticketLookUpDto.seatCategory.toString(),
                    source = ticketLookUpDto.source.toString(),
                    destination = ticketLookUpDto.destination.toString(),
                    date = ticketLookUpDto.date.toString(),
                    time = ticketLookUpDto.date.toString(),
                    passengerCount = ticketLookUpDto.passengerCount,
                    childPassengerCount = ticketLookUpDto.childPassengerCount,
                )
        )
    } catch (e: Exception) {
        ApiState.Error(errorMsg = e.message.toString())
    }
}