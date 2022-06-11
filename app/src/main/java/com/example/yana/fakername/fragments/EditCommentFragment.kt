package com.example.yana.fakername.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.yana.fakername.R
import com.example.yana.fakername.databinding.FragmentEditCommentBinding
import com.example.yana.fakername.databinding.FragmentMainBinding
import com.example.yana.fakername.fragmentsViewModel.EditCommentViewModel
import com.example.yana.fakername.fragmentsViewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditCommentFragment(id: Int) : Fragment() {

    private lateinit var binding: FragmentEditCommentBinding
    private val viewModel: EditCommentViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onRadioButtonClicked(view)
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.positive ->
                    if (checked) {}
                R.id.negative ->
                    if (checked) {}
            }
        }
    }
}