package com.example.yana.fakername.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yana.fakername.R
import com.example.yana.fakername.adapters.SpinnerAdapter
import com.example.yana.fakername.databinding.FragmentMainBinding
import com.example.yana.fakername.fragmentsViewModel.MainViewModel
import com.example.yana.fakername.ui.FragmentCallBack
import com.example.yana.fakername.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModel()
    private var callBack: FragmentCallBack? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.countries.observe(viewLifecycleOwner, {
            val adapter = SpinnerAdapter(requireContext(), R.layout.item_spinner, it?.toTypedArray() ?: emptyArray())

            binding.spinner.adapter = adapter
        })

        binding.btnSearch.setOnClickListener {

        }
    }
}