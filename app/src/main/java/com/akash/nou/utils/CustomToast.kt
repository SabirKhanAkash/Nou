/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.akash.nou.R

var customToastView: View? = null

fun showTopToast(context: Context, message: String, duration: String, type: String) {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    if (type == "positive")
        customToastView = inflater.inflate(R.layout.positive_toast_layout, null)
    if (type == "negative")
        customToastView = inflater.inflate(R.layout.negative_toast_layout, null)
    if (type == "neutral")
        customToastView = inflater.inflate(R.layout.neutral_toast_layout, null)

    val toast = Toast(context)
    toast.view = customToastView
    if (duration == "short")
        toast.duration = Toast.LENGTH_SHORT
    else if (duration == "long")
        toast.duration = Toast.LENGTH_LONG
    toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 25, 25)

    val toastText = customToastView!!.findViewById<TextView>(R.id.custom_toast_text)
    toastText.text = message

    toast.show()
}
