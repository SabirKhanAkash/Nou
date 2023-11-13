/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.view.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.akash.nou.R
import com.akash.nou.databinding.FragmentTicketBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TicketFragment : Fragment() {
    companion object {
        fun newInstance() = TicketFragment()
    }

    /**
     * Global Variables
     */
    private lateinit var binding: FragmentTicketBinding
    private var childItemCount = 0
    private var adultItemCount = 0
    private val calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /**
         * Initializations
         */
        binding = FragmentTicketBinding.inflate(layoutInflater)

        val items = requireContext().resources.getStringArray(R.array.zilla)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items)

        binding.source.setAdapter(adapter)

        /**
         * Event Listener
         */
        binding.journeyDate.setOnClickListener { showDatePickerDialog() }
        binding.timePickerEditText.setOnClickListener { showTimePickerDialog() }
        binding.btnMinusChild.setOnClickListener { decrementChildItemCount() }
        binding.btnPlusChild.setOnClickListener { incrementChildItemCount() }
        binding.btnMinusAdult.setOnClickListener { decrementAdultItemCount() }
        binding.btnPlusAdult.setOnClickListener { incrementAdultItemCount() }

        return binding.root
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.DatePickerStyle,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                // Update the TextView with the selected date
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)

                val dateFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)

                binding.journeyDate.text = "$formattedDate"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .build()

        timePicker.addOnPositiveButtonClickListener {
            val selectedHour = timePicker.hour
            val selectedMinute = timePicker.minute
            val amPm = if (timePicker.hour < 12) "AM" else "PM"
            binding.timePickerEditText.text = getString(R.string.selected_time_format, selectedHour % 12, selectedMinute, amPm)
        }
        timePicker.show(childFragmentManager, "tag")
    }

    private fun updateItemCount() {
        binding.childItemCount.text = childItemCount.toString()
        binding.adultItemCount.text = adultItemCount.toString()
    }

    private fun decrementChildItemCount() {
        if (childItemCount > 0) {
            childItemCount--
            updateItemCount()
        }
    }

    private fun incrementChildItemCount() {
        childItemCount++
        updateItemCount()
    }

    private fun decrementAdultItemCount() {
        if (adultItemCount > 0) {
            adultItemCount--
            updateItemCount()
        }
    }

    private fun incrementAdultItemCount() {
        adultItemCount++
        updateItemCount()
    }
}