package com.akash.nou.utils

import android.content.Context
import com.akash.nou.model.SeatMap
import com.akash.nou.model.TicketBody
import com.google.android.material.bottomsheet.BottomSheetDialog

fun showSeatPlan(ctx: Context, ticketBody: TicketBody, data: SeatMap) {
    val popupWindow: BottomSheetDialog = BottomSheetDialog(ctx)
    popupWindow.show()
}