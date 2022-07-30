package com.example.yana.fakername.adapters

import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.fakername.dataClass.SearchModel
import com.example.yana.fakername.databinding.ItemListRecyclerBinding
import com.example.yana.fakername.utils.SearchDiffUtil

class SearchAdapter(private val listener: DocumentListener): PagingDataAdapter<SearchModel, SearchVH>(
    SearchDiffUtil()
) {
    override fun onBindViewHolder(holder: SearchVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchVH.create(listener, parent)
}

class SearchVH(private val binding: ItemListRecyclerBinding, private val listener: DocumentListener):
   RecyclerView.ViewHolder(binding.root){

    fun bind(documents: SearchModel){


    //        binding.inn.text = documents.inn ?: documents.passport
    //        binding.comment.text = documents.country.name
    //
    //        binding.data.text = documents.description
    //        binding.positiveTv.text = documents.positiveCount.toString()
    //        binding.negativeTv.text = documents.negativeCount.toString()
    //
    //        binding.edit.setOnClickListener {
    //            listener.editDocument(documents.id)
    //        }
    }


    companion object {
        fun create(listener: DocumentListener, parent: ViewGroup): SearchVH {
            val binding =
                ItemListRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SearchVH(binding, listener)
        }
    }
}