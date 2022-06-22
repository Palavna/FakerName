package com.example.yana.fakername.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.yana.fakername.adapters.DocumentListener
import com.example.yana.fakername.adapters.SearchAdapter
import com.example.yana.fakername.dataClass.SearchModel
import com.example.yana.fakername.databinding.FragmentDetailsBinding
import com.example.yana.fakername.fragmentsViewModel.DetailsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment() : Fragment(), DocumentListener {

    private val countryId : Int
        get() = arguments?.getInt(COUNTRYID) ?: -1

    private val query: String
        get() =  arguments?.getString(QUERY) ?: ""

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModel()
    private val adapter by lazy { SearchAdapter(this) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerList.adapter = adapter

        lifecycleScope.launch {
            viewModel.doc(query, countryId).collect { adapter.submitData(it) }
        }
    }


    companion object {

        private const val COUNTRYID = "COUNTRYID"
        private const val QUERY = "QUERY"

        fun create(query: String, id: Int): DetailsFragment{
            val fragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putInt(COUNTRYID, id)
            bundle.putString(QUERY, query)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun editDocument(id: Int) {
    }
}