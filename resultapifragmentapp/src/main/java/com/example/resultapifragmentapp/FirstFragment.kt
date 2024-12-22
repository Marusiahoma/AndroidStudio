package com.example.resultapifragmentapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.resultapifragmentapp.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    interface OnDataPassListener {
        fun onDataPass(data: String)
    }

    private var dataPassListener: OnDataPassListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPassListener = context as? OnDataPassListener
            ?: throw ClassCastException("$context must implement OnDataPassListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.sendButton.setOnClickListener {
            val inputText = binding.inputText.text.toString()
            dataPassListener?.onDataPass(inputText)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
