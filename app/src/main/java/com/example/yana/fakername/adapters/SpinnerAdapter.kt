package com.example.yana.fakername.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.yana.fakername.dataClass.Countries
import com.example.yana.fakername.databinding.ItemSpinnerBinding

class SpinnerAdapter (context: Context, resId: Int, val array: Array<Countries>):
    ArrayAdapter<Countries>(context, resId, array) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding =
            ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.tvTitle.text = array[position].name
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding =
            ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.tvTitle.text = array[position].name
        return binding.root
    }
}