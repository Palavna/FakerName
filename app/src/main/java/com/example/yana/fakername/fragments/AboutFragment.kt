package com.example.yana.fakername.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.yana.fakername.adapters.DocumentListener
import com.example.yana.fakername.adapters.DocumentsAdapter
import com.example.yana.fakername.dataClass.DocumentsPage
import com.example.yana.fakername.databinding.FragmentAboutBinding
import com.example.yana.fakername.fragmentsViewModel.AboutViewModel
import com.example.yana.fakername.ui.MainActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class AboutFragment: Fragment(), DocumentListener {

    private lateinit var binding: FragmentAboutBinding
    private val viewModel: AboutViewModel by viewModel()
    private val adapter by lazy { DocumentsAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.recyclerDoc.adapter = adapter

        lifecycleScope.launch {
            viewModel.doc().collect { adapter.submitData(it) }
        }
    }

    override fun editDocument(id: Int) {
        (requireActivity() as MainActivity).changeFragment(EditCommentFragment(id), true)
    }

}