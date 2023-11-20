/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.view.fragment

import GenericApiResponse
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.akash.nou.R
import com.akash.nou.databinding.FragmentTicketBinding
import com.akash.nou.model.TicketBody
import com.akash.nou.utils.LoadingDialog
import com.akash.nou.utils.SharedPref
import com.akash.nou.utils.showSeatPlan
import com.akash.nou.viewmodel.TicketViewModel
import com.akash.nou.viewmodel.viewmodelfactory.TicketViewModelFactory
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import showTopToast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TicketFragment : Fragment() {




    /**
     * Global Variables
     */
    private lateinit var ticketViewModel: TicketViewModel
    private lateinit var binding: FragmentTicketBinding
    private var selectedSeatCategory: String = ""
    private var source: String = ""
    private var destination: String = ""
    private var journeyDate: String = ""
    private var journeyTime: String = ""
    private var childItemCount = 0
    private var adultItemCount = 0
    private lateinit var ticketBody: TicketBody
    private var isInputValidated = false
    private lateinit var phone_no: String
    private lateinit var authToken: String
    private lateinit var refreshToken: String
    private val calendar = Calendar.getInstance()
    private var sharedPref: SharedPref = SharedPref()
    private var loadingDialog: LoadingDialog = LoadingDialog(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /**
         * Initializations
         */
        binding = FragmentTicketBinding.inflate(layoutInflater)
        val items = requireContext().resources.getStringArray(R.array.zilla)
        val seat_category_items = requireContext().resources.getStringArray(R.array.seat_category)
        val seat_categoryAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, seat_category_items)
        val sourceAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items)
        val destinationAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items)
        binding.seatCategory.setAdapter(seat_categoryAdapter)
        binding.source.setAdapter(sourceAdapter)
        binding.destination.setAdapter(destinationAdapter)
        ticketViewModel = ViewModelProvider(this, TicketViewModelFactory())[TicketViewModel::class.java]
        phone_no = sharedPref.getUser(requireContext(), "user").phone_no.toString()
        authToken = sharedPref.getString(requireContext(), "authToken").toString()
        refreshToken = sharedPref.getString(requireContext(), "refreshToken").toString()


        /**
         * Event Listener
         */
        binding.seatCategory.setOnItemClickListener { parent, view, position, id ->
            binding.errorMessage.visibility = View.GONE
            selectedSeatCategory = parent.getItemAtPosition(position).toString()
        }
        binding.source.setOnItemClickListener { parent, view, position, id ->
            binding.errorMessage.visibility = View.GONE
            source = parent.getItemAtPosition(position).toString()
        }
        binding.destination.setOnItemClickListener { parent, view, position, id ->
            binding.errorMessage.visibility = View.GONE
            destination = parent.getItemAtPosition(position).toString()
        }
        binding.journeyDate.setOnClickListener { showDatePickerDialog() }
        binding.timePickerEditText.setOnClickListener { showTimePickerDialog() }
        binding.btnMinusChild.setOnClickListener { decrementChildItemCount() }
        binding.btnPlusChild.setOnClickListener { incrementChildItemCount() }
        binding.btnMinusAdult.setOnClickListener { decrementAdultItemCount() }
        binding.btnPlusAdult.setOnClickListener { incrementAdultItemCount() }
        binding.confirmTicket.setOnClickListener {
            if (
                selectedSeatCategory.isNotEmpty() &&
                source.isNotEmpty() &&
                destination.isNotEmpty() &&
                journeyDate.isNotEmpty() &&
                journeyTime.isNotEmpty() &&
                adultItemCount != 0
            ) {
                binding.errorMessage.visibility = View.GONE
                isInputValidated = true
            }
            if (isInputValidated) {
                binding.errorMessage.visibility = View.VISIBLE
                binding.errorMessage.text = "টিকিট খোঁজা হচ্ছে..."
                binding.errorMessage.setTextColor(Color.parseColor("#3c763d"))
                ticketBody = TicketBody(
                    selectedSeatCategory,
                    source,
                    destination,
                    "$journeyDate $journeyTime",
                    adultItemCount
                )
                ticketViewModel.searchTicket(phone_no, authToken, refreshToken, ticketBody)
            }
            else {
                if (selectedSeatCategory == "") {
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.errorMessage.text = "সিটের ধরণ সিলেক্ট করুন"
                }
                if (source == "") {
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.errorMessage.text = "যাত্রা শুরুর স্থানটি সিলেক্ট করুন"
                }
                if (destination == "") {
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.errorMessage.text = "যাত্রা শেষের স্থানটি সিলেক্ট করুন"
                }
                if (journeyDate == "") {
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.errorMessage.text = "যাত্রার তারিখ সিলেক্ট করুন"
                }
                if (journeyTime == "") {
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.errorMessage.text = "যাত্রার সময় সিলেক্ট করুন"
                }
                if (adultItemCount == 0) {
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.errorMessage.text = "যাত্রী সংখ্যা সিলেক্ট করুন"
                }
                if (childItemCount > 0 && adultItemCount == 0) {
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.errorMessage.text = "প্রাপ্তবয়স্ক ব্যতীত শিশু টিকিট পাওয়া যাবেনা"
                }
            }
        }


        /**
         * LiveData Observer
         */
        ticketViewModel.ticketsLiveData.observe(viewLifecycleOwner) { result ->
            when(result) {
                is GenericApiResponse.Success -> {
                    if(result.data.status == "Success") {
                        if(result.data.tickets.count >= adultItemCount) {
                            showSeatPlan(requireContext(), ticketBody, result.data)
                        }
                        else {
                            binding.errorMessage.visibility = View.VISIBLE
                            binding.errorMessage.text = "দুঃখিত! উল্লিখিত বিবরণের টিকিট নেই"
                            binding.errorMessage.setTextColor(Color.parseColor("#FFBCA903"))
                        }
                    }
                    else {
                        showTopToast(
                            requireContext(),
                            "দুঃখিত! আবার চেষ্টা করুন",
                            "short",
                            "neutral"
                        )
                    }
                }
                is GenericApiResponse.Error -> {
                    showTopToast(
                        requireContext(),
                        "দুঃখিত! কারিগরি ত্রুটি হয়েছে ☹️\n${result.message}",
                        "short",
                        "neutral"
                    )
                }
                else -> {
                    showTopToast(
                        requireContext(),
                        "দুঃখিত! অজানা ত্রুটি হয়েছে ☹️\n$result",
                        "short",
                        "neutral"
                    )
                }
            }
        }
        ticketViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if(isLoading)
                loadingDialog.startFragmentLoading()
            else
                loadingDialog.dismissLoading()
        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val items = requireContext().resources.getStringArray(R.array.zilla)
        val sourceAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items)
        val destinationAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items)
        binding.source.setAdapter(sourceAdapter)
        binding.destination.setAdapter(destinationAdapter)
    }

    private fun showDatePickerDialog() {
        binding.errorMessage.visibility = View.GONE
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
                journeyDate = "$formattedDate"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        binding.errorMessage.visibility = View.GONE
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .build()

        timePicker.addOnPositiveButtonClickListener {
            val selectedHour = timePicker.hour
            val selectedMinute = timePicker.minute
            val amPm = if (timePicker.hour < 12) "AM" else "PM"
            binding.timePickerEditText.text =
                getString(R.string.selected_time_format, selectedHour % 12, selectedMinute, amPm)
            journeyTime =
                getString(R.string.selected_time_format, selectedHour % 12, selectedMinute, amPm)
        }
        timePicker.show(childFragmentManager, "tag")
    }

    private fun updateItemCount() {
        binding.childItemCount.text = childItemCount.toString()
        binding.adultItemCount.text = adultItemCount.toString()
    }

    private fun decrementChildItemCount() {
        binding.errorMessage.visibility = View.GONE
        if (childItemCount > 0) {
            childItemCount--
            updateItemCount()
        }
    }

    private fun incrementChildItemCount() {
        binding.errorMessage.visibility = View.GONE
        childItemCount++
        updateItemCount()
    }

    private fun decrementAdultItemCount() {
        binding.errorMessage.visibility = View.GONE
        if (adultItemCount > 0) {
            adultItemCount--
            updateItemCount()
        }
    }

    private fun incrementAdultItemCount() {
        binding.errorMessage.visibility = View.GONE
        adultItemCount++
        updateItemCount()
    }
}