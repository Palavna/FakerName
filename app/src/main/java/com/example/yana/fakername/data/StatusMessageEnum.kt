package com.example.yana.fakername.data

import com.example.yana.fakername.R

enum class StatusMessageEnum(val status: Int) {
    POSITIVE(1),
    NEGATIVE(0);

    companion object{
        fun isPositiveStatus(status: Int) = POSITIVE.status == status

        fun getStatusText(status: Int): Int =
            if(isPositiveStatus(status)) R.string.positive else R.string.negative
    }
}