package com.example.yana.fakername.fragments

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yana.fakername.R
import com.example.yana.fakername.databinding.FragmentAddTextBinding
import com.example.yana.fakername.fragmentsViewModel.AddTextViewModel
import com.example.yana.fakername.prefs.SharedPreferenceFaker
import com.example.yana.fakername.ui.MainActivity
import com.example.yana.fakername.ui.SelectScreenActivity
import com.example.yana.fakername.utils.cleanLaunchActivity
import com.example.yana.fakername.utils.setSafeOnClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTextFragment : Fragment() {

    private lateinit var binding: FragmentAddTextBinding
    private val viewModel: AddTextViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        viewModel.eventAuth.observe(viewLifecycleOwner) {
            if (it) requireContext().cleanLaunchActivity<SelectScreenActivity>()
        }
    }

    private fun setupListeners() {
        binding.btnVhod.setSafeOnClickListener {
            if (isEmailValid(binding.emailEt.text.toString())) {
                viewModel.login(
                    binding.emailEt.text.toString(),
                    binding.passwordEt.text.toString()
                )
            }
        }

        binding.zabiliPass.setSafeOnClickListener {
            findNavController().navigate(R.id.toForgotPasswordFragment3)
        }
    }

    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) {
            binding.emailEt.error = getString(R.string.emailEmpty)
            return false
        }
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (!isValid) {
            binding.emailEt.error = getString(R.string.enterEmailCorrect)
        }
        return isValid
    }
}