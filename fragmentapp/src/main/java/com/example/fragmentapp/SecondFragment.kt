package com.example.fragmentapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Инфлейтим разметку фрагмента
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        // Получаем переданный номер из arguments
        val number = arguments?.getInt("item_number", -1) // -1 - значение по умолчанию, если номер не передан

        // Если number не равен -1, это значит, что данные были переданы
        if (number != -1) {
            Toast.makeText(activity, "Номер элемента: $number", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "Номер не передан", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
