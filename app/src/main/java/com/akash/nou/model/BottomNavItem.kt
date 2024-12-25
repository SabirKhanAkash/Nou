/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.akash.nou.R

sealed class BottomNavItem(
    val route: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @StringRes val title: Int,
) {
    object ticket : BottomNavItem(
        "ticket", R.drawable.ic_home, R.drawable.ic_unselected_home, R.string.menu_item_1
    )

    object history : BottomNavItem(
        "history", R.drawable.ic_history, R.drawable.ic_unselected_history, R.string.menu_item_2
    )

    object profile : BottomNavItem(
        "profile", R.drawable.ic_profile, R.drawable.ic_unselected_profile, R.string.menu_item_3
    )
}