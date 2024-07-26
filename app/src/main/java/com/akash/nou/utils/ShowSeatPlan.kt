/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.utils

import android.content.Context
import com.akash.nou.dto.TicketLookUpDTO
import com.akash.nou.model.Tickets
import com.google.android.material.bottomsheet.BottomSheetDialog

fun showSeatPlan(ctx: Context, ticketBody: TicketLookUpDTO, data: Tickets) {
    val popupWindow = BottomSheetDialog(ctx)
    popupWindow.setCancelable(true)
    popupWindow.show()
}