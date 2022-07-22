package com.example.yana.fakername.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yana.fakername.R
import com.example.yana.fakername.adapters.SpinnerAdapter
import com.example.yana.fakername.dataClass.Countries
import com.example.yana.fakername.databinding.FragmentMainBinding
import com.example.yana.fakername.fragmentsViewModel.MainViewModel
import com.example.yana.fakername.ui.FragmentCallBack
import com.example.yana.fakername.utils.hideKeyboard
import com.example.yana.fakername.utils.setSafeOnClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

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
        setupListeners()

        viewModel.countries.observe(viewLifecycleOwner, {
            val adapter = SpinnerAdapter(
                requireContext(),
                R.layout.item_spinner,
                it?.toTypedArray() ?: emptyArray()
            )

            binding.spinner.adapter = adapter
        })
        doSomething(binding.etFaker)
    }


    private fun doSomething(search: EditText) {
        search.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (isMainValid()) {
                    hideKeyboard()
                    goToDetails()
                }

                return@OnEditorActionListener true
            }
            false
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setupListeners() {
        binding.btnSearch.setSafeOnClickListener {
            if (isMainValid()) goToDetails()
        }

        binding.spinner.setOnTouchListener { _, _ ->
            hideKeyboard()
            return@setOnTouchListener false
        }
    }

    private fun goToDetails() {
        val directions =
            MainFragmentDirections.toDetailsFragment(binding.etFaker.text.toString())
        directions.countryId = (binding.spinner.selectedItem as? Countries)?.id ?: -1
        findNavController().navigate(directions)
    }

    fun isMainValid(): Boolean {
        var isValid = true
        var missiedFileds = mutableListOf<String>()
        if ((binding.spinner.selectedItem as? Countries)?.id == -1) {
            missiedFileds.add("выберите страну")
            isValid = false
        }
        if (binding.etFaker.text.toString().isEmpty()) {
            binding.etFaker.error = "введите ПИН"
            missiedFileds.add("введите ПИН")
            isValid = false
        }
        if (!isValid) {
            val text = resources.getQuantityString(R.plurals.empty_field_msg, missiedFileds.size)
                .plus(missiedFileds.joinToString(separator = ", "))
            Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
        }
        return isValid
    }

}