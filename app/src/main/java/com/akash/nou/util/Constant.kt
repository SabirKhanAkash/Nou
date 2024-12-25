/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.akash.nou.R

object Constant {
    const val LOG_TAG = "NouApp"
    const val AUTH_SOURCE = "nou-mobile"

    const val SP_ACCESS_TOKEN_KEY = "accessToken"
    const val SP_NAME = "spNou"

    const val ESP_REFRESH_TOKEN_KEY = "refreshToken"
    const val ESP_NAME = "spNouTinkEncrypted"
    const val ESP_KEYSET_NAME = "tink_keyset"
    const val ESP_KEYSET_VALUE = "spNouTinkEncrypted_pref"

    const val LOGIN_SCREEN_ICON_DESCRIPTION = "Logo Here"
    const val LOGIN_SCREEN_TAGLINE = "নৌযানের টিকেট ব্যবস্থাপনার এক বিশ্বস্ত নাম"

    const val INVALID_REFRESH_TOKEN_RESPONSE_MSG = "401"
    const val UNEXPECTED_ERROR_MSG = "An unexpected error occurred"

    const val TICKET_SCREEN_HEADING = "টিকিট কাউন্টার"
    const val TICKET_SCREEN_SUB_HEADING = "সিট বুক করুন"

    val balooda2font = FontFamily(Font(R.font.balooda, FontWeight.Normal))
    val app_theme_color = Color(0xFF0064D2)
    val app_theme_color_secondary = Color(0xFF488BD5)
    val app_theme_color_tertiary = Color(0xFF87ADD6)
    val toast_red = Color(0xFFefd7d7)
    val toast_text_red = Color(0xFFab527b)
    val toast_green = Color(0xFFd7eccf)
    val toast_text_green = Color(0xFF3c763d)
    val toast_blue = Color(0xFFabcee5)
    val toast_text_blue = Color(0xFF3c647c)
    val light_black = Color(0xFF0D1634)
    val light_gray_3 = Color(0xFF22313F)
    val light_gray_2 = Color(0xFF808080)
    val medium_gray = Color(0xFF1A304A)
    val regular_gray = Color(0xFF898B8D)
    val very_light_gray = Color(0xFFE6E8E9)
    val small_light_gray = Color(0xFFF5F6F6)
    val light_gray = Color(0xFFDAEAECED)
}


