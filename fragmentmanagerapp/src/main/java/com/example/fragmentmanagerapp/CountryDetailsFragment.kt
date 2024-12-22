package com.example.fragmentmanagerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class CountryDetailsFragment : Fragment() {

    private lateinit var countryTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_country_details, container, false)

        countryTextView = view.findViewById(R.id.countryDetailsTextView)

        // Получаем данные, переданные через Bundle
        val countryName = arguments?.getString("country_name")
        countryTextView.text = "Детали о стране: $countryName"

        return view
    }
}
