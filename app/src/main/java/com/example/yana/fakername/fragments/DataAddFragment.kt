package com.example.yana.fakername.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.yana.fakername.R
import com.example.yana.fakername.adapters.SpinnerAdapter
import com.example.yana.fakername.dataClass.Countries
import com.example.yana.fakername.databinding.FragmentDataAddBinding
import com.example.yana.fakername.fragmentsViewModel.DataAddViewModel
import com.example.yana.fakername.ui.FragmentCallBack
import com.example.yana.fakername.ui.MainActivity
import com.example.yana.fakername.utils.cleanLaunchActivity
import com.example.yana.fakername.utils.setSafeOnClickListener
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
        viewModel.eventAuth.observe(viewLifecycleOwner, {
            if (it)callBack?.openProfile()
        })

        viewModel.countries.observe(viewLifecycleOwner, {
            val adapter = SpinnerAdapter(requireContext(), R.layout.item_spinner, it?.toTypedArray() ?: emptyArray())

            binding.spinnerAdd.adapter = adapter

            setupListeners()

        })
    }

    fun setupListeners(){
        binding.btnSend.setSafeOnClickListener {
            if (isInnValid()){
                viewModel.createDocument(
                    (binding.spinnerAdd.selectedItem as Countries).id,
                    binding.etAddData.text.toString(),
                    binding.etAddText.text.toString(),
                    binding.positive.isChecked)
            }
        }
    }

    fun isInnValid(): Boolean {
        var isValid = true
        var missiedFileds = mutableListOf<String>()
        if ((binding.spinnerAdd.selectedItem as? Countries)?.id == -1) {
            missiedFileds.add("выберите страну")
            isValid = false
        }
        if (binding.etAddData.text.toString().isEmpty())  {
            binding.etAddData.error = "введите ПИН"
            missiedFileds.add("введите ПИН")
            isValid = false
        }
        if (binding.etAddText.text.toString().length<10) {
            binding.etAddText.error = "оставьте комментарий"
            missiedFileds.add("оставьте комментарий")
            isValid = false
        }
        if (!binding.positive.isChecked && !binding.negative.isChecked){
            missiedFileds.add("radio button")
            isValid = false
        }
        if (!isValid){
            val text = resources.getQuantityString(R.plurals.empty_field_msg, missiedFileds.size)
                .plus(missiedFileds.joinToString(separator = ", "))
            Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
        }

        return isValid
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = context as FragmentCallBack
    }
}