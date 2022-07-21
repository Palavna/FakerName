package com.example.yana.fakername.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.yana.fakername.databinding.FragmentRegisterNameBinding
import com.example.yana.fakername.fragmentsViewModel.RegisterNameViewModel
import com.example.yana.fakername.ui.MainActivity
import com.example.yana.fakername.ui.SelectScreenActivity
import com.example.yana.fakername.utils.cleanLaunchActivity
import com.example.yana.fakername.utils.setSafeOnClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterNameFragment: Fragment() {

    private lateinit var binding: FragmentRegisterNameBinding
    private val viewModel: RegisterNameViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        viewModel.eventAuth.observe(viewLifecycleOwner, {
            if (it)requireContext().cleanLaunchActivity<SelectScreenActivity>()
            Toast.makeText(requireContext(), "dfdfgeewewew$it", Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupListeners() {
        binding.btnRegistrationName.setSafeOnClickListener {
            if (isEmailValid(binding.etEmail.text.toString(),
                    binding.etPasswordRepeat.text.toString())) {
                viewModel.register(
                    binding.etName.text.toString(),
                     binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
            }
        }
    }

    fun isEmailValid(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.etEmail.error = "email пустой"
            return false
        }
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (!isValid){
            binding.etEmail.error = "введите корректный email"
        }
        if (password.isEmpty()){
            binding.etPasswordRepeat.error = "подтвердите пароль"
            return false
        }
        return isValid
    }
}