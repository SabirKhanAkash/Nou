package com.akash.nou.utils

import android.content.Context
import com.akash.nou.model.Tickets
import com.akash.nou.model.TicketBody
import com.google.android.material.bottomsheet.BottomSheetDialog

fun showSeatPlan(ctx: Context, ticketBody: TicketBody, data: Tickets) {
    val popupWindow = BottomSheetDialog(ctx)
    popupWindow.setCancelable(true)
    popupWindow.show()
}