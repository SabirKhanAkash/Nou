/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

import android.content.Context
import android.content.Intent
import com.akash.nou.utils.SharedPref
import com.akash.nou.view.feature.auth.activity.AuthActivity

private val sharedPref: SharedPref by lazy { SharedPref() }

fun logout(context: Context) {
    sharedPref.clearData(context)
    val intent = Intent(context, AuthActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    context.startActivity(intent)
}
