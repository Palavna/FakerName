package com.example.yana.fakername.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yana.fakername.R
import com.example.yana.fakername.adapters.CommentListAdapter
import com.example.yana.fakername.adapters.DocumentListener
import com.example.yana.fakername.adapters.SearchAdapter
import com.example.yana.fakername.databinding.FragmentDetailsBinding
import com.example.yana.fakername.fragmentsViewModel.DetailsViewModel
import com.example.yana.fakername.ui.FragmentCallBack
import com.example.yana.fakername.utils.getTextIsNotEmpty
import com.example.yana.fakername.utils.setSafeOnClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment() : Fragment(), DocumentListener {

    private val args by navArgs<DetailsFragmentArgs>()

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModel()
    private val adapter by lazy { SearchAdapter(this) }
    private val adapterList by lazy { CommentListAdapter(this) }


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
        setupListeners()
        viewModelObserve()

        binding.groupTv.isVisible = false
        binding.recyclerList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL))
        binding.recyclerList.adapter = adapterList
    }


    private fun viewModelObserve() {
//        lifecycleScope.launch {
//            viewModel.doc(args.query, args.countryId).collect { adapterList.submitData(it) }
//        }

        viewModel.search(args.query, args.countryId)
        viewModel.saveDoc.observe(
            viewLifecycleOwner
        ) {
            adapterList.submitList(it?.comments ?: emptyList())
            adapterList.notifyDataSetChanged()
        }

        viewModel.userDoc.observe(
            viewLifecycleOwner
        ) {
            binding.telephoneNam.text = it?.inn
            binding.country.text = it?.countries?.name
            binding.description.text = it?.description.getTextIsNotEmpty()
            binding.positiveTv.text = it?.positiveCount.toString()
            binding.negativeTv.text = it?.negativeCount.toString()
            binding.groupTv.isVisible = it != null
            binding.tvEmptiMessege.isVisible = it == null

        }
    }

    private fun setupListeners() {
        binding.btnSaveEditCom.setSafeOnClickListener {
            if (isInnValid()) {
                viewModel.createDocument(
                    args.countryId, args.query,
                    binding.etAddTextEditCom.text.toString(),
                    binding.positive.isChecked
                )
                binding.etAddTextEditCom.setText("")
            }

            viewModel.progress.observe(viewLifecycleOwner) {
                binding.progress.isVisible = it
            }
        }
        binding.toolbarInfo.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun isInnValid(): Boolean {

        var isValid = true
        var missiedFileds = mutableListOf<String>()

        if (binding.etAddTextEditCom.text.toString().length < 10) {
            binding.etAddTextEditCom.error = getString(R.string.leaveComment)
            missiedFileds.add(getString(R.string.leaveComment))
            isValid = false
        }
        if (!binding.positive.isChecked && !binding.negative.isChecked) {
            missiedFileds.add(getString(R.string.radioButton))
            isValid = false
        }

        return isValid
    }

    override fun editDocument(id: Int) {
        findNavController().navigate(R.id.action_detailsFragment2_to_editCommentFragment2)
    }

    override fun deleteDocument(position: Int) {
        viewModel.deleteDocument(position)
    }
}