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
        "ticket", R.drawable.home_icon, R.drawable.unselected_home_icon, R.string.menu_item_1
    )

    object history : BottomNavItem(
        "history", R.drawable.history_icon, R.drawable.unselected_history_icon, R.string.menu_item_2
    )

    object profile : BottomNavItem(
        "profile", R.drawable.profile_icon, R.drawable.unselected_profile_icon, R.string.menu_item_3
    )
}