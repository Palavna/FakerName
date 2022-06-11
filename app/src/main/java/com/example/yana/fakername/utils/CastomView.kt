package com.example.yana.fakername.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.yana.fakername.R
import com.example.yana.fakername.ui.CastomViewCallback

class CastomView: ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var listener: CastomViewCallback? = null
    private val tv: TextView
    private val et: EditText
    private val btn: ImageView
    private val check: ImageView
    private var isEditable = false

    init {
        val view =  View.inflate(context, R.layout.castom_view, this)

        tv = view.findViewById(R.id.nameFioTv)
        et = view.findViewById(R.id.nameFioEt)
        btn = view.findViewById(R.id.pencil)
        check = view.findViewById(R.id.checkM)

        btn.setOnClickListener {
            changeEditable()
        }
        check.setOnClickListener {
            changeEditable()
            listener?.changeUserName(et.text.toString())
        }
    }

    private fun changeEditable() {
        isEditable = !isEditable
        tv.isInvisible = isEditable
        et.isInvisible = !isEditable

        btn.isVisible = !isEditable
        check.isVisible = isEditable
    }

     fun setupText(name: String?) {
         tv.text = name
         et.setText(name)
     }

    fun setupListener(listener: CastomViewCallback) {

        this.listener = listener
    }
}