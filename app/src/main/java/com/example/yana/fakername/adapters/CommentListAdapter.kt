package com.example.yana.fakername.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.fakername.data.StatusMessageEnum
import com.example.yana.fakername.dataClass.CommentsUser
import com.example.yana.fakername.databinding.ItemListRecyclerBinding
import com.example.yana.fakername.prefs.SharedPreferenceFaker
import com.example.yana.fakername.utils.getTextIsNotEmpty

class CommentListAdapter(private val listener: (DocumentListener)):RecyclerView.Adapter<CommentListAdapterVH>() {

    private val list = arrayListOf<CommentsUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentListAdapterVH {
        val binding = ItemListRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentListAdapterVH(binding, listener)
    }

    override fun onBindViewHolder(holder: CommentListAdapterVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun update(comment: List<CommentsUser>) {
        list.clear()
        list.addAll(comment)
        notifyDataSetChanged()
    }
}

class CommentListAdapterVH(val binding: ItemListRecyclerBinding, private val listener: (DocumentListener)):
    RecyclerView.ViewHolder(binding.root) {

        fun bind(commentUser: CommentsUser){
            binding.nameUser.text = commentUser.user.name
            binding.commentUser.text = commentUser.text.getTextIsNotEmpty()

            binding.positive.isActivated = StatusMessageEnum.isPositiveStatus(commentUser.is_positive)
            binding.positive.text = binding.root.context.getString(StatusMessageEnum.getStatusText(commentUser.is_positive))

            binding.btnEdit.isInvisible = commentUser.user_id != SharedPreferenceFaker.id

            binding.delete.isInvisible = commentUser.user_id != SharedPreferenceFaker.id

            binding.btnEdit.setOnClickListener {
                listener.editDocument(commentUser.id)
            }
            binding.delete.setOnClickListener {

            }
        }
}