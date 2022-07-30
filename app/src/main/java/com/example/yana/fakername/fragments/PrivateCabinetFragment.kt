package com.example.yana.fakername.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.yana.fakername.R
import com.example.yana.fakername.adapters.DocumentListener
import com.example.yana.fakername.adapters.DocumentsAdapter
import com.example.yana.fakername.databinding.FragmentPrivateCabinetBinding
import com.example.yana.fakername.fragmentsViewModel.PrivateCabinetViewModel
import com.example.yana.fakername.ui.CastomViewCallback
import com.example.yana.fakername.ui.SelectScreenActivity
import com.example.yana.fakername.utils.cleanLaunchActivity
import com.example.yana.fakername.utils.setSafeOnClickListener
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PrivateCabinetFragment: Fragment(), CastomViewCallback, DocumentListener {

    private lateinit var binding: FragmentPrivateCabinetBinding
    private val viewModel: PrivateCabinetViewModel by viewModel()
    private val adapter by lazy { DocumentsAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPrivateCabinetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        viewModel.eventAuth.observe(viewLifecycleOwner) {
            if (it) requireContext().cleanLaunchActivity<SelectScreenActivity>()
        }

//        binding.recyclerCom.adapter = adapter

        lifecycleScope.launch {
            viewModel.doc().collect { adapter.submitData(it) }
        }

        viewModel.progress.observe(viewLifecycleOwner) {
            binding.progress.isVisible = it
        }

        viewModel.profile.observe(viewLifecycleOwner) {
            binding.profileName.text = it?.name
            binding.view.setupText(it?.name)
            binding.adressName.text = it?.email
            val phone = if (it?.phone == null) getString(R.string.notSpecified)
            else it.phone.toString()
            binding.telephoneName.text = phone
        }
    }

    private fun setupListeners() {
        binding.btnExit.setSafeOnClickListener {
            viewModel.logout()
        }
        binding.view.setupListener(this)
    }

    override fun changeUserName(name: String) {
        viewModel.profileUser(name)
    }

    override fun editDocument(id: Int) {
//        (requireActivity() as MainActivity).changeFragment(EditCommentFragment(id), true)
    }
}