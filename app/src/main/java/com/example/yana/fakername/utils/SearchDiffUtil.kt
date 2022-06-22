package com.example.yana.fakername.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.yana.fakername.dataClass.DocumentsPage
import com.example.yana.fakername.dataClass.SearchModel

class SearchDiffUtil:  DiffUtil.ItemCallback<SearchModel>() {

    override fun areItemsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
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