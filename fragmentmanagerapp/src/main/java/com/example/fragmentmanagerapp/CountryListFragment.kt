package com.example.fragmentmanagerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CountryListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var countryAdapter: CountryAdapter
    private val countries = listOf(
        "Россия", "США", "Канада", "Германия", "Франция", "Италия", "Япония"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_country_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        countryAdapter = CountryAdapter(countries) { country ->
            // Передаем выбранную страну в DetailsFragment
            val fragment = CountryDetailsFragment()
            val bundle = Bundle()
            bundle.putString("country_name", country)
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentDetails, fragment)
                .commit()
        }
        recyclerView.adapter = countryAdapter

        return view
    }
}