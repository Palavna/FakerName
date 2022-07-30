package com.example.yana.fakername.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yana.fakername.R
import com.example.yana.fakername.databinding.FragmentRegistrationBinding
import com.example.yana.fakername.fragmentsViewModel.RegistrationViewModel
import com.example.yana.fakername.ui.MainActivity
import com.example.yana.fakername.ui.SelectScreenActivity
import com.example.yana.fakername.utils.changeFragment
import com.example.yana.fakername.utils.cleanLaunchActivity
import com.example.yana.fakername.utils.setSafeOnClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.regex.Pattern

class RegistrationFragment: Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel: RegistrationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
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
        binding.btnVhodReg.setSafeOnClickListener {
            if (isEmailValid(binding.emailEtReg.text.toString())){
                viewModel.login(
                    binding.emailEtReg.text.toString(),
                    binding.passwordEtReg.text.toString())
            }
        }
        binding.btnRegistrationReg.setSafeOnClickListener {
            findNavController().navigate(R.id.toRegistrationFragment)
        }

        binding.forgotPass.setSafeOnClickListener{
            findNavController().navigate(R.id.toForgotPasswordFragment)
        }
    }
    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) {
            binding.emailEtReg.error = getString(R.string.emailEmpty)
            return false
        }
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (!isValid){
            binding.emailEtReg.error = getString(R.string.enterEmailCorrect)
        }
        return isValid
    }
}