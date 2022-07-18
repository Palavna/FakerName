package com.example.yana.fakername.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yana.fakername.adapters.CommentListAdapter
import com.example.yana.fakername.adapters.DocumentListener
import com.example.yana.fakername.adapters.SearchAdapter
import com.example.yana.fakername.dataClass.Countries
import com.example.yana.fakername.dataClass.DocumentsPage
import com.example.yana.fakername.databinding.FragmentDetailsBinding
import com.example.yana.fakername.fragmentsViewModel.DetailsViewModel
import com.example.yana.fakername.ui.MainActivity
import com.example.yana.fakername.utils.getTextIsNotEmpty
import com.example.yana.fakername.utils.setSafeOnClickListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment() : Fragment(), DocumentListener {

    private val countryId: Int
        get() = arguments?.getInt(COUNTRYID) ?: -1

    private val query: String
        get() = arguments?.getString(QUERY) ?: ""

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
        binding.groupTv.isVisible = false


        binding.recyclerList.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        binding.recyclerList.adapter = adapterList

        binding.btnSaveEditCom.setSafeOnClickListener{
            if (isInnValid()){
                viewModel.createDocument(
                    countryId, query,
                    binding.etAddTextEditCom.text.toString(),
                    binding.positive.isChecked)
                binding.etAddTextEditCom.setText("")
            }

            viewModel.progress.observe(this, {
                binding.progress.isVisible = it
            })
        }

        lifecycleScope.launch {
            viewModel.doc(query, countryId).collect { adapter.submitData(it) }
        }

        viewModel.search(query, countryId)
        viewModel.saveDoc.observe(viewLifecycleOwner, {
            adapterList.update(it?.comments ?: emptyList())
        }
        )

        viewModel.userDoc.observe(viewLifecycleOwner, {
            binding.telephoneNam.text = it?.inn
            binding.country.text = it?.country?.name
            binding.description.text = it?.description.getTextIsNotEmpty()
            binding.positiveTv.text = it?.positiveCount.toString()
            binding.negativeTv.text = it?.negativeCount.toString()
            binding.groupTv.isVisible = it != null
            binding.tvEmptiMessege.isVisible = it == null

        }
        )
    }

    fun isInnValid(): Boolean {

        var isValid = true
        var missiedFileds = mutableListOf<String>()

        if (binding.etAddTextEditCom.text.toString().length<10) {
            binding.etAddTextEditCom.error = "оставьте комментарий"
            missiedFileds.add("оставьте комментарий")
            isValid = false
        }
        if (!binding.positive.isChecked && !binding.negative.isChecked){
            missiedFileds.add("radio button")
            isValid = false
        }

        return isValid
    }


    companion object {

        private const val COUNTRYID = "COUNTRYID"
        private const val QUERY = "QUERY"

        fun create(query: String, id: Int): DetailsFragment {
            val fragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putInt(COUNTRYID, id)
            bundle.putString(QUERY, query)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun editDocument(id: Int) {
        (requireActivity() as MainActivity).changeFragment(EditCommentFragment(id), true)
    }
}