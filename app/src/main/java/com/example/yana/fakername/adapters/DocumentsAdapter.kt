package com.example.yana.fakername.adapters

import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.fakername.dataClass.DocumentsPage
import com.example.yana.fakername.databinding.ItemRecyclerBinding
import com.example.yana.fakername.prefs.SharedPreferenceFaker
import com.example.yana.fakername.utils.DocumentDiffUtil
import java.text.SimpleDateFormat
import java.util.*

class DocumentsAdapter(private val listener: DocumentListener) :
    PagingDataAdapter<DocumentsPage, DocumentsVH>(DocumentDiffUtil()) {

    override fun onBindViewHolder(holder: DocumentsVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DocumentsVH.create(listener, parent)

}


class DocumentsVH(private val binding: ItemRecyclerBinding, private val listener: DocumentListener) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(documents: DocumentsPage) {
        binding.inn.text = documents.inn ?: documents.passport
        binding.comment.text = documents.description
        binding.data.text = documents.created_at

        binding.edit.setOnClickListener {
            listener.editDocument(documents.id)
        }

        setupDate(documents.created_at)

        if (documents.id % 2 == 0) {
            binding.btnPositive.text = "Положительный"
            binding.btnPositive.isActivated = true
        } else {
            binding.btnPositive.text = "Отрицательный"
            binding.btnPositive.isActivated = false
        }

        val isButtonsVisible = SharedPreferenceFaker.id == documents.user_id
        binding.delete.isVisible = isButtonsVisible
//        binding.edit.isVisible = isButtonsVisible


        val htmlString: String = documents.description ?: "Отсутствует"
        val spanned: Spanned = HtmlCompat
            .fromHtml(
                htmlString,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        binding.comment.text = spanned.trim()
    }

    private fun setupDate(date: String){
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
        val data = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val sdfNew = sdf.parse(date)
        val dataNew = data.format(sdfNew)
        binding.data.text = dataNew
    }

    companion object {
        fun create(listener: DocumentListener, parent: ViewGroup): DocumentsVH {
            val binding =
                ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DocumentsVH(binding, listener)
        }
    }
}