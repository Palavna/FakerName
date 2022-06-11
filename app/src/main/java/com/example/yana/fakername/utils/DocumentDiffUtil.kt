package com.example.yana.fakername.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.yana.fakername.dataClass.DocumentsPage

class DocumentDiffUtil:  DiffUtil.ItemCallback<DocumentsPage>() {

    override fun areItemsTheSame(oldItem: DocumentsPage, newItem: DocumentsPage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DocumentsPage, newItem: DocumentsPage): Boolean {
        return oldItem.id == newItem.id
                && oldItem.inn == newItem.inn
                && oldItem.passport == newItem.passport
                && oldItem.description == newItem.description
                && oldItem.country_id == newItem.country_id
                && oldItem.user_id == newItem.user_id
                && oldItem.created_at == newItem.created_at
                && oldItem.updated_at == newItem.updated_at

    }
}