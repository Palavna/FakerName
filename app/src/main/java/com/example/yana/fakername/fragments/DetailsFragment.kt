package com.example.yana.fakername.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yana.fakername.databinding.FragmentDetailsBinding
import com.example.yana.fakername.databinding.FragmentEditCommentBinding
import com.example.yana.fakername.fragmentsViewModel.DetailsViewModel
import com.example.yana.fakername.fragmentsViewModel.EditCommentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment: Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}