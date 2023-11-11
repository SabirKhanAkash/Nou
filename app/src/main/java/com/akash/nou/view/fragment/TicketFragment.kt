/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akash.nou.R
import com.akash.nou.databinding.FragmentTicketBinding

class TicketFragment : Fragment() {
    /**
     * Global Variables
     */
    private lateinit var binding: FragmentTicketBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /**
         * Initializations
         */
        binding = FragmentTicketBinding.inflate(layoutInflater)



        return binding.root
    }
}