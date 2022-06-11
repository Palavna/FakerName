package com.example.yana.fakername.fragments

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.yana.fakername.R
import com.example.yana.fakername.adapters.SpinnerAdapter
import com.example.yana.fakername.databinding.FragmentDataAddBinding
import com.example.yana.fakername.fragmentsViewModel.DataAddViewModel
import com.example.yana.fakername.ui.FragmentCallBack
import org.koin.androidx.viewmodel.ext.android.viewModel

class DataAddFragment: Fragment() {

    private lateinit var binding: FragmentDataAddBinding
    private val viewModel: DataAddViewModel by viewModel()
    private var callBack: FragmentCallBack? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.countries.observe(viewLifecycleOwner, {
            val adapter = SpinnerAdapter(requireContext(), R.layout.item_spinner, it?.toTypedArray() ?: emptyArray())

            binding.spinnerAdd.adapter = adapter

            setupListeners()

        })
    }

    fun setupListeners(){
        binding.btnSend.setOnClickListener {
            isInnValid(inn = String())
           callBack?.openProfile()
            viewModel.createComment()

        }
    }

    fun isInnValid(inn: String): Boolean {
        if (inn.isEmpty()) {
            binding.etAddData.error = "заполните это поле"
            return false
        }
        val isValid = Patterns.EMAIL_ADDRESS.matcher(inn).matches()
        if (!isValid){
            binding.etAddText.error = "добавьте комментарий"
        }
        return isValid
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = context as FragmentCallBack
    }
}